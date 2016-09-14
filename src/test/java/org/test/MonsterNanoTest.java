package org.test;

import org.junit.Assert;
import org.junit.Test;
import org.test.proto.nano.MonsterProto;

import com.google.protobuf.nano.MessageNano;


public class MonsterNanoTest {

    private static final int TRIALS = 1_000_000;

    @Test
    public void testWriting() throws Exception {
        long marker = System.nanoTime();

        MonsterProto.Monster bean = new MonsterProto.Monster();
        bean.value001 = "aaaa";
        bean.value002 = 1000;

        for (int i = 0; i < TRIALS; i++) {
            byte[] data = MessageNano.toByteArray(bean);
            Assert.assertEquals(9, data.length);
        }

        System.out.printf("Writing: %.6f ns/op\n", 1.0 * (System.nanoTime() - marker) / TRIALS);
    }

}
