package fun.oook.here.web.rest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.oook.here.entity.Position;
import fun.oook.here.entity.User;
import fun.oook.here.service.PositionService;
import fun.oook.here.entity.RestResponse;
import fun.oook.here.web.anno.UserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/rest/position")
public class PositionRestController extends AbstractRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionRestController.class);

    @Autowired
    private PositionService positionService;


    @PostMapping(value = "/random", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public RestResponse<JSONArray> random(@RequestParam(defaultValue = "10") int fetchSize, @UserAuth User user) {

        return exceptionHandler(() -> {

            LOGGER.info("Getting random positions {}", fetchSize);
            LOGGER.debug("USER: {}", user);
            JSONArray positions = positionService.listPositionsRandom(fetchSize);

            return new RestResponse<JSONArray>().normalRestResponse(positions);
        });
    }

    @PostMapping(value = "/nearby", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public RestResponse<JSONArray> nearby(@RequestBody Position position, @RequestParam(defaultValue = "10") int fetchSize) {

        return exceptionHandler(() -> {
            LOGGER.info("Getting {} positions near {}", fetchSize, position);

            JSONArray positions = positionService.listPositionsNearby(position, fetchSize);

            return new RestResponse<JSONArray>().normalRestResponse(positions);
        });
    }

    @PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public RestResponse<JSONObject> savePosition(@RequestBody Position position, @UserAuth User user) {

        return exceptionHandler(() -> {
            position.setCreatedBy(String.valueOf(user.getId()));
            LOGGER.info("Saving position {}", position);

            String newPositionId = positionService.savePosition(position);
            JSONObject result = new JSONObject();
            result.put("positionId", newPositionId);

            return new RestResponse<JSONObject>().normalRestResponse(result);
        });
    }
}