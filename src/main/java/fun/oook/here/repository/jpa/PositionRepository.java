package fun.oook.here.repository.jpa;

import fun.oook.here.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
public interface PositionRepository extends JpaRepository<Position, Long> {

    /**
     * List positions randomly
     *
     * @param fetchSize size
     * @return position list
     */
    @Query(nativeQuery = true, value = "SELECT * FROM position ORDER BY rand() LIMIT :fetchSize")
    List<Position> listRandomPosition(@Param("fetchSize") int fetchSize);
}