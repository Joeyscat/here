package fun.oook.here.processor;

import fun.oook.here.model.Position;
import fun.oook.here.service.PositionService;
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

import java.util.List;

/**
 * PositionProcessor
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
     * Create a position
     *
     * @param context           context
     * @param requestJSONObject requestJSONObject
     */
    @RequestProcessing(value = "/position/add", method = HTTPRequestMethod.POST)
    public void createPosition(final HTTPRequestContext context, final JSONObject requestJSONObject) {
        final JSONRenderer renderer = new JSONRenderer();
        final JSONObject response = new JSONObject();
        final JSONObject position = requestJSONObject.optJSONObject(Position.POSITION);

        renderer.setJSONObject(response);
        context.setRenderer(renderer);

        if (position == null) {
            response.put("success", false);
            response.put("msg", "position not found");
            return;
        }
        try {
            String res = positionService.addPosition(requestJSONObject);

            response.put("success", true);
            response.put("msg", res);
        } catch (final ServiceException e) {
            LOGGER.error(e.getMessage());
            response.put("msg", "add position failure");
            response.put("success", false);
        }
    }

    /**
     * Delete the position by the specified request
     *
     * @param context    the specified http request context
     * @param positionId the specified http request positionId
     */
    @RequestProcessing(value = "/position/{positionId}", method = {HTTPRequestMethod.DELETE, HTTPRequestMethod.POST})
    public void deletePosition(final HTTPRequestContext context, final String positionId) {
        final JSONRenderer renderer = new JSONRenderer();
        context.setRenderer(renderer);
        final JSONObject response = new JSONObject();
        renderer.setJSONObject(response);

        try {
            positionService.removePosition(positionId);

            response.put("success", true);
            response.put("msg", "delete position successfully");
        } catch (final Exception e) {
            LOGGER.error(e.getMessage());
            response.put("msg", "delete position failure");
            response.put("success", false);
        }
    }

    /**
     * Get a positions
     *
     * @param context context
     */
    @RequestProcessing(value = "/position/{articleId}", method = HTTPRequestMethod.GET)
    public void getPosition(final HTTPRequestContext context, final String positionId) {

        JSONRenderer renderer = new JSONRenderer();
        JSONObject response = new JSONObject();
        response.put("uri", context.getRequest().getRequestURI());

        try {
            JSONObject position = positionService.getPosition(positionId);
            response.put("data", position);
            response.put("success", true);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            response.put("success", false);
        }

        renderer.setJSONObject(response);
        context.setRenderer(renderer);
    }

    /**
     * Get random positions
     *
     * @param context context
     */
    @RequestProcessing(value = "/position/example/random", method = HTTPRequestMethod.GET)
    public void randomPosition(final HTTPRequestContext context) {

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
}