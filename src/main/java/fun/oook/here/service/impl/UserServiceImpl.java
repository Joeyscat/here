package fun.oook.here.service.impl;

import fun.oook.here.entity.User;
import fun.oook.here.repository.jpa.UserRepository;
import fun.oook.here.repository.redis.UserRedisRepository;
import fun.oook.here.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Joey
 * @version 1.0
 * @since 2018/12/18 19:28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRedisRepository userRedisRepository;

    @Override
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User tokenVerify(String id) {
        return userRedisRepository.findById(id);
    }

    @Override
    public boolean auth(User user) {

        return true;
    }

    @Override
    public User visitor() {
        User user = new User();
        user.setUid(UUID.randomUUID().toString());
        userRedisRepository.saveVisitor(user);
        return user;
    }

    @Override
    public User register(String email, String password) {

        User user = new User();
        user.setUid(UUID.randomUUID().toString());
        user.setName(email);
        user.setPassword(password);
        user.setEmail(email);

        userRepository.save(user);

        return user;
    }
}