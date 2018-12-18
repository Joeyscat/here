package fun.oook.here.web.arg;

import fun.oook.here.common.HereException;
import fun.oook.here.entity.User;
import fun.oook.here.repository.redis.UserRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Joey
 * @version 1.0
 * @since 2018/12/18 18:19
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserRedisRepository userRedisRepository;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {

        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

        String token = request.getHeader("token");

        User user = userRedisRepository.findById(token);

        if (user == null) {
            user = request.getSession().getAttribute("online") != null ? (User) request.getSession().getAttribute("online") : null;
            if (user == null) {
                throw new HereException("EEEEEEE", "授权过期或授权失败");
            }
        }

        return user;
    }
}