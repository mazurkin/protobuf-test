package org.test;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;
import org.test.proto.BeanProto;

public class ProtoTest {

    private static final int TRIALS = 1_000_000;

    @Test
    public void testWriting() throws Exception {
        long marker = System.nanoTime();

        BeanProto.Bean bean = BeanProto.Bean.newBuilder()
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
            BeanProto.Bean.newBuilder()
                    .setValue001("aaaa")
                    .setValue002(1000)
                    .build();
        }

        System.out.printf("Creating: %.6f ns/op\n", 1.0 * (System.nanoTime() - marker) / TRIALS);
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
