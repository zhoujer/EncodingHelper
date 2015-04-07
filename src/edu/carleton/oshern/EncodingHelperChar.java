package edu.carleton.oshern;
import java.io.*;
import java.util.*;

/**
 * The main model class for the EncodingHelper project in
 * CS 257, Spring 2015, Carleton College. Each object of this class
 * represents a single Unicode character. The class's methods
 * generate various representations of the character. 
 */
public class EncodingHelperChar {
    private int codePoint;

    public EncodingHelperChar(int codePoint) {
        this.codePoint = codePoint;
    }
    
    public EncodingHelperChar(byte[] utf8Bytes) {
        String utf8String = "";
        for (int i = 0; i < utf8Bytes.length; i++){
            utf8String += String.format("%8s", Integer.toBinaryString(utf8Bytes[i] & 0xFF)).replace(' ', '0');
        }
        // System.out.println(utf8String);
        if (utf8String.length() == 8){
            utf8String = utf8String;
        }else if(utf8String.length() == 16){
            utf8String = utf8String.substring(3, 8) + utf8String.substring(10);
        }else if(utf8String.length() == 24){
            utf8String = utf8String.substring(4, 8) + utf8String.substring(10, 16) + utf8String.substring(18);
        }else if(utf8String.length() == 32){
            utf8String = utf8String.substring(5, 8) + utf8String.substring(10, 16) + utf8String.substring(18, 24) +
                    utf8String.substring(26);
        }else if(utf8String.length() == 40){
            utf8String = utf8String.substring(6, 8) + utf8String.substring(10, 16) + utf8String.substring(18, 24) +
                    utf8String.substring(26, 32) + utf8String.substring(34);
        }else if(utf8String.length() == 48){
            utf8String = utf8String.substring(6, 8) + utf8String.substring(10, 16) + utf8String.substring(18, 24) +
                    utf8String.substring(26, 32) + utf8String.substring(34, 40) +
                    utf8String.substring(42);
        }else{
            System.out.println("Error- bytes not in UTF-8 format");
        }
        this.codePoint = Integer.parseInt(utf8String, 2);
        // System.out.println(this.codePoint);
    }
    
    public EncodingHelperChar(char ch) {
        this.codePoint = ch;
        // System.out.println(Integer.toString(this.codePoint));
    }
    
    public int getCodePoint() {
        return this.codePoint;
    }
    
    public void setCodePoint(int codePoint) {
        this.codePoint = codePoint;
    }
    
    /**
     * Converts this character into an array of the bytes in its UTF-8
     * representation.
     *   For example, if this character is a lower-case letter e with an acute
     * accent, whose UTF-8 form consists of the two-byte sequence C3 A9, then
     * this method returns a byte[] of length 2 containing C3 and A9.
     * 
     * @return the UTF-8 byte array for this character
     */
    public byte[] toUtf8Bytes() {

        return null;
    }
    
    /**
     * Generates the conventional 4-digit hexadecimal code point notation for
     * this character.
     *   For example, if this character is a lower-case letter e with an acute
     * accent, then this method returns the string U+00E9 (no quotation marks in
     * the returned String).
     *
     * @return the U+ string for this character
     */
    public String toCodePointString() {
        String hexString = Integer.toHexString(this.codePoint);
        if (hexString.length() == 2){
            hexString = "00" + hexString;
        } else if (hexString.length() == 3){
            hexString = "0" + hexString;
        }
        hexString = "U+" + hexString;
        // System.out.println(hexString);
        return hexString.toUpperCase();
    }
    
    /**
     * Generates a hexadecimal representation of this character suitable for
     * pasting into a string literal in languages that support hexadecimal byte
     * escape sequences in string literals (e.g. C, C++, and Python).
     *   For example, if this character is a lower-case letter e with an acute
     * accent (U+00E9), then this method returns the string \xC3\xA9. Note that
     * quotation marks should not be included in the returned String.
     *
     * @return the escaped hexadecimal byte string
     */
    public String toUtf8String() {
        // Not yet implemented.
        return "";
    }
    
    /**
     * Generates the official Unicode name for this character.
     *   For example, if this character is a lower-case letter e with an acute
     * accent, then this method returns the string "LATIN SMALL LETTER E WITH
     * ACUTE" (without quotation marks).
     *
     * @return this character's Unicode name
     */
    public String getCharacterName() {
        String codePointString = this.toCodePointString();
        codePointString = codePointString.substring(2).toUpperCase();
        // System.out.println(codePointString);
        int codeLength = codePointString.length();
        String name = null;
        File file = new File ("/Users/Nate/documents/projects/EncodingHelper/src/edu/carleton/oshern/unicode.txt");
        try{
            Scanner scan = new Scanner (file);
            while (scan.hasNextLine() == true){
                String line = scan.nextLine();
                // System.out.println(line.substring(0, codeLength));
                if (line.substring(0, codeLength).equals(codePointString)) {
                    String[] pieces = line.split(";");
                    // System.out.println(Arrays.toString(pieces));
                    name = pieces[1];
                }

            }
        }
        catch(FileNotFoundException ex){
            System.out.println("Error- file not found");
        }
        if (name != null){
            return name;
        } else {
            return "Error- character name not found";
        }
    }
}