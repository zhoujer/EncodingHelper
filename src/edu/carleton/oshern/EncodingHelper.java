// package edu.carleton.oshern;

import java.util.ArrayList;

public class EncodingHelper {

    public static void main(String[] args) {
//        for(int i = 0; i < args.length; i++){
//            System.out.println(args[i]);
//        }
        if (args.length == 1){
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
                if (args[2].equals("--input") || args[2].equals("-i")){
                    // System.out.println("Got here 1");
                    String outputType = args[1];
                    String inputType = args[3];
                    ArrayList data = collector(args, 4);
                    processor(inputType, outputType, data);
                }else{
                    // System.out.println("Got here 2");
                    String outputType = args[1];
                    String inputType = "none";
                    ArrayList data = collector(args, 2);
                    processor(inputType, outputType, data);
                }
            }else if (args[0].equals("--input") || args[0].equals("-i")){
                if (args[2].equals("--output") || args[2].equals("-o")){
                    // System.out.println("Got here 3");
                    String outputType = args[3];
                    String inputType = args[1];
                    ArrayList data = collector(args, 4);
                    processor(inputType, outputType, data);
                }else{
                    // System.out.println("Got here 4");
                    String inputType = args[1];
                    String outputType = "none";
                    ArrayList data = collector(args, 2);
                    processor(inputType, outputType, data);
                }
            }else{
                // System.out.println("Oops");
            }
        }
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
            System.out.println("UTF-8: ");
            for (int i = 0; i < charray.length; i++){
                EncodingHelperChar tempChar = new EncodingHelperChar(charray[i]);
                System.out.print(tempChar.toUtf8String() + " ");
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

    public static void processor(String in, String out, ArrayList d){
        System.out.println(in + " " + out);
        if(in.equals("string") || in.equals("none")){
            stringSubProcessor(d, out);
        }else if(in == "utf8"){

        }else if(in == "codepoint"){

        }else{
            // ERROR MESSAGE
        }
    }

    public static void stringSubProcessor(ArrayList lst, String o){
        if(o == "none"){
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
        }else if(o == "codepoint"){
            if (lst.get(0).toString().length() == 1){
                char theChar = lst.get(0).toString().charAt(0);
                charSummary(theChar, "cdp");
            }else{
                stringSummary(lst.get(0).toString(), "cdp");
            }
        }else{
            System.out.println("Fuck");
        }
    }

    public static void utf8SubProcessor(ArrayList lst){

    }

}