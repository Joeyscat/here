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

import java.util.List;

/**
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
@Service
public class PositionService {

    @Inject
    private PositionRepository positionRepository;


    public List<JSONObject> getAllPositions() {

        return null;
    }

    public String addPosition(JSONObject requestJSONObject) throws ServiceException {
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