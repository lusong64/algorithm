package org.song.java8;

import java.util.Arrays;

/**
 * Purpose of this class is to
 */
public class ArrayProblems {
    public int firstMissingPositive(int[] a) {
        if (a == null || a.length==0){
            return 1;
        }

        int i=0;
        while(i<a.length){
            if(a[i] >0 && a[i]<a.length &&
                a[a[i]-1] != a[i] && a[i] != i+1){
                swap(a, i, a[i]-1);
            }
            else{
                i++;
            }

        }

        i=0;
        while (i<a.length){
            if (a[i] != i+1){
                return i+1;
            }
            ++i;
        }
        return a.length +1;
    }

    private void swap(int[] a, int i, int j){
        if (i==j){
            return;
        }
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;
        int common_bits=0;
        for (int i=0; i<nums.length; i++){
            twos |= ones & nums[i];
            ones^=nums[i];
            common_bits = ~(ones & twos);
            ones &=common_bits;
            twos &=common_bits;
        }
        return ones;

    }

    /**
     * Got the idea from http://www.geeksforgeeks.org/find-the-element-that-appears-once/
     * @param nums
     * @param k
     * @return
     */
    public int findSingleNumberNotAppearingKTimes(int [] nums, int k){
        int result =0;
        for (int i=0; i<32; i++){
            int count =0;
            int andResult =0;
            int x=1<<i;
            for (int j=0; j<nums.length; j++){
                andResult = x & nums[j];
                if (andResult !=0){
                    count++;
                }
            }
            if (count%k !=0){
                result |= x;
            }
        }
        return result;
    }

    public static void main(String[] args){
        ArrayProblems as = new ArrayProblems();

        int[] a1={0,1,2};
        System.out.println("The first missing positive of " + Arrays.toString(a1) + "is: " + as.firstMissingPositive(a1));

        int[] a2={-1,4,2,1,9,10};
        System.out.println("The first missing positive of " + Arrays.toString(a2) + "is: " + as.firstMissingPositive(a2));

        int[] a3 = {5, 5, 1, 5 };
        System.out.println("The single number is: " + as.singleNumber(a3));
        System.out.println("The single number is: " + as.findSingleNumberNotAppearingKTimes(a3, 3));

        int[] a4 = {5, 5, 5, 5, 5, 2, 2, 3, 2, 2, 2 };
        System.out.println("The single number is: " + as.findSingleNumberNotAppearingKTimes(a4, 5));
    }
}
