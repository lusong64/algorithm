package org.song.java8;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

/**
 * Purpose of this class is to
 */
public class Queues {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k==0 || nums==null || nums.length==0){
            return new int[0];
        }
        if (nums.length==k){
            int [] res = new int[1];
            res[0] = Integer.MIN_VALUE;
            for (int i=0; i<nums.length; i++){
                res[0] = Math.max(res[0], nums[i]);
            }
            return res;

        }
        if (k==1){
            return nums;
        }

        int [] res = new int[nums.length-k+1];
        Deque<Integer> maxQ = new ArrayDeque<>(k);
        Deque<Integer> q = new ArrayDeque<>(k);

        // init q with k-1 element without out put.
        int i=0;
        while (i<k){
            enqueue(maxQ, q, nums[i]);
            i++;
        }

        int j=0;
        while (i<nums.length){
            res[j++] = dequeue(maxQ, q);
            enqueue(maxQ, q, nums[i]);
            i++;
        }

        while(!q.isEmpty() && j<res.length){
            res[j++] = dequeue(maxQ, q);
        }


        return res;

    }

    private void enqueue(Deque<Integer>maxQ, Queue<Integer> q, int v){
        Integer val = Integer.valueOf(v);
        while (!maxQ.isEmpty() && maxQ.peekLast().intValue()<v){
            maxQ.removeLast();
        }
        maxQ.addLast(val);
        q.add(val);
    }

    private int dequeue(Deque<Integer>maxQ, Queue<Integer> q ){
        int max = maxQ.peekFirst();
        int v = q.poll().intValue();
        if (v >= max){
            maxQ.removeFirst();
        }
        return max;

    }

    public static void main(String[] args){
        Queues qs = new Queues();
        int [] nums1 = {7,2,4};
        int k1 = 2;
        System.out.println(Arrays.toString(nums1) + ": " + Arrays.toString(qs.maxSlidingWindow(nums1, k1)));

    }
}
