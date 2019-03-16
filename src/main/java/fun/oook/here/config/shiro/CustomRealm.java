package fun.oook.here.config.shiro;

import fun.oook.here.entity.shiro.SysPermission;
import fun.oook.here.entity.shiro.SysRole;
import fun.oook.here.entity.shiro.UserInfo;
import fun.oook.here.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Joey
 * @version 1.0
 * @since 2019/3/11 21:09
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 授权
     *
     * @param principalCollection p
     * @return auth info
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        log.info("用户授权: {}", userInfo.getUsername());

        for (SysRole role : userInfo.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            for (SysPermission permission : role.getPermissions()) {
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }

        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken token
     * @return auth info
     * @throws AuthenticationException ae
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 获取用户输入的账号
        String username = token.getUsername();
        log.info("身份认证: {}", username);
        UserInfo userInfo = userInfoService.findByUsername(username);

        if (userInfo == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),
                getName()
        );
    }
}
