package fun.oook.here.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Joey
 * @date 2018-12-15
 * @since 1.0
 */
@Controller
public class UserController {

    @GetMapping(value = "/login")
    public String login(){

        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout(){

        return "here";
    }

    @GetMapping(value = "/register")
    public String register(){

        return "register";
    }
}