package fun.oook.here.service;

import fun.oook.here.AbstractTestCase;
import fun.oook.here.model.Position;
import org.b3log.latke.service.ServiceException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


//@Test(suiteName = "service")
public class PositionServiceTest extends AbstractTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionServiceTest.class);

    /**
     * Init.
     *
     * @throws Exception exception
     */
//    @Test
    public void init() throws Exception {
        super.init();
    }

//    @Test(dependsOnMethods = "init")
    public void addPosition() throws ServiceException {

        final PositionService positionService = getPositionService();

        final JSONObject requestJSONObject = new JSONObject();
        final JSONObject position = new JSONObject();
        requestJSONObject.put(Position.POSITION, position);

        position.put(Position.POSITION_ADDRESS, "广州");
        position.put(Position.POSITION_TITLE, "公司");
        position.put(Position.POSITION_LNG, 113.38099);
        position.put(Position.POSITION_LAT, 23.15);

        final String positionId = positionService.addPosition(requestJSONObject);

        Assert.assertNotNull(positionId);
    }

//    @Test(dependsOnMethods = "init")
    public void getPositions() throws ServiceException {

        final PositionService positionService = getPositionService();

        final List<JSONObject> positions = positionService.getPositionsRandomly(5);

        Assert.assertNotNull(positions);

        for (JSONObject position : positions) {
            LOGGER.info(String.valueOf(positions));
        }
    }
}
