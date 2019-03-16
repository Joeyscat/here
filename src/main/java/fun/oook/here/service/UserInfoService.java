package fun.oook.here.service;

import fun.oook.here.entity.shiro.UserInfo;

/**
 * @author Joey
 */
public interface UserInfoService {

    /**
     * 通过username查找用户信息
     *
     * @param username username
     * @return 用户信息
     */
    UserInfo findByUsername(String username);
}
