package fun.oook.here.web.arg;

import fun.oook.here.common.HereException;
import fun.oook.here.entity.User;
import fun.oook.here.service.UserService;
import fun.oook.here.web.anno.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * {@link fun.oook.here.config.ClientResourcesConfig}
 *
 * @author Joey
 * @version 1.0
 * @since 2018/12/18 18:19
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    // TODO 18-12-22 23:00 how to inject userService with ClientResourcesConfig

    @Autowired
    private UserService userService;


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(UserAuth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {

        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

        User user = null;
        // 目前token检验为实现
        if (request != null && request.getHeader("token") != null) {
            String token = request.getHeader("token");

            user = userService.tokenVerify(token);
        }

        if (user == null && request != null) {
            // TODO 18-12-18 22:34 NOT safe
            user = request.getSession().getAttribute("here_auth") != null ? (User) request.getSession().getAttribute("here_auth") : null;
        }

        if (user == null || !userService.auth(user)) {
            throw new HereException("EEEEEEE", "授权过期或授权失败");
        }

        return user;
    }
}