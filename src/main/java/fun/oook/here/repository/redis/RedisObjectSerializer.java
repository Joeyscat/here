package fun.oook.here.repository.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author Joey
 * @date 2018-12-05
 * @since 1.0
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    static final byte[] EMPTY_ARRAY = new byte[0];

    @Override
    public byte[] serialize(Object object) throws SerializationException {

        if (object == null) {
            return EMPTY_ARRAY;
        }

        try {
            return serializer.convert(object);
        } catch (Exception e) {
            return EMPTY_ARRAY;
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {

        if (isEmpty(bytes)) {
            return null;
        }

        try {
            return deserializer.convert(bytes);
        } catch (Exception e) {
            throw new SerializationException("Cannot deserialize", e);
        }
    }

    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
}