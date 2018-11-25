package fun.oook.here.processor;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.b3log.latke.servlet.AbstractServletListener;
import org.b3log.latke.servlet.HTTPRequestContext;
import org.b3log.latke.servlet.renderer.AbstractFreeMarkerRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.util.TimeZone;

/**
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
public class MapFreeMarkerRenderer extends AbstractFreeMarkerRenderer {

    private static final Logger LOG = LoggerFactory.getLogger(MapFreeMarkerRenderer.class);

    public static final Configuration SKIN;

    static {
        final ServletContext servletContext = AbstractServletListener.getServletContext();
        SKIN = new Configuration(Configuration.VERSION_2_3_28);
        SKIN.setDefaultEncoding("utf-8");
        SKIN.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        SKIN.setServletContextForTemplateLoading(servletContext, "skin/classic");
        SKIN.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        SKIN.setLogTemplateExceptions(false);
    }

    /**
     * Invoked before render.
     *
     * @param context the specified context
     * @throws Exception exception
     */
    @Override
    protected void beforeRender(HTTPRequestContext context) throws Exception {
        LOG.info("beforeRender");
    }

    /**
     * Invoked after render.
     *
     * @param context the specified context
     * @throws Exception exception
     */
    @Override
    protected void afterRender(HTTPRequestContext context) throws Exception {
        LOG.info("afterRender");
    }

    /**
     * Gets a template.
     *
     * @return template, returns {@code null} if not found
     */
    @Override
    protected Template getTemplate() {
        try {
            return SKIN.getTemplate(getTemplateName());
        } catch (final Exception e) {
            LOG.error("Get template [{}] failed {}", getTemplate(), e);
        }
        return null;
    }
}