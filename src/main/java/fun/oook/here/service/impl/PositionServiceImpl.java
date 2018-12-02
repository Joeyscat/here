package fun.oook.here.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.oook.here.entity.Position;
import fun.oook.here.repository.PositionRepository;
import fun.oook.here.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
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

    /**
     * Get a position by id
     *
     * @param id id
     * @return position
     */
    @Override
    public JSONObject getPositionById(Long id) {

        return null;
    }

    /**
     * List positions randomly
     *
     * @param fetchSize list size
     * @return positions
     */
    @Override
    public JSONArray listPositionsRandom(int fetchSize) {
        List<Position> positions = positionRepository.listRandomPosition(fetchSize);

        JSONArray positionArray = new JSONArray();
        positionArray.addAll(positions);

        return positionArray;
    }

    /**
     * Count all positions
     *
     * @return number of positions
     */
    @Override
    public Long countPositions() {
        return null;
    }

    /**
     * Save a position
     *
     * @param position position
     * @return id
     */
    @Override
    public String savePosition(Position position) {

        Position newPosition = positionRepository.save(position);

        return String.valueOf(newPosition.getId());
    }

    /**
     * Remove a position by id
     *
     * @param id id
     */
    @Override
    public void removePositionById(Long id) {

    }

    /**
     * Update a position
     *
     * @param position position
     */
    @Override
    public void updatePosition(Position position) {

    }
}