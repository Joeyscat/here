package fun.oook.here.processor;

import fun.oook.here.model.Position;
import fun.oook.here.service.PositionService;
import org.b3log.latke.Keys;
import org.b3log.latke.ioc.Inject;
import org.b3log.latke.service.ServiceException;
import org.b3log.latke.servlet.HTTPRequestContext;
import org.b3log.latke.servlet.HTTPRequestMethod;
import org.b3log.latke.servlet.annotation.RequestProcessing;
import org.b3log.latke.servlet.annotation.RequestProcessor;
import org.b3log.latke.servlet.renderer.JSONRenderer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理position相关请求
 *
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
@RequestProcessor()
public class PositionProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(PositionProcessor.class);

    @Inject
    private PositionService positionService;

    /**
     * Get random positions
     *
     * @param context context
     */
    @RequestProcessing(value = "/position/example/joey", method = HTTPRequestMethod.GET)
    public void randomPosition(final HTTPRequestContext context) {
        LOGGER.info(context.getRequest().getRequestURI());

        JSONRenderer renderer = new JSONRenderer();
        JSONObject response = new JSONObject();
        response.put("uri", context.getRequest().getRequestURI());

        try {
            List<JSONObject> positions = positionService.getPositionsRandomly(5);
            response.put("data", positions);
            response.put("success", true);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            response.put("success", false);
        }

        renderer.setJSONObject(response);
        context.setRenderer(renderer);
    }

    /**
     * Add position
     *
     * @param context           context
     * @param requestJSONObject requestJSONObject
     */
    @RequestProcessing(value = "/position/add", method = HTTPRequestMethod.POST)
    public void addPosition(final HTTPRequestContext context, final JSONObject requestJSONObject) {
        final JSONRenderer renderer = new JSONRenderer();
        final JSONObject response = new JSONObject();
        final JSONObject position = requestJSONObject.optJSONObject(Position.POSITION);

        renderer.setJSONObject(response);
        context.setRenderer(renderer);

        if (position == null) {
            response.put("msg", "position not found");
            return;
        }
        try {
            String res = positionService.addPosition(requestJSONObject);

            response.put("success", true);
            response.put("data", res);
        } catch (final ServiceException e) {
            LOGGER.error(e.getMessage());
            response.put("data", "something wrong");
            response.put("success", false);
        }

    }
}