package edu.carleton.oshern;

import jdk.nashorn.internal.runtime.regexp.joni.EncodingHelper;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.both;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.junit.matchers.JUnitMatchers.everyItem;
import static org.junit.matchers.JUnitMatchers.hasItems;

/**
 * Created by Nate on 4/4/15.
 */
public class EncodingHelperCharTest {


    @Test
    public void testGetCodePointShouldNotReturnNull() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar(42);
        org.junit.Assert.assertNotNull("Codepoint should not be null", thing);
    }

    @Test
    public void testSetCodePointShouldChangeValue() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar(null);
        thing.setCodePoint(0x42);
        org.junit.Assert.assertEquals("Failure- should return true", 0x42, thing.getCodePoint());
    }

    @Test
    public void testToUtf8BytesShouldReturnCorrectValues() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('A');
        byte[] expected = new byte[10];
        expected[0] = 0x41;
        org.junit.Assert.assertArrayEquals("Failure- byte arrays are not the same", expected, thing.toUtf8Bytes());
    }

    @Test
    public void testToUtf8BytesShouldNotReturnNull() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('A');
        org.junit.Assert.assertNotNull("Failure- byte array was empty", thing.toUtf8Bytes());
    }

    @Test
    public void testToCodePointStringShouldReturnCorrectString() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('Ꮬ');
        String correct = "U+13DC";
        org.junit.Assert.assertSame("Failure- string returned was not expected string", correct, thing.toCodePointString());
    }

    @Test
    public void testToUtf8StringShouldReturnCorrectString() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('é');
        String correct = "\\xC3\\xA9";
        org.junit.Assert.assertSame("Failure- string returned was not expected string", correct , thing.toUtf8String());
    }

    @Test
    public void testToUtf8StringShouldNotReturnEmptyString() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('é');
        String wrong = "";
        org.junit.Assert.assertNotSame("Failure- method returned empty string", wrong, thing.toUtf8String());
    }

    @Test
    public void testGetCharacterNameShouldReturnCorrectName() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('é');
        String correct = "LATIN SMALL LETTER E WITH ACUTE";
        org.junit.Assert.assertSame("Failure- string returned was not expected string", correct, thing.getCharacterName());
    }

    @Test
    public void testGetCharacterNameShouldNotReturnEmptyString() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('é');
        String wrong = "";
        org.junit.Assert.assertNotSame("Failure- method returned empty string", wrong, thing.getCharacterName());
    }
}

