package org.song.java8;

/**
 * Purpose of this class is to
 */
public class PalindromeNumber {

    public static boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        long num = x;
        long revert = 0;
        while (num>0){
            revert = revert *10 + num%10;
            num/=10;
        }

        return revert == (long)x;
    }

    public static void main(String[] args){
        //boolean is = isPalindrome(-2147483648);
        boolean is = isPalindrome(1000021);
        System.out.println("result is " + is);

    }
}
