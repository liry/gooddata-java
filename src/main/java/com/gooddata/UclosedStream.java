package com.gooddata;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class for static analysis tool if it's able to detect unclosed stream.
 */
public class UclosedStream {
    public void something() throws IOException {
        final InputStream is = new FileInputStream("test");
        try {
            System.out.println("IS: " + is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
    }

    public void test() {

    }
}
