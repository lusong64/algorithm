package org.song.java8;

import java.util.Arrays;
import java.util.Random;

/**
 * Purpose of this class is to
 */
public class Sort {

    public void quickSort(int [] a){
        if (a == null || a.length<=1){
            return;
        }

        Random random = new Random();

        quickSortHelper(a, 0, a.length-1, random);
    }

    private void quickSortHelper(int [] a, int l, int r, Random random){
        if (l>=r){
            return;
        }

        // both are working
        //int index = Math.abs(random.nextInt()%(r-l+1))+l;
        int index = random.nextInt(r-l+1)+l;
        swap(a, index, r);

        int low = l;
        int pivot = a[r];

        int m = l;
        while (m<r){
            if (a[m] <= pivot){
                swap(a, l++, m++);
            }
            else{
                m++;
            }
        }

        swap(a, l, r);

        quickSortHelper(a, low, l-1, random);
        quickSortHelper(a, l+1, r, random);
    }

    private void swap(int[] a, int i1, int i2){
        if (i1==i2){
            return;
        }
        int temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }


    public static void test(int [] a, Sort sort){
        System.out.println("Array is "+ Arrays.toString(a));
        sort.quickSort(a);
        System.out.println("after the sorting, the Array is "+ Arrays.toString(a));

    }

    public static void main(String[] args){
        Sort sort = new Sort();
        int[] a1 = {4, 3, 2, 0, -1, 2, 8, 9, 3, 4, 6};
        int[] a2 = {2};
        int[] a3 = {2, 2, 2, 2};
        test(a1, sort);
        test(a2, sort);
        test(a3, sort);
    }
}
