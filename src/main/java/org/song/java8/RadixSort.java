package org.song.java8;

import java.util.Arrays;

/**
 * Purpose of this class is to
 */
public class RadixSort {
    public void sort (int[] a){
        if (a==null || a.length<=1){
            return;
        }

        int max = a[0];
        for (int i=1; i<a.length; i++){
            max = Math.max(max, a[i]);
        }

        int ten=1;
        int[] aux = new int[a.length];
        int [] count = new int[10];
        while (ten<=max){
            for (int i=0; i<a.length; i++){
                count[(a[i]/ten)%10]++;
            }
            for (int i=1; i<count.length; i++){
                count[i]+=count[i-1];
            }
            for (int i=a.length-1; i>=0; i--){
                aux[--count[(a[i]/ten)%10]] = a[i];
            }

            for (int i=0; i<a.length; i++){
                a[i] = aux[i];
            }

            for (int i=0; i<count.length; i++){
                count[i]=0;
            }
            ten*=10;
        }

    }

    private static void test(int[] a, RadixSort rs){
        System.out.println("Before RadixSort: " + Arrays.toString(a));
        rs.sort(a);
        System.out.println("After RadixSort: " + Arrays.toString(a));
    }


    public static void main(String[] args){
        RadixSort rs = new RadixSort();
        test(new int[]{397, 3, 33, 397, 666, 533, 459, 92, 32}, rs);
        test(new int[]{1}, rs);
        test(new int[]{1, 2, 3, 5, 7, 1000, 9, 3, 4, 2}, rs);
    }
}
