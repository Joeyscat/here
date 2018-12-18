package fun.oook.here.repository.jpa;

import fun.oook.here.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * @author Joey
 * @version 1.0
 * @since 2018/12/18 19:31
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * find an user by email
     *
     * @param email email
     * @return user if exist
     */
    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE email=:email")
    User findByEmail(@Param("email") String email);
}