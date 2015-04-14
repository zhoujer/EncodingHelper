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
        if ( codePoint > 1114111 | codePoint < 0) {
            System.out.println(errorMsg("codepointsize"));
            //throw new IllegalArgumentException("Error- codepoint input is " +
                    //"either too large or too small")
        }else{
            this.codePoint = codePoint;
        }
    }
    
    public EncodingHelperChar(byte[] utf8Bytes) {
        if (utf8Bytes.length > 4){
            System.out.println(errorMsg("bytearray"));
            //throw new IllegalArgumentException("Error- UTF-8 encodings cannot" +
                    //" be longer than 4 bytes");
        }
        
        if (utf8Bytes.length == 1){
            this.codePoint = utf8Bytes[0];
            // System.out.println(Integer.toString(this.getCodePoint()));
        }else if (utf8Bytes.length == 2){
            utf8Bytes[0] = (byte) (utf8Bytes[0] & 0x1F);
            utf8Bytes[1] = (byte) ((utf8Bytes[1] & 0x3F) | (utf8Bytes[0] << 6));
            utf8Bytes[0] = (byte) (utf8Bytes[0] >> 2);
            this.setCodePoint(Byte.toUnsignedInt(utf8Bytes[0]) << 6);
            this.setCodePoint(this.getCodePoint() |
                    (Byte.toUnsignedInt(utf8Bytes[1])));
            // System.out.println(Integer.toString(this.getCodePoint()));

        }else if (utf8Bytes.length == 3){
            utf8Bytes[0] = (byte) (utf8Bytes[0] & 0x0F);
            utf8Bytes[1] = (byte) (utf8Bytes[1] & 0x3F);
            utf8Bytes[2] = (byte) (utf8Bytes[2] & 0x3F);
            this.setCodePoint(Byte.toUnsignedInt(utf8Bytes[0]) << 12);
            this.setCodePoint(this.getCodePoint() |
                    (Byte.toUnsignedInt(utf8Bytes[1]) << 6));
            this.setCodePoint(this.getCodePoint() |
                    (Byte.toUnsignedInt(utf8Bytes[2])));
            // System.out.println(Integer.toString(this.getCodePoint()));


        }else if (utf8Bytes.length == 4){
            utf8Bytes[0] = (byte) (utf8Bytes[0] & 0x07);
            utf8Bytes[1] = (byte) (utf8Bytes[1] & 0x3F);
            utf8Bytes[2] = (byte) (utf8Bytes[2] & 0x3F);
            utf8Bytes[3] = (byte) (utf8Bytes[3] & 0x3F);
            this.setCodePoint(Byte.toUnsignedInt(utf8Bytes[0]) << 18);
            this.setCodePoint(this.getCodePoint() |
                    (Byte.toUnsignedInt(utf8Bytes[1]) << 12));
            this.setCodePoint(this.getCodePoint() |
                    (Byte.toUnsignedInt(utf8Bytes[2]) << 6));
            this.setCodePoint(this.getCodePoint() |
                    (Byte.toUnsignedInt(utf8Bytes[3])));
            // System.out.println(Integer.toString(this.getCodePoint()));
        }

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
        //long unsignedInt = -1 & this.getCodePoint();
        int cp = this.getCodePoint();
        byte cpByte = (byte) cp;
        //System.out.println("CodePoint (hex) : " +
        // Integer.toHexString(cp) + " (dec) : " + cp);


        byte[] byteArray = null;

        if ( cp <  0x0080){
            byteArray =  new byte[]{ (byte) cp};
            //System.out.println("byte[] length should be 1");
        }
        else if (cp <  0x0800){
            byteArray = new byte[2];
            byte byte1 = (byte) ((cp >>6) & 0x001F | 0xFFC0 );
            byte byte2 = (byte)(cp & 0x003F | 0x80 );
            //System.out.println("byte[] length should be 2");
            byteArray =  new byte[]{ byte1 , byte2 };
        }
        else if (cp <  0x10000){
            byteArray = new byte[3];
            byte byte1 = (byte) ((cp >>12) & 0x000F | 0x00E0 );
            byte byte2 = (byte) ((cp >>6) & 0x003F | 0x0080 );
            byte byte3 = (byte) (cp & 0x003F | 0x0080 );
            // System.out.println("byte[] length should be 3");
            byteArray =  new byte[]{ byte1 , byte2, byte3 };
        }
        else if (cp < 0x110000)  {
            byteArray = new byte[4];
            byte byte1 = (byte) ((cp >>18) & 0x0007 | 0x00F0 );
            byte byte2 = (byte) ((cp >>12) & 0x003F | 0x0080 );
            byte byte3 = (byte) ((cp >>6) & 0x003F | 0x0080 );
            byte byte4 = (byte) (cp & 0x003F | 0x0080 );
            // System.out.println("byte[] length should be 4");
            byteArray =  new byte[]{ byte1 , byte2, byte3, byte4};
        } else {
            System.out.println("Error - Code point not within U+0000 to " +
                    "U+110000: Returning byte array as null ");
        }
        //System.out.println("ByteArray: "  + Arrays.toString(byteArray));
        return byteArray;
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
        if (hexString.length() == 1){
            hexString = "000" + hexString;
        }else if (hexString.length() == 2){
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
        byte[] byteHolder = this.toUtf8Bytes();
        String utf8String = "";
        for (int i = 0; i < byteHolder.length; i++){
            utf8String += String.format("\\x%02X", byteHolder[i]);
        }
        return utf8String;
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
        // codePointString = "0015";
        // System.out.println(codePointString);
        int codeLength = codePointString.length();
        String name = null;
        File file = new File("edu/carleton/oshern/unicode" +
                ".txt");
        //File file = new File("unicode.txt");
        try{
            Scanner scan = new Scanner (file);
            while (scan.hasNextLine() == true){
                String line = scan.nextLine();
                // System.out.println(line.substring(0, codeLength));
                if (line.substring(0, codeLength).equals(codePointString)) {
                    String[] pieces = line.split(";");
                    // System.out.println(Arrays.toString(pieces));
                    if (pieces[1].equals("<control>")){
                        name = pieces[1] + " " + pieces[10];
                        // System.out.println(name);
                    }else{
                        name = pieces[1];
                    }
                }

            }
        }
        catch(FileNotFoundException ex){
            System.out.println("Error- file not found");
        }
        if (name != null){
            return name;
        } else {
            return "<unknown> " + this.toCodePointString();
        }
    }
    public static String errorMsg (String err){
        if (err.equals("codepointsize")){
            return "Error- codepoint input is " +
            "either too large or too small";

        }else if (err.equals("bytearray")){
            return "Error- byte array not valid UTf-8 encoding ";

        }else if (err.equals("unknown")){
            return "Unknown Error";

        }else{
            return "Incorrect Error Message ";
        }
    }
}