package fun.oook.here.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.oook.here.common.HereException;
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
     * @throws HereException e
     */
    JSONObject getPositionById(Long id) throws HereException;

    /**
     * List positions randomly
     *
     * @param fetchSize list size
     * @return positions
     * @throws HereException e
     */
    JSONArray listPositionsRandom(int fetchSize) throws HereException;

    /**
     * List positions nearby
     *
     * @param fetchSize list size
     * @param position current position
     * @return positions
     * @throws HereException e
     */
    JSONArray listPositionsNearby(Position position, int fetchSize) throws HereException;

    /**
     * Count all positions
     *
     * @return number of positions
     * @throws HereException e
     */
    Long countPositions()throws HereException;

    /**
     * Save a position
     *
     * @param position position
     * @return id
     * @throws HereException e
     */
    String savePosition(Position position) throws HereException;

    /**
     * Remove a position by id
     *
     * @param id id
     * @throws HereException e
     */
    void removePositionById(Long id) throws HereException;

    /**
     * Update a position
     *
     * @param position position
     * @throws HereException e
     */
    void updatePosition(Position position) throws HereException;
}
