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
        thing.setCodePoint(42);
        org.junit.Assert.assertEquals("Failure- should return true", 42, thing.getCodePoint());
    }

    @Test
    public void testToUtf8BytesShouldReturnCorrectValues() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('A');
        byte[] actual = new byte[42];
        org.junit.Assert.assertArrayEquals("Failure- byte arrays are not the same", actual, thing.toUtf8Bytes());
    }

    @Test
    public void testToCodePointStringShouldReturnCorrectString() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('Ꮬ');
        String correct = "\\x13\\xDC";
        org.junit.Assert.assertSame("Failure- string returned was not expected string", correct , thing.toCodePointString());
    }

    @Test
    public void testToUtf8String() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('é');
        String correct = "\\xC3\\xA9";
        org.junit.Assert.assertSame("Failure- string returned was not expected string", correct , thing.toUtf8String());
    }

    @Test
    public void testGetCharacterName() throws Exception {
        EncodingHelperChar thing = new EncodingHelperChar('é');
        String correct = "LATIN SMALL LETTER E WITH ACUTE";
        org.junit.Assert.assertSame("Failure- string returned was not expected string", correct , thing.getCharacterName());
    }
}