package fun.oook.here.service.impl;

import fun.oook.here.entity.shiro.UserInfo;
import fun.oook.here.repository.UserInfoRepository;
import fun.oook.here.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Joey
 * @version 1.0
 * @since 2019/3/13 23:20
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;


    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }
}
