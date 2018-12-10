package fun.oook.here.repository.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Joey
 * @date 2018-12-10
 * @since 1.0
 */
@Repository
public class GeoRedisRepository {

    @Autowired
    private RedisTemplate<String, String> geoRedisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoRedisRepository.class);

    public static final String GEO_KEY_POSITION = "GEO_POSITION";


    /**
     * GeoAdd
     *
     * @param geoKey geoKey
     * @param member member
     * @param x      longitude
     * @param y      latitude
     * @return result
     */
    public Long geoAdd(String geoKey, String member, double x, double y) {
        Long addedResult = geoRedisTemplate.opsForGeo()
                .add(geoKey, new Point(x, y), member);

        LOGGER.debug("GeoAdd result: [{}]", addedResult);
        return addedResult;
    }

    /**
     * GeoAdd
     *
     * @param geoKey geoKey
     * @param members members
     * @return geo list
     */
    public List<Point> geoGet(String geoKey, String... members) {
        List<Point> points = geoRedisTemplate.opsForGeo().position(geoKey, members);

        LOGGER.debug("Get geo list: {}", points);

        return points;
    }

    public GeoResults<RedisGeoCommands.GeoLocation<String>> getNearByWithPlace(String geoKey, String member, Distance distance, Long count){
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance().includeCoordinates().sortAscending().limit(count);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoRedisTemplate.opsForGeo()
                .radius(geoKey,member,distance,args);

        if (results == null){
            return null;
        }

        return results;
    }
}