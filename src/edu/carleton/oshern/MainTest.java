package edu.carleton.oshern;
import java.io.*;
import java.util.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nate on 4/12/15.
 */
public class MainTest {
    @Test
    public void testRunWithoutError() throws Exception {
        String[] args = {"-i","codepoint","-o","utf8","U+0012"};
        Main.main(args);
        //System.out.println(Integer.parseInt("a33", 16));
        //System.out.println(Arrays.toString("U234\\2345+abc".split("u|U|\\+|\\\\")));
        //System.out.println( Arrays.toString("wer wer wer ".split(" ")));

    }
    @Test
    public void testRunWithError() throws Exception {
        String[] args = {"-i","codepoint","-o","utf8","wrg80"};
        String[] args2 = {"-i","codepoint","-o","u8","U+0012"};
        String[] args3 = {"-i345","codepoint","-34","utf8","U+0012"};
        String[] args4 = {"-i","codint","-o","utf8","U+0012"};
        Main.main(args);
        //EncodingHelper.main(args2);
        //EncodingHelper.main(args3);
        //EncodingHelper.main(args4);
    }

    @Test
    public void testValidHExString() throws Exception {
        boolean empty = EncodingHelper.validHexString("");
        boolean valid = EncodingHelper.validHexString("0012");
        boolean wrong = EncodingHelper.validHexString("U");
        org.junit.Assert.assertTrue("Error - unexpected boolean", valid);
        org.junit.Assert.assertFalse("Error - unexpected boolean", empty);
        org.junit.Assert.assertFalse("Error - unexpected boolean", wrong);

    }
    @Test
    public void testValidInOut() throws Exception {
        boolean valid = EncodingHelper.validCommandInOut("codepoint", "utf8");
        boolean wrong1 = EncodingHelper.validCommandInOut("codepoint", "u8");
        boolean wrong2= EncodingHelper.validCommandInOut("codt","ut");
        org.junit.Assert.assertTrue("Error - unexpected boolean", valid);
        org.junit.Assert.assertFalse("Error - unexpected boolean", wrong1);
        org.junit.Assert.assertFalse("Error - unexpected boolean", wrong2);

    }
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



    @Test
    public void testCpArrayGetter() throws Exception {
        ArrayList<String> cpStrArray = new ArrayList<>(Arrays.asList("U+0012", "U+0231"));
        String[] cpArray = Main.cpArrayGetter(cpStrArray);
        String[] expected = {"U+0012","U+0231"};
        System.out.println("CpArrayGetter " + Arrays.toString(cpArray));
        org.junit.Assert.assertArrayEquals("Error- array returned was not " +
                "array expected", expected, cpArray);
    }
}