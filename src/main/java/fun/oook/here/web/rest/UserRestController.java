package fun.oook.here.web.rest;

import com.alibaba.fastjson.JSONObject;
import fun.oook.here.entity.RestResponse;
import fun.oook.here.entity.User;
import fun.oook.here.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Joey
 * @date 2018-12-15d
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/rest/user")
public class UserRestController extends AbstractRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public RestResponse<JSONObject> login(@RequestBody User user, HttpSession session) {

        return exceptionHandler(() -> {
            LOGGER.info("Logging {}", user);

            User login = userService.login(user.getEmail(), user.getPassword());
            session.setAttribute("here_auth", login);

            return new RestResponse<JSONObject>().normalRestResponse();
        });
    }

    /**
     * 游客模式
     *
     * @param session session
     * @return visitor
     */
    @PostMapping(value = "/visitor", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public RestResponse<JSONObject> visitor(HttpSession session) {

        return exceptionHandler(() -> {
            // TODO 18-12-22 21:39 visitor 游客模式下使用session缓存信息

            User visitor = userService.visitor();

            session.setAttribute("here_auth", visitor);
            LOGGER.info("visitor {}", visitor);

            return new RestResponse<JSONObject>().normalRestResponse();
        });
    }


    // TODO 18-12-15 22:15 user logout
    // TODO 18-12-15 22:15 user register

    @PostMapping(value = "/register", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public RestResponse<JSONObject> register(@RequestBody User user) {

        return exceptionHandler(() -> {
            LOGGER.info("Logging {}", user);

            User login = userService.register(user.getEmail(), user.getPassword());

            return new RestResponse<JSONObject>().normalRestResponse();
        });
    }
    // TODO 18-12-15 22:15 user unregister

}