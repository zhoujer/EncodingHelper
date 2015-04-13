package edu.carleton.oshern;
import java.io.*;
import java.util.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nate on 4/12/15.
 */
public class EncodingHelperTest {

    @Test
    public void testCollectorShouldCreateCorrectArray() throws Exception {
        EncodingHelper thing = new EncodingHelper();
        String[] b = new String[]{"this", "that", "those", "them", "these",
                "more", "yay"};
        int j = 5;
        ArrayList<String> correct = new ArrayList<>(2);
        correct.add("more");
        correct.add("yay");
        org.junit.Assert.assertEquals("Error- array returned was not " +
                "array expected", correct, thing.collector(b, j));
    }

    @Test
    public void testDoubleArrayGeneratorShouldReturnCorrect2DArray() throws
            Exception {
        EncodingHelper thing = new EncodingHelper();
        byte[] b = {(byte) 0x24, (byte) 0xc2, (byte) 0xa2,
                (byte) 0xe2, (byte) 0x82, (byte) 0xac};
        byte[][] correct = new byte[6][];
        correct[0] = new byte[]{(byte) 0x24};
        correct[1] = new byte[]{(byte) 0xc2, (byte) 0xa2};
        correct[2] = new byte[]{(byte) 0xe2, (byte) 0x82, (byte) 0xac};
        org.junit.Assert.assertArrayEquals("Error- array returned was not " +
                "array expected", correct, thing.doubleArrayGenerator(b));

    }


}