package fun.oook.here.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.oook.here.common.CommonException;
import fun.oook.here.entity.Position;

/**
 * Service for {@link Position}
 *
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
public interface PositionService {

    /**
     * Get a position by id
     *
     * @param id id
     * @return position
     * @throws CommonException e
     */
    JSONObject getPositionById(Long id) throws CommonException;

    /**
     * List positions randomly
     *
     * @param fetchSize list size
     * @return positions
     * @throws CommonException e
     */
    JSONArray listPositionsRandom(int fetchSize) throws CommonException;

    /**
     * Count all positions
     *
     * @return number of positions
     * @throws CommonException e
     */
    Long countPositions()throws CommonException;

    /**
     * Save a position
     *
     * @param position position
     * @return id
     * @throws CommonException e
     */
    String savePosition(Position position) throws CommonException;

    /**
     * Remove a position by id
     *
     * @param id id
     * @throws CommonException e
     */
    void removePositionById(Long id) throws CommonException;

    /**
     * Update a position
     *
     * @param position position
     * @throws CommonException e
     */
    void updatePosition(Position position) throws CommonException;
}
