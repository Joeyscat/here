package fun.oook.here.repository.redis;

import fun.oook.here.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Joey
 * @version 1.0
 * @since 2018/12/18 18:24
 */
public class UserRedisRepository {

    @Autowired
    private RedisTemplate<String, User> userRedisTemplate;

    public User findById(String id) {
        return userRedisTemplate.opsForValue().get(id);
    }
}