package fun.oook.here.service;

import fun.oook.here.entity.User;

import java.util.UUID;

/**
 * @author Joey
 * @version 1.0
 * @since 2018/12/18 19:28
 */
public interface UserService {

    /**
     * User login
     *
     * @param email    email
     * @param password password
     * @return user
     */
    User login(String email, String password);

    /**
     * User Authentication
     *
     * @param user user
     * @return true if user verification succeeded
     */
    boolean auth(User user);

    /**
     * Token verification
     *
     * @param token token
     * @return return user if user verification succeeded
     */
    User tokenVerify(String token);

    /**
     * make a visitor, only save in redis
     *
     * @return visitor
     */
    User visitor();

    /**
     * User registration
     *
     * @param email    email
     * @param password password
     * @return user
     */
    User register(String email, String password);

}