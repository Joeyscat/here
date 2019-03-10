package fun.oook.here.repository.redis;

import fun.oook.here.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Joey
 * @version 1.0
 * @since 2018/12/18 18:24
 */
@Repository
public class UserRedisRepository {

    private final RedisTemplate<String, User> userRedisTemplate;

    @Autowired
    public UserRedisRepository(RedisTemplate<String, User> userRedisTemplate) {
        this.userRedisTemplate = userRedisTemplate;
    }

    public User findById(String id) {
        return userRedisTemplate.opsForValue().get(id);
    }

    public void saveVisitor(User user) {
        userRedisTemplate.opsForValue().set(String.valueOf(user.getId()), user);
    }
}