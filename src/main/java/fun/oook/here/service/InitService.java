package fun.oook.here.service;

import fun.oook.here.repository.PositionRepository;
import org.b3log.latke.Latkes;
import org.b3log.latke.ioc.Inject;
import org.b3log.latke.logging.Level;
import org.b3log.latke.repository.Transaction;
import org.b3log.latke.repository.jdbc.util.Connections;
import org.b3log.latke.repository.jdbc.util.JdbcRepositories;
import org.b3log.latke.service.ServiceException;
import org.b3log.latke.service.annotation.Service;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
@Service
public class InitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitService.class);


    /**
     * Maximum count of initialization.
     */
    private static final int MAX_RETRIES_CNT = 3;

    /**
     * Position repository
     */
    @Inject
    private PositionRepository positionRepository;

    /**
     * Flag of init status.
     */
    private static boolean inited;

    /**
     * Flag of printed init prompt.
     */
    private static boolean printedInitMsg;

    /**
     * Determines Solo had been initialized.
     *
     * @return {@code true} if it had been initialized, {@code false} otherwise
     */
    public boolean isInited() {
        if (inited) {
            return true;
        }

        try (final Connection connection = Connections.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(1) AS `c` FROM `" + positionRepository.getName() + "`");
            final ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            final int c = resultSet.getInt("c");
            inited = 0 < c;

            return inited;
        } catch (final Exception e) {
            if (!printedInitMsg) {
                LOGGER.info("Here has not been initialized, please open your browser and visit [" + Latkes.getServePath() + "] to init Here");
            }
            printedInitMsg = true;

            return false;
        }
    }

    public void init(final JSONObject requestJSONObject) throws ServiceException {
        if (isInited()) {
            return;
        }

        LOGGER.info("Here is running with database [{0}], creates all tables", Latkes.getRuntimeDatabase());

        if (Latkes.RuntimeDatabase.H2 == Latkes.getRuntimeDatabase()) {
            String dataDir = Latkes.getLocalProperty("jdbc.URL");
            dataDir = dataDir.replace("~", System.getProperty("user.home"));
            LOGGER.info("Your DATA will be stored in directory [" + dataDir + "], "
                        + "please pay more attention on it!");
        }

        final List<JdbcRepositories.CreateTableResult> createTableResults = JdbcRepositories.initAllTables();
        for (final JdbcRepositories.CreateTableResult createTableResult : createTableResults) {
            LOGGER.info("Create table result [tableName={0}, isSuccess={1}]",
                    createTableResult.getName(), createTableResult.isSuccess());
        }
    }

    /**
     * Sets the position repository with the specified article repository.
     *
     * @param positionRepository the specified position repository
     */
    public void setPositionRepository(final PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
}