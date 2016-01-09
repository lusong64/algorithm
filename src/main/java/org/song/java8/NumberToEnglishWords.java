package org.song.java8;

import java.util.Stack;

/**
 * Purpose of this class is to
 */
public class NumberToEnglishWords {
    private static final String[] HTMB = {"Hundred ", "Thousand ", "Million ", "Billion "};
    private static final String[] UNDER_TWENTY={"Zero", "One ", "Two ", "Three ", "Four ", "Five ",
        "Six ", "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ",
        "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
    private static final String[] ABOVE_TWENTY={"Ten ", "Twenty ",
        "Thirty ", "Forty ", "Fifty ", "Sixty ","Seventy ", "Eighty ", "Ninety "};

    private static final String EMPTY="";

    public String numberToWords(int num) {
        if (num == 0){
            return UNDER_TWENTY[0];
        }
        Stack<String> stack = new Stack<>();
        int htmbIndex = 1;
        while(num>0){
            int part = num%1000;
            num/=1000;

            if (num>0){
                htmbIndex++;
            }
            if (part >0){
                String toBePushed=formHundreds(part);
                stack.push(toBePushed);
            }
            else{
                stack.push(EMPTY);
            }
        }

        StringBuilder bldr = new StringBuilder();
        while (!stack.isEmpty()){
            String token=stack.pop();
            --htmbIndex;
            if (!EMPTY.equals(token)){
                bldr.append(token);
                if (htmbIndex>0 ){
                    bldr.append(HTMB[htmbIndex]);
                }

            }
        }

        return bldr.toString().trim();
    }

    private String formHundreds(int num){
        StringBuilder bldr = new StringBuilder();
        int underHundred = num%100;
        num /=100;
        if (num>0){
            bldr.append(UNDER_TWENTY[num]).append(HTMB[0]);
        }

        int teen = underHundred%20;
        underHundred /=10;

        if (teen>=10 && underHundred<2){
            // from 10 to 19
            bldr.append(UNDER_TWENTY[teen]);
        }
        else{
            if (underHundred>=2){
                bldr.append(ABOVE_TWENTY[underHundred-1]);
            }

            teen%=10;

            if (teen>0){
                bldr.append(UNDER_TWENTY[teen]);
            }

        }

        return bldr.toString();

    }
    public static void main (String[] args){
        int i = 12345;
        NumberToEnglishWords numberToEnglishWords = new NumberToEnglishWords();
        System.out.println(i + ": " + numberToEnglishWords.numberToWords(i));

        int j=123;
        System.out.println(j + ": " + numberToEnglishWords.numberToWords(j));

        int k=1234567;
        System.out.println(k + ": " + numberToEnglishWords.numberToWords(k));

        int l=1000000;
        System.out.println(l + ": " + numberToEnglishWords.numberToWords(l));

        int m=1000;
        System.out.println(m + ": " + numberToEnglishWords.numberToWords(m));

        int n=100010;
        System.out.println(n + ": " + numberToEnglishWords.numberToWords(n));

        int o=1000010;
        System.out.println(o + ": " + numberToEnglishWords.numberToWords(o));

        int p=3055000;
        System.out.println(p + ": " + numberToEnglishWords.numberToWords(p));
    }
}
