package fun.oook.here.service;

import fun.oook.here.entity.User;

import java.util.UUID;

/**
 * @author Joey
 * @version 1.0
 * @since 2018/12/18 19:28
 */
public interface UserService {


    public User login(String email, String password);
}