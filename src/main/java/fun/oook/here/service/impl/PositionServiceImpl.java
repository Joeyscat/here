package fun.oook.here.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.oook.here.common.CommonException;
import fun.oook.here.entity.Position;
import fun.oook.here.repository.jpa.PositionRepository;
import fun.oook.here.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * implementation of {@link PositionService}
 *
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
@Service
public class PositionServiceImpl implements PositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);

    @Autowired
    private PositionRepository positionRepository;

    // TODO 18-12-2 19:37 查询条件做好判空,以免传入null导致全表扫描


    @Autowired
    private RedisTemplate<String, String> geoRedisTemplate;


    @Override
    public JSONObject getPositionById(Long id) {

        return null;
    }

    @Override
    public JSONArray listPositionsRandom(int fetchSize) {
        List<Position> positions = positionRepository.listRandomPosition(fetchSize);

        JSONArray positionArray = new JSONArray();
        positionArray.addAll(positions);

        return positionArray;
    }

    @Override
    public JSONArray listPositionsNearby(Position position, int fetchSize) {

        List<Position> positions = new ArrayList<>();
        // 从redis获取缓存

        Distance distance = new Distance(1D);
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = geoRedisTemplate
                .boundGeoOps("location").radius(position.getCreatedBy(), distance);

        position = new Position();
        assert geoResults != null;
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult : geoResults) {

            RedisGeoCommands.GeoLocation<String> res = geoResult.getContent();
            Point point = res.getPoint();
            LOGGER.info("P {}", point);

            position.setName(res.getName());
            position.setLng(String.valueOf(point.getX()));
            position.setLat(String.valueOf(point.getY()));

            positions.add(position);
        }
        // 返回point+name

        return new JSONArray(Collections.singletonList(positions));
    }

    @Override
    public Long countPositions() {
        return null;
    }

    @Override
    public String savePosition(Position position) {

        if (position == null) {
            throw new CommonException("", "Position must not be null");
        }

        Position newPosition = positionRepository.save(position);

        // TODO 18-12-3 21:25 redis 缓存最近的position记录,缓存时间=最新位置保留时间
        // 用户标记作为key,每个用户只保留一个最新位置
        if (position.getCreatedBy() != null) {
            Point point = new Point(Long.getLong(position.getLng()), Long.getLong(position.getLat()));
            String locationName = position.getCreatedBy();

            geoRedisTemplate.boundGeoOps("location").add(new RedisGeoCommands.GeoLocation<>(locationName, point));
        }

        return String.valueOf(newPosition.getId());
    }

    @Override
    public void removePositionById(Long id) {

    }

    @Override
    public void updatePosition(Position position) {

    }
}