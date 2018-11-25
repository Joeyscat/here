package fun.oook.here;

import org.b3log.latke.Latkes;
import org.b3log.latke.logging.Logger;
import org.b3log.latke.servlet.AbstractServletListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpSessionEvent;

/**
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
public class HereServletListener extends AbstractServletListener {

    private static final Logger LOGGER = Logger.getLogger(HereServletListener.class);

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        Latkes.setScanPath(HereServletListener.class.getPackage().getName());
        super.contextInitialized(servletContextEvent);

        LOGGER.info("Initialized the context");
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {
        super.contextDestroyed(servletContextEvent);

        LOGGER.info("Destroyed the context");
    }


    @Override
    public void sessionCreated(final HttpSessionEvent httpSessionEvent) {
        LOGGER.info("Session created");
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent httpSessionEvent) {
        super.sessionDestroyed(httpSessionEvent);

        LOGGER.info("Session Destroyed");
    }

    @Override
    public void requestInitialized(final ServletRequestEvent servletRequestEvent) {
        LOGGER.info("Request initialized");
    }

    @Override
    public void requestDestroyed(final ServletRequestEvent servletRequestEvent) {
        super.requestDestroyed(servletRequestEvent);

        LOGGER.info("Request destroyed");
    }
}