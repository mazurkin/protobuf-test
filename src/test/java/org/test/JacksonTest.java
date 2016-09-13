package org.test;

import org.junit.Test;
import org.test.beans.MonsterEntity;
import org.test.util.NullOutputStream;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonTest {

    private static final int TRIALS = 1_000_000;

    @Test
    public void testJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        // Serialization options
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.disable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
        // Deserialization options
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

        long marker = System.nanoTime();

        for (int i = 0; i < TRIALS; i++) {
            MonsterEntity entity = new MonsterEntity();
            entity.value001 = "aaaa";
            entity.value002 = 1000;

            mapper.writeValue(NullOutputStream.INSTANCE, entity);
        }

        System.out.printf("Total: %.6f ns/op\n", 1.0 * (System.nanoTime() - marker) / TRIALS);
    }
}
