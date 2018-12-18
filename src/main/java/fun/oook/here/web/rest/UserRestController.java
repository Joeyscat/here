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
 * @date 2018-12-15
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/rest/user")
public class UserRestController extends AbstractRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    // TODO 18-12-15 22:15 user login

    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public RestResponse<JSONObject> login(@RequestBody User user, HttpSession session) {

        return exceptionHandler(() -> {
            LOGGER.info("Logging {}", user);

            User login = userService.login(user.getEmail(), user.getPassword());
            session.setAttribute("online", login);

            return new RestResponse<JSONObject>().normalRestResponse();
        });
    }
    // TODO 18-12-15 22:15 user logout
    // TODO 18-12-15 22:15 user register
    // TODO 18-12-15 22:15 user unregister

}