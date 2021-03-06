package edu.carleton.oshern;

import jdk.nashorn.internal.runtime.regexp.joni.EncodingHelper;
import org.junit.Before;
import org.junit.Test;



/**
 * Created by Nate on 4/4/15.
 * A series of tests for the EncodingHelperChar class
 * By Nate Osher and Jerry Zhou
 */
public class EncodingHelperCharTest {

    // CONSTRUCTOR TESTS

    @Test
    public void testIntConstructorShouldWork() throws Exception{
        EncodingHelperChar thing = new EncodingHelperChar(66);
        org.junit.Assert.assertEquals("Failure- Constructor failed to create " +
                " instance of EncodingHelperChar - code point constructor",
                0x42, thing.getCodePoint());
    }

    @Test
    public void testUtf8ConstructorShouldWork() throws Exception{
        EncodingHelperChar thing = new EncodingHelperChar(new byte[]{(byte)
                0xE2,(byte) 0x82, (byte) 0xAC});
        org.junit.Assert.assertNotNull("Failure- Constructor failed to create" +
                "  instance of EncodingHelperChar - byte constructor", thing);
    }


    @Test
    public void testCharConstructorShouldWork() throws Exception{
        EncodingHelperChar thing = new EncodingHelperChar('A');
        org.junit.Assert.assertNotNull("Failure- Constructor failed to create" +
                "  instance of EncodingHelperChar - char constructor", thing);
    }

    // GETTER/SETTER TESTS

    @Test
    public void testGetCodePointShouldNotReturnNull() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar(42);
        org.junit.Assert.assertNotNull("Codepoint should not be null", thing);
    }

    @Test
    public void testGetCodePointShouldReturnCorrectValue() throws Exception{
        EncodingHelperChar thing = new EncodingHelperChar(0x42);
        org.junit.Assert.assertEquals("Failure- should return correct value",
                0x42, thing.getCodePoint());
    }

    @Test
    public void testSetCodePointShouldChangeValue() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar(0x00);
        thing.setCodePoint(0x42);
        org.junit.Assert.assertEquals("Failure- values should be equal",
                0x42, thing.getCodePoint());
    }

    // EVERYTHING ELSE

    @Test
    public void testToUtf8BytesShouldReturnCorrectValues() throws Exception {
        //System.out.println('A');
        EncodingHelperChar thing = new EncodingHelperChar('A');
        byte[] expected = new byte[]{ (byte)0x41};
        org.junit.Assert.assertArrayEquals("Failure- byte arrays for 'A' are not the same", expected, thing.toUtf8Bytes());

        //System.out.println('Δ');
        EncodingHelperChar thing2 = new EncodingHelperChar('Δ');
        byte[] expected2 = new byte[]{(byte) 0xCE, (byte)0x94};
        org.junit.Assert.assertArrayEquals("Failure- byte arrays for 'Δ' are not the same", expected2, thing2.toUtf8Bytes());

        //System.out.println('ఋ');
        EncodingHelperChar thing3 = new EncodingHelperChar('ఋ');
        byte[] expected3 = new byte[]{(byte) 0xE0, (byte)0xB0, (byte)0x8B };
        org.junit.Assert.assertArrayEquals("Failure- byte arrays are not the same", expected3, thing3.toUtf8Bytes());

        EncodingHelperChar thing4 = new EncodingHelperChar(0x101111);
        byte[] expected4 = new byte[]{(byte) 0xF4, (byte)0x81, (byte)0x84, (byte)0x91 };
        org.junit.Assert.assertArrayEquals("Failure- byte arrays are not the same", expected4, thing4.toUtf8Bytes());
    }

    @Test
    public void testToUtf8BytesShouldNotReturnNull() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('A');
        org.junit.Assert.assertNotNull("Failure- returned null",  thing
                .toUtf8Bytes());
    }

    @Test
    public void testToCodePointStringShouldReturnCorrectString() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('!');
        String correct = "U+0021";
        org.junit.Assert.assertEquals("Failure- string returned was not " +
                "expected string", correct, thing.toCodePointString());
    }

    @Test
    public void testToUtf8StringShouldReturnCorrectString() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('é');
        String correct = "\\xC3\\xA9";
        org.junit.Assert.assertEquals("Failure- string returned was not " +
                "expected string", correct , thing.toUtf8String());
    }

    @Test
    public void testToUtf8StringShouldNotReturnEmptyString() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('é');
        String wrong = "";
        org.junit.Assert.assertNotSame("Failure- method returned empty  " +
                "string", wrong, thing.toUtf8String());
    }

    @Test
    public void testGetCharacterNameShouldReturnCorrectName() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('é');
        String correct = "LATIN SMALL LETTER E WITH ACUTE";
        org.junit.Assert.assertEquals("Failure- string returned was not " +
                "expected  string", correct, thing.getCharacterName());
    }

    @Test
    public void testGetCharacterNameShouldNotReturnEmptyString() throws
            Exception {
        EncodingHelperChar thing = new EncodingHelperChar('é');
        String wrong = "";
        org.junit.Assert.assertNotSame("Failure- method returned empty  " +
                "string", wrong, thing.getCharacterName());
    }
}

