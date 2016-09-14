package org.test;

import org.junit.Test;
import org.test.beans.MonsterPojo;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class StuffTest {

    private static final int TRIALS = 1_000_000;

    @Test
    public void test() throws Exception {
        Schema<MonsterPojo> schema = RuntimeSchema.getSchema(MonsterPojo.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);

        long marker = System.nanoTime();

        for (int i = 0; i < TRIALS; i++) {
            MonsterPojo entity = new MonsterPojo();
            entity.value001 = "aaaa";
            entity.value002 = 1000;

            byte[] data = ProtobufIOUtil.toByteArray(entity, schema, buffer);
            buffer.clear();
        }

        System.out.printf("Total: %.6f ns/op\n", 1.0 * (System.nanoTime() - marker) / TRIALS);
    }
}
