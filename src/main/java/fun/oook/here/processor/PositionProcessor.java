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

    @RequestProcessing(value = "/position/example/joey", method = HTTPRequestMethod.GET)
    public void position(final HTTPRequestContext context) {
        LOGGER.info(context.getRequest().getRequestURI());

        JSONRenderer renderer = new JSONRenderer();
        JSONObject json = new JSONObject();
        JSONObject po = new JSONObject();
        po.put("lng", 113.38099);
        po.put("lat", 23.15);

        json.put("success", true);
        json.put("data", po);
        json.put("uri", context.getRequest().getRequestURI());

        renderer.setJSONObject(json);
        context.setRenderer(renderer);
    }

    @RequestProcessing
    public void addPosition(final HTTPRequestContext context, final JSONObject requestJSONObject) {
        final JSONRenderer renderer = new JSONRenderer();
        context.setRenderer(renderer);
        final JSONObject ret = new JSONObject();

        try {
            final JSONObject position = requestJSONObject.optJSONObject(Position.POSITION);
            positionService.addPosition(requestJSONObject);
        } catch (final ServiceException e) {

        }
    }
}