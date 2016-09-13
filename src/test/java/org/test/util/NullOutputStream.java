package org.test.util;

import java.io.IOException;
import java.io.OutputStream;

public final class NullOutputStream extends OutputStream {

    public static final OutputStream INSTANCE = new NullOutputStream();

    private NullOutputStream() {
    }

    @Override
    public void write(byte[] b) throws IOException {
        //
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        //
    }

    @Override
    public void write(int b) throws IOException {
        //
    }
}
