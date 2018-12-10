package fun.oook.here.repository.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GeoRedisRepositoryTest {

    @Autowired
    private GeoRedisRepository geoRedisRepository;

    @Test
    public void testGeoAdd() {

        Long res = geoRedisRepository.geoAdd(GeoRedisRepository.GEO_KEY_POSITION, "joey", 23, 34);
        System.out.println(res);
    }

    @Test
    public void testGeoGet() {

        List<Point> points = geoRedisRepository.geoGet(GeoRedisRepository.GEO_KEY_POSITION, "joey");

        System.out.println(points);
    }

    @Test
    public void testGetNearByWithPlace() {

        Distance distance = new Distance(5, Metrics.KILOMETERS);

        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults  = geoRedisRepository.getNearByWithPlace(
                GeoRedisRepository.GEO_KEY_POSITION, "joey", distance, (long) 5);

        System.out.println(geoResults);
    }
}