package org.song.java8;

/**
 * Purpose of this class is to
 */

public class CountingBit1 {
    public int hammingWeight(int n) {
        int count =0;
        if (n == Integer.MIN_VALUE){
            return 1;
        }
        else if ((n&Integer.MIN_VALUE) != 0){
            count = (n&1)!=0?1:0;
            n = (n>>>1);

        }


        while (n>0){
            n = n & (n-1);
            count++;
        }
        return count;
    }

    public static void main(String[] args){
        CountingBit1 countingBit1 = new CountingBit1();
        System.out.println(countingBit1.hammingWeight( Integer.MAX_VALUE | Integer.MIN_VALUE));
        System.out.println(countingBit1.hammingWeight( Integer.MIN_VALUE));
        System.out.println(countingBit1.hammingWeight( Integer.MAX_VALUE));
        System.out.println(countingBit1.hammingWeight( 0b10101010101010101010101010101010));
    }
}
