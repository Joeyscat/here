package fun.oook.here.repository;

import fun.oook.here.model.Position;
import org.b3log.latke.repository.AbstractRepository;
import org.b3log.latke.repository.annotation.Repository;

/**
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
}