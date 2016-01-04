package org.song.java8;

/**
 * Purpose of this class is to
 */
public class ReverseNumber {
    public static int reverse(int x) {
        boolean positive = x>0;
        long num = (long)x;

        if (num >= Integer.MAX_VALUE||num<=Integer.MIN_VALUE){
            return 0;
        }
        num = positive?num:0-num;
        long revert = 0;
        while (num>0){
            revert = revert *10 + num%10;
            num/=10;
        }

        if (revert>=Integer.MAX_VALUE || (0L-revert)<=Integer.MIN_VALUE){
            return 0;
        }
        return positive?(int)revert:0-(int)revert;
    }

    public static void main(String[] args){
        //System.out.println("reversing "+1534236469 + ": "+reverse(1534236469));
        System.out.println("reversing "+(-1563847412) + ": "+reverse(-1563847412));
    }
}
