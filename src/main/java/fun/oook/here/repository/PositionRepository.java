package fun.oook.here.repository;

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
     * Constructs a repository with the specified name.
     *
     * @param name the specified name
     */
    public PositionRepository(String name) {
        super(name);
    }
}