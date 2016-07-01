package org.song.java8;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Purpose of this class is to
 */
public class UglyNumberII {
    private final static int[] multi={2, 3, 5};
    public int nthUglyNumber(int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);

        int res = 0;
        int k=0;
        Set<Integer> set = new HashSet<>(9);
        while (k++<n){
            res = pq.poll();
            while (!pq.isEmpty() && res == pq.peek()){
                pq.poll();
            }
            for (int i=0; i<multi.length; i++){
                pq.add(res*multi[i]);
            }

        }
        return res;

    }

    public static void main(String[] args){
        UglyNumberII ug = new UglyNumberII();
        int n1=4;
        System.out.println("The "+n1 +"th ugly # is " + ug.nthUglyNumber(n1));

        int n2=5;
        System.out.println("The "+n2 +"th ugly # is " + ug.nthUglyNumber(n2));

        int n3=1;
        System.out.println("The "+n3 +"th ugly # is " + ug.nthUglyNumber(n3));

        int n4=7;
        System.out.println("The " + n4 + "th ugly # is " + ug.nthUglyNumber(n4));

        int n5=18;
        System.out.println("The " + n5 + "th ugly # is " + ug.nthUglyNumber(n5));

    }
}
