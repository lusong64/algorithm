package org.song.java8;

/**
 * Purpose of this class is to
 */
public class MultiplyString {
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length()<1 || num2 == null || num2.length() <1){
            return "0";
        }

        char [] result = new char[num1.length()+num2.length()+1];
        for (int i=0; i< result.length; i++){
            result[i] = '0';
        }
        char [] nc1 = num1.toCharArray();
        char [] nc2 = num2.toCharArray();
        int carry =0;
        int tail =0;
        int sum =0;
        int ni=0;
        for (int i=nc2.length-1; i>=0; i--){
            ni = nc2[i] -'0';
            for (int j=nc1.length-1; j>=0; j--){
                tail = i+j+2;
                sum = (result[tail] - '0') + ni * (nc1[j]-'0') + carry;
                result[tail] = table[sum%10];
                carry = sum/10;
            }

            while(carry != 0){
                tail --;
                sum = (result[tail] -'0') + carry;
                result[tail] = table[sum%10];
                carry=sum/10;

            }
        }
        return String.valueOf(result, tail, result.length-tail);
    }

    public static void main(String[] args){
        MultiplyString ms = new MultiplyString();
        String s1="999";
        String s2="999";

        System.out.println(s1 + "X" + s2 + "=" + ms.multiply(s1, s2));
    }
}
