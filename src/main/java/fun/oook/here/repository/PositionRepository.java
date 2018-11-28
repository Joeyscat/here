package fun.oook.here.repository;

import fun.oook.here.model.Position;
import org.b3log.latke.repository.AbstractRepository;
import org.b3log.latke.repository.RepositoryException;
import org.b3log.latke.repository.annotation.Repository;

/**
 * PositionRepository
 *
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
@Repository
public class PositionRepository extends AbstractRepository {
    /**
     * Constructor
     */
    public PositionRepository() {
        super(Position.POSITION);
    }

    @Override
    public void remove(String id) throws RepositoryException {
        // TODO 18-11-27 01:09 Doesn't Work
        super.remove(id);
    }
}