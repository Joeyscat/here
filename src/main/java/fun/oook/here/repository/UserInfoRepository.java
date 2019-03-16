package fun.oook.here.repository;

import fun.oook.here.entity.shiro.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Joey
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {


    /**
     * Find by username
     *
     * @param username username
     * @return user
     */
    UserInfo findByUsername(String username);

}
