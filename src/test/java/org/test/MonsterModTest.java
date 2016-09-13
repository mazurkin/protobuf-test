package org.test;

import org.junit.Test;
import org.test.proto.MonsterProtoMod;
import org.test.util.NullOutputStream;

public class MonsterModTest {

    private static final int TRIALS = 1_000_000;

    @Test
    public void test() throws Exception {
        long marker = System.nanoTime();

        for (int i = 0; i < TRIALS; i++) {
            MonsterProtoMod.Monster.newBuilder()
                    .setValue001("aaaa")
                    .setValue002(1000)
                    .build()
                    .writeTo(NullOutputStream.INSTANCE);
        }

        System.out.printf("Total: %.6f ns/op\n", 1.0 * (System.nanoTime() - marker) / TRIALS);
    }

}
