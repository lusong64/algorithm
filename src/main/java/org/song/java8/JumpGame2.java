package org.song.java8;

import java.util.Arrays;

/**
 * Purpose of this class is to
 */
public class JumpGame2 {
    public int jump(int[] A) {
        int count = 0, max = 0;
        for (int i = 0, nextMax = 0; i <= max && i < A.length - 1; i++) {
            nextMax = Math.max(nextMax, i + A[i]);
            if (i == max) {
                max = nextMax;
                count++;
            }
        }
        // if there is no way to get to the end, return -1
        return max >= A.length - 1 ? count : -1;
    }

    private static void test(JumpGame2 j2, int[] a){
        System.out.println("The min steps for " + Arrays.toString(a) + " is " + j2.jump(a));
    }
    public static void main(String[] args){
        JumpGame2 j2 = new JumpGame2();
        test(j2, new int[]{2,3,1,1,4});


    }
}
