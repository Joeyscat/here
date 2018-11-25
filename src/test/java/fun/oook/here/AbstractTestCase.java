package fun.oook.here;

import fun.oook.here.model.Position;
import fun.oook.here.repository.PositionRepository;
import fun.oook.here.service.InitService;
import fun.oook.here.service.PositionService;
import org.b3log.latke.Latkes;
import org.b3log.latke.ioc.BeanManager;
import org.b3log.latke.ioc.Discoverer;
import org.b3log.latke.model.User;
import org.b3log.latke.repository.jdbc.util.Connections;
import org.b3log.latke.repository.jdbc.util.JdbcRepositories;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.Connection;
import java.util.Collection;
import java.util.Locale;

/**
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
public abstract class AbstractTestCase {

    private BeanManager beanManager;

    /**
     * Before class.
     * <ol>
     * <li>Initializes Latke runtime</li>
     * <li>Instantiates repositories</li>
     * </ol>
     *
     * @throws Exception exception
     */
    @BeforeClass
    public void beforeClass() throws Exception {
        Latkes.init();
        Latkes.setLocale(Locale.SIMPLIFIED_CHINESE);

        final Collection<Class<?>> classes = Discoverer.discover("fun.oook.here");
        BeanManager.start(classes);
        beanManager = BeanManager.getInstance();

        final Connection connection = Connections.getConnection();
        connection.createStatement().execute("DROP ALL OBJECTS");
        connection.close();

        JdbcRepositories.initAllTables();
    }

    /**
     * After class.
     * <ul>
     * <li>Clears all caches</li>
     * </ul>
     */
    @AfterClass
    public void afterClass() {

    }


    /**
     * Init here in test.
     *
     * @throws Exception exception
     */
    public void init() throws Exception {
        final InitService initService = getInitService();

        initService.init();
    }

    /**
     * Gets page repository.
     *
     * @return page repository
     */
    public PositionRepository getPositionRepository() {
        return beanManager.getReference(PositionRepository.class);
    }

    /**
     * Gets page repository.
     *
     * @return page repository
     */
    public PositionService getPositionService() {
        return beanManager.getReference(PositionService.class);
    }


    /**
     * Gets initialization service.
     *
     * @return initialization service
     */
    public InitService getInitService() {
        return beanManager.getReference(InitService.class);
    }

}