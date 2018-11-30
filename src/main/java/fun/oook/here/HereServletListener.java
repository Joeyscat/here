package fun.oook.here;

import org.b3log.latke.Latkes;
import org.b3log.latke.servlet.AbstractServletListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpSessionEvent;

/**
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
public class HereServletListener extends AbstractServletListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(HereServletListener.class);

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
        LOGGER.debug("FROM: {}", servletRequestEvent.getServletRequest().getRemoteHost());
    }

    @Override
    public void requestDestroyed(final ServletRequestEvent servletRequestEvent) {
        super.requestDestroyed(servletRequestEvent);
    }
}