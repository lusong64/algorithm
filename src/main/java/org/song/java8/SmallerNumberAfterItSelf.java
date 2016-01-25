package org.song.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose of this class is to
 */
public class SmallerNumberAfterItSelf {
    /**
     * I am going to use CountAndMergeSort to do the Divide and Conquer.
     */
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length==0){
            return new ArrayList<Integer>();
        }
        Count[] counts = new Count[nums.length];
        for (int i=0; i<counts.length; i++){
            counts[i]= new Count(i, nums[i]);

        }
        mergeCount(nums, counts, 0, nums.length-1);

        // reusing nums to store the count.
        for (Count c : counts){
            nums[c.index]= Integer.valueOf(c.count);
        }

        List<Integer> result = new ArrayList<>();
        for (int i=0; i<nums.length; i++){
            result.add(Integer.valueOf(nums[i]));
        }
        return result;
    }

    private void mergeCount(int[] nums, Count[] counts, int start, int end){
        if (start >= end){
            return;
        }
        int mid = start + (end-start)/2;
        mergeCount(nums, counts, start, mid);
        mergeCount(nums, counts, mid+1, end);

        Count [] cache = new Count[end-start+1];
        int cIndex = 0;
        int j=mid+1;
        int smallerCount=0;
        for (int i=start; i<=mid; i++){

            while (j<=end && counts[i].value > counts[j].value){
                smallerCount++;
                cache[cIndex++]=counts[j++];
            }
            counts[i].count+=smallerCount;
            cache[cIndex++] = counts[i];
        }


        while (j<=end && cIndex<cache.length){
            cache[cIndex++] = counts[j++];
        }

        // copy back
        for (int i=0; i<cache.length; i++){
            counts[start+i]=cache[i];
        }

    }

    private final static class Count{
        private final int index;
        private final int value;
        private int count=0;

        public Count(int index, int value){
            this.index=index;
            this.value=value;
        }
    }

    public static void main(String[] args){
        int[] nums = {26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};

        SmallerNumberAfterItSelf smallerNumberAfterItSelf = new SmallerNumberAfterItSelf();
        List<Integer> result = smallerNumberAfterItSelf.countSmaller(nums);
        System.out.println("The result is: " + result);
    }
}
