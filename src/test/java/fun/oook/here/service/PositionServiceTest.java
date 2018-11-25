package fun.oook.here.service;

import fun.oook.here.AbstractTestCase;
import fun.oook.here.model.Position;
import org.b3log.latke.service.ServiceException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


@Test(suiteName = "service")
public class PositionServiceTest extends AbstractTestCase {

    /**
     * Init.
     *
     * @throws Exception exception
     */
    @Test
    public void init() throws Exception {
        super.init();
    }

    @Test(dependsOnMethods = "init")
    public void addPosition() throws ServiceException {

        final PositionService positionService = getPositionService();

        final JSONObject requestJSONObject = new JSONObject();
        final JSONObject position = new JSONObject();
        requestJSONObject.put(Position.POSITION, position);

        position.put(Position.POSITION_ABSTRACT,"position-1");
        position.put(Position.POSITION_TITLE,"position-1");

        final String positionId = positionService.addPosition(requestJSONObject);

        Assert.assertNotNull(positionId);
    }
}
