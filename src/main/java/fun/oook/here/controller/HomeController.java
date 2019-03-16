package fun.oook.here.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
@Slf4j
@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index() {

        return "here";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) {
        log.info("登录...");
        String exception = (String) request.getAttribute("shiroLoginFailure");
        log.info("exception: {}", exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "UnknownAccountException --> 账号不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "IncorrectCredentialsException --> 密码不正确";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = exception;
            }
            log.warn(msg);
        }
        map.put("msg", msg);
        return "/login";
    }

    @GetMapping(value = "/key.html")
    public String key() {

        return "key";
    }

    @GetMapping("/403")
    public String unauthorizedRole() {
        log.info("没有权限");
        return "403";
    }
}