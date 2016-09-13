package org.test;

import org.junit.Test;
import org.test.proto.MonsterProto;
import org.test.util.NullOutputStream;

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
            bean.writeTo(NullOutputStream.INSTANCE);
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

}
