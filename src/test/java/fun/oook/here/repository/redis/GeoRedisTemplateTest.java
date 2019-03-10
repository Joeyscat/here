package fun.oook.here.repository.redis;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Joey
 * @date 2018-12-10
 * @since 1.0
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class GeoRedisTemplateTest {

    @Autowired
    private RedisTemplate<String, String> geoRedisTemplate;


    private static final String cityGeoKey = "cityGeo";

    @Test
    @Ignore
    public void testAdd(){
        Long addedNum = geoRedisTemplate.opsForGeo()
                .add(cityGeoKey,new Point(116.405285,39.904989),"北京");
        System.out.println(addedNum);
    }

    @Test
    @Ignore
    public void testGeoGet(){
        List<Point> points = geoRedisTemplate.opsForGeo().position(cityGeoKey,"北京","上海","深圳");
        System.out.println(points);
    }

    @Test
    @Ignore
    public void testDist(){
        Distance distance = geoRedisTemplate.opsForGeo()
                .distance(cityGeoKey,"北京","上海", RedisGeoCommands.DistanceUnit.KILOMETERS);
        System.out.println(distance);
    }

    @Test
    @Ignore
    public void testNearByXY(){
        //longitude,latitude
        Circle circle = new Circle(116.405285,39.904989, Metrics.KILOMETERS.getMultiplier());
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoRedisTemplate.opsForGeo()
                .radius(cityGeoKey,circle,args);
        System.out.println(results);
    }

    @Test
    @Ignore
    public void testNearByPlace(){
        Distance distance = new Distance(5,Metrics.KILOMETERS);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>>  results = geoRedisTemplate.opsForGeo()
                .radius(cityGeoKey,"北京",distance,args);
        System.out.println(results);
    }

    @Test
    @Ignore
    public void testGeoHash(){
        List<String> results = geoRedisTemplate.opsForGeo()
                .hash(cityGeoKey,"北京","上海","深圳");
        System.out.println(results);
    }
}