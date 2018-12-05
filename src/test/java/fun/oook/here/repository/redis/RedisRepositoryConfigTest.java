package fun.oook.here.repository.redis;

import fun.oook.here.entity.Position;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisRepositoryConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRepositoryConfigTest.class);

    @Autowired
    private RedisTemplate<String, Position> positionRedisTemplate;

    @Autowired
    private RedisTemplate<String, String> geoRedisTemplate;


    @Test
    @Ignore
    public void testSavePosition() throws NullPointerException {
        Position position = new Position();
        position.setCreatedBy("Joey");
        position.setName("Home");

        positionRedisTemplate.opsForValue().set(position.getCreatedBy(), position);

        Position joey = positionRedisTemplate.opsForValue().get("Joey");
        Assert.assertNotNull(joey);

        LOGGER.info("Joey: {}", joey);
        // Position部分自断由BaseEntity继承而来,BaseEntity必须实现序列化
        LOGGER.info("createBy: {}", joey.getCreatedBy());

        Assert.assertEquals("Home", positionRedisTemplate.opsForValue().get("Joey").getName());


    }

    @Test
    @Ignore
    public void testGeo() throws NullPointerException {

        Point point = new Point(2L, 3L);
        String locationName = "Home";

        geoRedisTemplate.boundGeoOps("location").add(new RedisGeoCommands.GeoLocation<>(locationName, point));

        List<Point> points = geoRedisTemplate.boundGeoOps("location").position("Home");


        LOGGER.info("points is null {}", points == null);
        LOGGER.info("P {}", points.get(0));
    }

}