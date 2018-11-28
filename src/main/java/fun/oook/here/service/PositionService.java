package fun.oook.here.service;

import fun.oook.here.model.Position;
import fun.oook.here.repository.PositionRepository;
import org.apache.commons.lang.StringUtils;
import org.b3log.latke.Keys;
import org.b3log.latke.ioc.Inject;
import org.b3log.latke.repository.RepositoryException;
import org.b3log.latke.repository.Transaction;
import org.b3log.latke.service.ServiceException;
import org.b3log.latke.service.annotation.Service;
import org.b3log.latke.util.Ids;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * PositionService
 *
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
@Service
public class PositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionService.class);

    /**
     * Repository for {@link Position}
     */
    @Inject
    private PositionRepository positionRepository;

    /**
     * Add a position
     *
     * @param requestJSONObject requestJSONObject
     * @return result
     * @throws ServiceException e
     */
    public String addPosition(JSONObject requestJSONObject) throws ServiceException {
        LOGGER.info("Adding position {}", requestJSONObject);

        final Transaction transaction = positionRepository.beginTransaction();

        try {
            final JSONObject position = requestJSONObject.getJSONObject(Position.POSITION);
            final String ret = addPositionInternal(position);
            transaction.commit();

            return ret;
        } catch (final Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Removes the position by the given id.
     *
     * @param positionId the given id
     * @throws ServiceException service exception
     */
    public void removePosition(String positionId) throws ServiceException {
        LOGGER.info("Removing position by id [{}]", positionId);

        final Transaction transaction = positionRepository.beginTransaction();

        try {
            final JSONObject position = positionRepository.get(positionId);
            if (position == null) {
                LOGGER.info("Cannot found position [{}]", positionId);

                throw new ServiceException("Cannot found position");
            }
            positionRepository.remove(positionId);
        } catch (final Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            LOGGER.info("Remove position [{}] failure", positionId);
            throw new ServiceException(e);
        }

        LOGGER.info("Removed position [{}]", positionId);
    }

    /**
     * Get the position by specified id
     *
     * @param positionId positionId
     * @return position
     * @throws ServiceException e
     */
    public JSONObject getPosition(final String positionId) throws ServiceException {
        LOGGER.info("Getting position by id [{}]", positionId);

        final Transaction transaction = positionRepository.beginTransaction();

        try {
            final JSONObject position = positionRepository.get(positionId);
            transaction.commit();
            LOGGER.info("Got position [{}]", position);

            return position;
        } catch (final Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Get positions randomly
     *
     * @param fetchSize size
     * @return positions
     * @throws ServiceException e
     */
    public List<JSONObject> getPositionsRandomly(final int fetchSize) throws ServiceException {
        LOGGER.info("Getting [{}] positions", fetchSize);

        final Transaction transaction = positionRepository.beginTransaction();

        try {
            final List<JSONObject> positions = positionRepository.getRandomly(fetchSize);
            transaction.commit();
            return positions;
        } catch (final Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Add a position
     *
     * @param position position
     * @return id
     * @throws RepositoryException e
     */
    private String addPositionInternal(final JSONObject position) throws RepositoryException {
        String ret = position.optString(Keys.OBJECT_ID);
        if (StringUtils.isBlank(ret)) {
            ret = Ids.genTimeMillisId();
            position.put(Keys.OBJECT_ID, ret);
        }

        positionRepository.add(position);

        return ret;
    }
}