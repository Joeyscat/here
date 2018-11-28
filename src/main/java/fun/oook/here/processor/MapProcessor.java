package fun.oook.here.processor;

import org.b3log.latke.logging.Level;
import org.b3log.latke.logging.Logger;
import org.b3log.latke.servlet.HTTPRequestContext;
import org.b3log.latke.servlet.HTTPRequestMethod;
import org.b3log.latke.servlet.annotation.RequestProcessing;
import org.b3log.latke.servlet.annotation.RequestProcessor;
import org.b3log.latke.servlet.renderer.AbstractFreeMarkerRenderer;
import org.b3log.latke.servlet.renderer.TextHTMLRenderer;
import org.b3log.latke.util.Requests;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
@RequestProcessor
public class MapProcessor {

    private static final Logger LOGGER = Logger.getLogger(MapProcessor.class);


    @RequestProcessing(value = "/here", method = HTTPRequestMethod.GET)
    public void index(final HTTPRequestContext context) {
        final AbstractFreeMarkerRenderer renderer = new MapFreeMarkerRenderer();
        context.setRenderer(renderer);
        renderer.setTemplateName("here.ftl");

        final Map<String, Object> dataModel = renderer.getDataModel();
        dataModel.put("position", "");

        Requests.log(context.getRequest(), Level.DEBUG, LOGGER);
    }


    @RequestProcessing(value = "/", method = HTTPRequestMethod.GET)
    public void map(final HTTPRequestContext context, HttpServletResponse response) {
        TextHTMLRenderer renderer = new TextHTMLRenderer();
        context.setRenderer(renderer);
        renderer.setContent("index.html");
    }


}