package fun.oook.here.service;

import fun.oook.here.model.Position;
import fun.oook.here.repository.PositionRepository;
import org.b3log.latke.ioc.Inject;
import org.b3log.latke.repository.Transaction;
import org.b3log.latke.service.ServiceException;
import org.b3log.latke.service.annotation.Service;
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


    public List<JSONObject> getAllPositions(){

        return null;
    }

    public String addPosition(JSONObject requestJSONObject) throws ServiceException {
        final Transaction transaction = positionRepository.beginTransaction();

        try {
            final JSONObject position = requestJSONObject.getJSONObject(Position.POSITION);
            final String ret = "";
            transaction.commit();

            return ret;
        }catch (final Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }

            throw new ServiceException(e.getMessage());
        }
    }
}