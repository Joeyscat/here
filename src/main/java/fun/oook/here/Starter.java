package fun.oook.here;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

/**
 * @author Joey
 * @version 1.0
 * @since 2018-11-25
 */
public class Starter {

    public static void main(String[] args) {
        // Real path including maven sub folder
        String classesPath = ClassLoader.getSystemResource("").getPath();
        // POM structure in dev env
        String webappDirLocation = classesPath.replace("target/classes/", "src/main/webapp/");
        final File file = new File(webappDirLocation);
        if (!file.exists()) {
            // production environment
            webappDirLocation = ".";
        }

        final Server server = new Server(8080);
        final WebAppContext root = new WebAppContext();
        // Use parent class loader
        root.setParentLoaderPriority(true);
        root.setContextPath("/");
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);
        server.setHandler(root);

        try {
            server.start();
        } catch (final Exception e) {
            e.printStackTrace();

            System.exit(-1);
        }
    }
}