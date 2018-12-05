package fun.oook.here.repository.redis;

import fun.oook.here.entity.Position;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Joey
 * @date 2018-12-04
 * @since 1.0
 */
@Configuration
@EnableRedisRepositories
public class RedisRepositoryConfig {


    /**
     * create a new Lettuce connection factory
     * <p>
     * https://docs.spring.io/spring-data/redis/docs/2.1.3.RELEASE/reference/html/#redis.repositories
     *
     * @return Lettuce connection factory
     */
//    @Bean(name = "redisConnectionFactory")
//    public LettuceConnectionFactory redisConnectionFactory() {
//
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("127.0.0.1", 6379);
//        config.setPassword("");
//
//        return new LettuceConnectionFactory();
//    }
    @Bean
    public RedisTemplate<String, Position> positionRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Position> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());

        return template;
    }

    @Bean
    public RedisTemplate<String, String> geoRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());

        return template;
    }
}