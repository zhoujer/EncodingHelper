package edu.carleton.oshern;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        for(int i = 0; i < args.length; i++){
//            System.out.println(args[i]);
//        }
        if(args.length == 0 || args[0].equals("help") || args[0].equals("-h")){
            help();
        }else if (args.length == 1){
            // System.out.println("Got here 0");
            if (args[0].length() == 1){
                char theChar = args[0].charAt(0);
                charSummary(theChar, "all");
            }else{
                stringSummary(args[0], "all");
            }
        }else{
            // System.out.println("Got here !");
            // System.out.println(args[0]);
            if (args[0].equals("--output") || args[0].equals("-o")){
                if ((args.length > 3) && (args[2].equals("--input") || args[2]
                        .equals
                        ("-i"))){
                    // System.out.println("Got here 1");
                    String outputType = args[1];
                    String inputType = args[3];
                    ArrayList data = collector(args, 4);
                    processor(inputType, outputType, data);

                }else if(args.length >= 3){
                    // System.out.println("Got here 2");
                    String outputType = args[1];
                    String inputType = "none";
                    ArrayList data = collector(args, 2);
                    processor(inputType, outputType, data);
                }else{
                    String error = errorMsg("arg");
                    System.out.println(error);
                }
            }else if (args[0].equals("--input") || args[0].equals("-i")){
                if ((args.length > 3) && (args[2].equals("--output") || args[2]
                        .equals("-o"))){
                    // System.out.println("Got here 3");
                    String outputType = args[3];
                    String inputType = args[1];
                    ArrayList data = collector(args, 4);
                    processor(inputType, outputType, data);
                }else if(args.length >= 3){
                    // System.out.println("Got here 4");
                    String inputType = args[1];
                    String outputType = "none";
                    ArrayList data = collector(args, 2);
                    processor(inputType, outputType, data);
                }else{
                    String error = errorMsg("arg");
                    System.out.println(error);
                }
            }else{
                String error = errorMsg("arg");
                System.out.println(error);
            }
        }
    }

    public static void help(){
        System.out.println("Welcome to EncodingHelper by Jerry Zhou and Nate " +
                "Osher!");
        System.out.println("Specifying input: (defaults to string)");
        System.out.println(">$ java EncodingHelper [-i or --input] <string> " +
                "<utf8> <codepoint>");
        System.out.println(" ");
        System.out.println("Specifying output: (defaults to summary)");
        System.out.println(">$ java EncodingHelper [-o or --output] <string> " +
                "<utf8> <codepoint>");
        System.out.println(" ");
        System.out.println("Example of how to specify codepoints:");
        System.out.println(">$ java EncodingHelper [-i or --input] codepoints" +
                " [U+0041 U+0042 U=0043] [\"U+0041 U+0042 U=0043\"]");
        System.out.println(" ");
        System.out.println("Example of how to specify UTF-8 bytes:");
        System.out.println(">$ java EncodingHelper [-i or --input] utf8" +
                " [\\xaa\\xbb\\xcc]");
        System.out.println(" ");
        System.out.println("Example of how to specify string:");
        System.out.println(">$ java EncodingHelper [-i or --input] string" +
                " [examplestringhere]");
    }

    public static void charSummary(char ch, String k){
        EncodingHelperChar sumChar = new EncodingHelperChar(ch);
        if(k.equals("all")){
            System.out.println("Character: " + ch);
            System.out.println("Codepoint: " + sumChar.toCodePointString());
            System.out.println("Name: " + sumChar.getCharacterName());
            System.out.println("UTF-8: " + sumChar.toUtf8String());
        }else if(k.equals("cdp")){
            System.out.println(sumChar.toCodePointString());
        }else if(k.equals("u8")){
            System.out.println(sumChar.toUtf8String());
        }else{

        }
    }

    public static void stringSummary(String inputString, String k){
        char[] charray = inputString.toCharArray();
        if(k.equals("all")){
            System.out.println("String: " + inputString);
            System.out.print("Code Points: ");
            for (int i = 0; i < charray.length; i++){
                EncodingHelperChar tempChar = new EncodingHelperChar(charray[i]);
                System.out.print(tempChar.toCodePointString() + " ");
            }
            System.out.println(" ");
            System.out.print("UTF-8: ");
            for (int i = 0; i < charray.length; i++){
                EncodingHelperChar tempChar = new EncodingHelperChar(charray[i]);
                System.out.print(tempChar.toUtf8String());
            }
            System.out.println("");
        }else if(k.equals("cdp")){
            for (int i = 0; i < charray.length; i++){
                EncodingHelperChar tempChar = new EncodingHelperChar(charray[i]);
                System.out.print(tempChar.toCodePointString() + " ");
            }
            System.out.println(" ");
        }else if(k.equals("u8")){
            for (int i = 0; i < charray.length; i++){
                EncodingHelperChar tempChar = new EncodingHelperChar(charray[i]);
                System.out.print(tempChar.toUtf8String());
            }
            System.out.println(" ");
        }
    }

    public static ArrayList collector(String[] b, int j){
        ArrayList a = new ArrayList();
        for(int i = j; i < b.length; i++){
            a.add(b[i]);
        }
        return a;
    }

    public static boolean validCommandInOut(String in, String out){
        if (in.equals("string")|in.equals("none")|in.equals("utf8")|in.equals("codepoint")){
            if (out.equals("string")|out.equals("none")|out.equals("utf8")|out.equals("codepoint")){
                return true;
            }else{
                System.out.println(errorMsg("outputType"));
                return false;
            }
        }else{
            System.out.println(errorMsg("inputType"));
            return false;
        }
    }

    public static void processor(String in, String out, ArrayList d){
        // System.out.println(in + " " + out);
        if (validCommandInOut(in,out)) {
            if (in.equals("string") || in.equals("none")) {
                stringOutProcessor(d, out);
            } else if (in.equals("utf8")) {
                utf8OutProcessor(d, out);
            } else if (in.equals("codepoint")) {
                codepointOutProcessor(d, out);
            } else {
                String error = errorMsg("inputType");
                System.out.println(error);
            }
        }
    }

    /**
     * Default processor for EncodingHelper.
     *
     * Processes the input of EncodingHelper if the user specifies
     * that the type is string or does not specify an input type, and gives the
     * output depending on the output
     * that the user specifies. Default output is a summary.
     *
     * For example, if the user simply types:
     * >$java EncodingHelper asdf
     *
     * It will output
     * String: asdf
     * Code Points: U+0061 U+0073 U+0064 U+0066
     * UTF-8: \x61\x73\x64\x66
     */
    public static void stringOutProcessor(ArrayList lst, String o){
        if(o.equals("none")){
            if (lst.get(0).toString().length() == 1){
                char theChar = lst.get(0).toString().charAt(0);
                charSummary(theChar, "all");
            }else{
                stringSummary(lst.get(0).toString(), "all");
            }
        }else if(o.equals("utf8")){
            // System.out.println("Here I am!");
            if (lst.get(0).toString().length() == 1){
                char theChar = lst.get(0).toString().charAt(0);
                charSummary(theChar, "u8");
            }else{
                stringSummary(lst.get(0).toString(), "u8");
            }
        }else if(o.equals("codepoint")){
            if (lst.get(0).toString().length() == 1){
                char theChar = lst.get(0).toString().charAt(0);
                charSummary(theChar, "cdp");
            }else{
                stringSummary(lst.get(0).toString(), "cdp");
            }
        }else{
            String error = errorMsg("outputType");
            System.out.println(error);
        }
    }

    /**
     * Processes the input of EncodingHelper if the user specifies that it is
     * in UTF-8 byte format. Output varies depending on whether or not the user
     * specified an output type. Default output type is a summary.
     *
     * For example, if the user types:
     *
     * >$java EncodingHelper -i utf8 \x24
     *
     * It will output:
     * Character: $
     * Codepoint: U+0024
     * Name: DOLLAR SIGN
     * UTF-8: \x24
     */
    public static void utf8OutProcessor(ArrayList lst, String o){
        String byteString = lst.get(0).toString();
        // System.out.println(byteString);
        byteString = byteString.replace("\\", "").replace("x", "");
        if(byteString.length() % 2 == 0){
            // UPDATE THIS UPDATE THIS UPDATE THIS UPDATE THIS
            // DO IT DO IT DO IT DO IT DO IT DO IT
        }
        byte[] byteArray = new byte[byteString.length() / 2];
        // System.out.println(byteString);
        int counter = 0;
        for(int i=0; i < byteString.length(); i+=2){
            String subByteString = byteString.substring(i, i+2);
            // System.out.println(subByteString);
            // System.out.println(Integer.parseInt(subByteString, 16));
            byteArray[counter] = (byte) (Integer.parseInt(subByteString, 16)
                    & 0xFF);
            counter += 1;
        }

        byte[][] doubleArray = doubleArrayGenerator(byteArray);
//        for(int i=0; i < doubleArray.length; i++){
//            System.out.println(Arrays.toString(doubleArray[i]));
//        }

        ArrayList<byte[]> newDoubleArray = new ArrayList<>(20);
        for(int i=0; i < doubleArray.length; i++){
            if(doubleArray[i] != null){
                newDoubleArray.add(i, doubleArray[i]);
            }
        }

        if(o.equals("none")){
            // System.out.println("Got here, in none section");
            if(newDoubleArray.size() == 1){
                EncodingHelperChar theCpEH = new EncodingHelperChar
                        (newDoubleArray.get(0));
                System.out.println("Character: " + Character.toString((char)
                        theCpEH.getCodePoint()));
                System.out.println("Codepoint: " + theCpEH.toCodePointString());
                System.out.println("Name: " + theCpEH.getCharacterName());
                System.out.println("UTF-8: " + theCpEH.toUtf8String());
            }else{
                String finalString = "";
                String finalCodepoints = "";
                String finalUTF8 = "";
                for(int i = 0; i < newDoubleArray.size(); i++){
                    EncodingHelperChar theCpEH = new EncodingHelperChar
                            (newDoubleArray.get(i));
                    finalCodepoints += theCpEH.toCodePointString() + " ";
                    finalString += Character.toString((char)
                            theCpEH.getCodePoint());
                    finalUTF8 += theCpEH.toUtf8String();
                }
                System.out.println("String: " + finalString);
                System.out.println("Codepoints: " + finalCodepoints);
                System.out.println("UTF-8: " + finalUTF8);
            }
        }else if(o.equals("utf8")){
            // TBD
        }else if(o.equals("codepoint")) {
            if (newDoubleArray.size() == 1) {
                EncodingHelperChar theCpEH = new EncodingHelperChar
                        (newDoubleArray.get(0));
                System.out.println(theCpEH.toCodePointString());
            }else{
                String finalCodepoints = "";
                for(int i = 0; i < newDoubleArray.size(); i++){
                    EncodingHelperChar theCpEH = new EncodingHelperChar
                            (newDoubleArray.get(i));
                    finalCodepoints += theCpEH.toCodePointString() + " ";
                }
                System.out.println(finalCodepoints);
            }
        } else if(o.equals("string")){
            if (newDoubleArray.size() == 1) {
                EncodingHelperChar theCpEH = new EncodingHelperChar
                        (newDoubleArray.get(0));
                System.out.println(Character.toString((char)
                        theCpEH.getCodePoint()));
            }else{
                String finalString = "";
                for(int i = 0; i < newDoubleArray.size(); i++){
                    EncodingHelperChar theCpEH = new EncodingHelperChar
                            (newDoubleArray.get(i));
                    finalString += Character.toString((char)
                            theCpEH.getCodePoint());
                }
                System.out.println(finalString);
            }
        }else{
            String error = errorMsg("outputType");
            System.out.println(error);
        }
    }

    /**
     * Given an array of bytes, outputs a 2D array of UTF-8 byte arrays.
     *
     * For example, if the user passes in byte[] b = {0x24, 0xc2, 0xa2},
     *
     * It will return doubleArray =
     * [0x24]
     * [0xc2, 0xa2]
     *
     * @return 2 dimensional array of UTF-8 byte arrays
     */
    public static byte[][] doubleArrayGenerator(byte[] b){
        int parser = 0;
        int tracker = 0;
        byte[][] doubleArray = new byte[b.length][];
        while(parser < b.length){
            if((b[parser] & 0x80) != 0x80){
                // System.out.println("First case works");
                doubleArray[tracker] = Arrays.copyOfRange(b, parser,
                        parser + 1);
                tracker += 1;
                parser += 1;
            }else if((b[parser] & 0xe0) == 0xc0){
                // System.out.println("Second case works");
                doubleArray[tracker] = Arrays.copyOfRange(b, parser,
                        parser + 2);
                tracker += 1;
                parser += 2;
            }else if((b[parser] & 0xf0) == 0xe0){
                // System.out.println("Third case works");
                doubleArray[tracker] = Arrays.copyOfRange(b, parser,
                        parser + 3);
                tracker += 1;
                parser += 3;
            }else if((b[parser] & 0xf8) == 0xf0){
                // System.out.println("Fourth case works");
                doubleArray[tracker] = Arrays.copyOfRange(b, parser,
                        parser + 4);
                tracker += 1;
                parser += 4;
            }else{
                // throw an error
            }
        }
        return doubleArray;
    }

    /**
     * Determines whether or not the input is a valid hexadecimal string
     *
     * @return boolean value (true if valid, false if not)
     */
    public static  boolean  validHexString(String cpStr){
        if (cpStr == ""){
            return false;
        }
        //if (cpStr.charAt(0) == 'U' | cpStr.charAt(0) == 'u' ){
        if (cpStr.matches("[0-9A-Fa-f]+")){
            /*if (cpStr.charAt(1) == '+' | cpStr.charAt(1) == '+' ){
                return true;
            }else{
                return false;
            } */ return true;
        }
        else {
            return false;
        }
    }

    /**
     * Processes the input of EncodingHelper if the user specifies that the
     * input type is codepoint. Output varies depending on whether or not the
     * user specified an output type. Default output type is a summary.
     *
     * For example, if the user types:
     *
     * >$java EncodingHelper -i codepoint u0041 u0042 u0055
     *
     * It will output:
     * String: ABU
     * Codepoints: U+0041 U+0042 U+0055
     * UTF-8: \x41\x42\x55
     */
    public static void codepointOutProcessor(ArrayList lst, String o){
        String cpArray[] = cpArrayGetter(lst);
        //System.out.println(cpArray.length);
        if(cpArray.length == 0) {
            System.out.println(errorMsg("noinput"));
        }
        boolean validCpArray = true;
        for (int i = 0; i<cpArray.length;i++){
            if (! validHexString(cpArray[i])){
                System.out.println("Error - "+ cpArray[i] + " not valid codepoint");
                validCpArray = false;
            }
        }
        if(validCpArray) {
            if (o.equals("none")) {
                if (cpArray.length == 1) {
                    //int theCp = Integer.parseInt(cpArray[0].substring(2), 16);
                    int theCp = Integer.parseInt(cpArray[0], 16);
                    EncodingHelperChar theCpEH = new EncodingHelperChar(theCp);
                    System.out.println("Character: " + Character.toString((char)
                            theCp));
                    System.out.println("Codepoint: " + theCpEH.toCodePointString());
                    System.out.println("Name: " + theCpEH.getCharacterName());
                    System.out.println("UTF-8: " + theCpEH.toUtf8String());
                } else {
                    String finalString = "";
                    String finalCodepoints = "";
                    String finalUTF8 = "";
                    for (int i = 0; i < cpArray.length; i++) {
                        //int theCp = Integer.parseInt(cpArray[i].substring(2), 16);
                        int theCp = Integer.parseInt(cpArray[i], 16);
                        EncodingHelperChar theCpEH = new EncodingHelperChar(theCp);
                        finalCodepoints += "U+" + cpArray[i] + " ";
                        finalString += Character.toString((char) theCp);
                        finalUTF8 += theCpEH.toUtf8String();
                    }
                    System.out.println("String: " + finalString);
                    System.out.println("Codepoints: " + finalCodepoints);
                    System.out.println("UTF-8: " + finalUTF8);
                }
            } else if (o.equals("utf8")) {
                //System.out.println(Arrays.toString(cpArray ));
                if (cpArray.length == 1) {
                    //int theCp = Integer.parseInt(cpArray[0].substring(2), 16);
                    int theCp = Integer.parseInt(cpArray[0], 16);
                    EncodingHelperChar theCpEH = new EncodingHelperChar(theCp);
                    System.out.println(theCpEH.toUtf8String());
                } else {
                    String finalUTF8 = "";
                    for (int i = 0; i < cpArray.length; i++) {
                        //int theCp = Integer.parseInt(cpArray[i].substring(2), 16);
                        int theCp = Integer.parseInt(cpArray[0], 16);
                        EncodingHelperChar theCpEH = new EncodingHelperChar(theCp);
                        finalUTF8 += theCpEH.toUtf8String();
                    }
                    System.out.println(finalUTF8);
                }
            } else if (o.equals("codepoint")) {
                for (int i = 0; i < cpArray.length; i++) {
                    System.out.print(cpArray[i] + " ");
                }
                System.out.println(" ");
            } else if (o.equals("string")) {
                String finalString = "";
                for (int i = 0; i < cpArray.length; i++) {
                    //int theCp = Integer.parseInt(cpArray[i].substring(2), 16);
                    int theCp = Integer.parseInt(cpArray[0], 16);
                    EncodingHelperChar theCpEH = new EncodingHelperChar(theCp);
                    finalString += Character.toString((char) theCp);
                }
                System.out.println(finalString);
            } else {
                String error = errorMsg("outputType");
                System.out.println(error);
            }
        }
    }

    /**
     * Takes an arraylist of codepoints in various formats and strips them down
     * to just their hexadecimal values.
     *
     * For example, if it passed the arraylist {u0042, u+0024, \u0095}
     * It outputs the array of strings cpArray = {0042, 0024, 0095}
     *
     * @return array of strings
     */
    public static String[] cpArrayGetter(ArrayList l){
        String[] cpArray;
        if(l.size() == 1){
            cpArray = l.get(0).toString().split(" ");
            for(int i=0; i < cpArray.length; i++){
                cpArray[i] = cpArray[i].toString().replace("U", "").replace("u",
                        "").replace("\\", "").replace("+", "");
            }
        }else{
            cpArray = new String[l.size()];
            for(int i=0; i < l.size(); i++){
                cpArray[i] = l.get(i).toString().replace("U", "").replace("u",
                        "").replace("\\", "").replace("+", "");
            }
        }
        return cpArray;
    }

    /**
     * Given a string input, returns the appropriate corresponding error
     * message string
     *
     * For example, if we pass in the string "inputArg" it will return the
     * string
     *
     * "Error - Incorrect input argument. Use: -i or --input"
     * "\n Use -h on command line for help"
     *
     * @return Error message string
     */
    public static String errorMsg (String err){
        if (err.equals("inputArg")){
            return "Error - Incorrect input argument. Use: -i or --input" +
                    "\n Use -h on command line for help";

        }else if (err.equals("inputType")) {
            return "Error - Incorrect input type. " +
                    "\n Correct types: [codepoint, utf8, or string]"+
                    "\n Use -h on command line for help";

        }else if (err.equals("outputArg")){
            return "Error - Incorrect output argument. Use: -i or --input"+
                    "\n Use -h on command line for help";

        }else if (err.equals("outputType")){
            return "Error - Incorrect output type. " +
                    "\n Correct types: [codepoint, utf8, or string]"+
                    "\n Use -h on command line for help";

        }else if (err.equals("arg")){
            return "Error - Incorrect input arguments. " +
                    "\n To specify input: Use [-i or --input] followed by " +
                    "[utf8, string, or codepoint ]" +
                    "\n To specify output: Use [-o or --output] followed by " +
                    "[utf8, string, or codepoint ]" +
                    "\n Use -h for help";

        }else if (err.equals("noinput")){
            return "Error - no input value";

        }else if (err.equals("unknown")){
            return "Unknown Error";

        }else{
            return "Incorrect Error Message ";
        }
    }

}

