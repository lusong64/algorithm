package org.song.java8;

import java.util.Arrays;

/**
 * Purpose of this class is to
 */
public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }
        Arrays.sort(nums);
        int n=nums.length;
        int[] temp = new int[n];

        int l=((n&1)==1)?n>>>1:(n>>>1)-1;
        int mid = l;
        int r=nums.length-1;
        int k=0;
        while (l>=0 && k<temp.length){
            temp[k++]=nums[l--];
            if (r>mid){
                temp[k++]=nums[r--];
            }

        }
        for (int i=0; i<n; i++){
            nums[i] = temp[i];
        }
    }

    public static void test(int[] a, WiggleSortII ws){
        System.out.println("Before wiggle sort: "+Arrays.toString(a));
        ws.wiggleSort(a);
        System.out.println("After wiggle sort: "+Arrays.toString(a));
        System.out.println("----------------------------");
        System.out.println();

    }
    public static void main (String[] args){
        WiggleSortII ws=new WiggleSortII();
        test(new int[]{1,5,1,1,6,4}, ws);
        test(new int[]{1,3,2,2,3,1}, ws);
        test(new int[]{1,3,2,2,2,3,1}, ws);
    }
}
