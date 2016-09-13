package org.test;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;
import org.test.beans.MonsterEntity;
import org.test.proto.MonsterProto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MonsterTest {

    private static final int TRIALS = 1_000_000;

    @Test
    public void testWriting() throws Exception {
        long marker = System.nanoTime();

        MonsterProto.Monster bean = MonsterProto.Monster.newBuilder()
                .setValue001("aaaa")
                .setValue002(1000)
                .build();

        for (int i = 0; i < TRIALS; i++) {
            bean.writeTo(NullStream.INSTANCE);
        }

        System.out.printf("Writing: %.6f ns/op\n", 1.0 * (System.nanoTime() - marker) / TRIALS);
    }

    @Test
    public void testCreating() throws Exception {
        long marker = System.nanoTime();

        for (int i = 0; i < TRIALS; i++) {
            MonsterProto.Monster.newBuilder()
                    .setValue001("aaaa")
                    .setValue002(1000)
                    .build();
        }

        System.out.printf("Creating: %.6f ns/op\n", 1.0 * (System.nanoTime() - marker) / TRIALS);
    }


    @Test
    public void testMutate() throws Exception {
        long marker = System.nanoTime();

        MonsterProto.Monster monster = MonsterProto.Monster.newBuilder()
                .setValue001("aaaa")
                .setValue002(1000)
                .build();

        for (int i = 0; i < TRIALS; i++) {
            monster.toBuilder()
                    .setValue002(1001);
        }

        System.out.printf("Mutate: %.6f ns/op\n", 1.0 * (System.nanoTime() - marker) / TRIALS);
    }

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

            mapper.writeValue(NullStream.INSTANCE, entity);
        }

        System.out.printf("Total: %.6f ns/op\n", 1.0 * (System.nanoTime() - marker) / TRIALS);
    }

    private static final class NullStream extends OutputStream {

        private static final OutputStream INSTANCE = new NullStream();

        @Override
        public void write(int b) throws IOException {
            //
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            //
        }

        @Override
        public void write(byte[] b) throws IOException {
            //
        }
    }
}
