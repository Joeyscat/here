package fun.oook.here.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = "/")
    public String index(){

        LOGGER.info(".............................");

        return "index";
    }

    @GetMapping(value = "/key.html")
    public String key(){

        LOGGER.info(".............................");

        return "key";
    }
}