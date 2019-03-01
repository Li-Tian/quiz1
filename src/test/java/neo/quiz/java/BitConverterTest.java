package neo.quiz.java;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class BitConverterTest {

    static class DataPair {
        int value;
        byte[] code;
        public DataPair(int v, byte[] c) {
            value = v;
            code = c;
        }
    }

    DataPair[] data = {
            new DataPair(0, new byte[] {0}),
            new DataPair(1, new byte[] {1}),
            new DataPair(127, new byte[] {127}),
            new DataPair(128, new byte[] {-128, 1}),
            new DataPair(255, new byte[] {-1, 1}),
            new DataPair(256, new byte[] {-128, 2}),
            new DataPair(511, new byte[] {-1, 3}),
            new DataPair(512, new byte[] {-128, 4}),
            new DataPair(1024, new byte[] {-128, 8}),
            new DataPair(1280, new byte[] {-128, 10}),
            new DataPair(16383, new byte[] {-1, 127}),
            new DataPair(16384, new byte[] {-128, -128, 1}),
            new DataPair(65535, new byte[] {-1, -1, 3}),
            new DataPair(2097151, new byte[] {-1, -1, 127}),
            new DataPair(2097152, new byte[] {-128, -128, -128, 1}),
            new DataPair(268435455, new byte[] {-1, -1, -1, 127}),
            new DataPair(268435456, new byte[] {-128, -128, -128, -128, 1}),
            new DataPair(-1, new byte[] {-1, -1, -1, -1, 15}),
    };

    @Test
    public void testEncodeXXX() {
        for (DataPair dataPair : data) {
            assertArrayEquals(dataPair.code, BitConverter.encodeXXX(dataPair.value));
        }
    }

    @Test
    public void testDecodeXXX() {
        try {
            for (DataPair dataPair : data) {
                assertEquals(dataPair.value, BitConverter.decodeXXX(new ByteArrayInputStream(dataPair.code)));
            }
        } catch (IOException e) {
            fail();
        }
    }
}