package org.song.java8;

import java.util.Arrays;
import java.util.Iterator;

/**
 * You are given an array of n elements [1,2,....n]. For example {3,2,1,6,7,4,5}.
 Now we create a signature of this array by comparing every consecutive pir of elements. If they increase, write I else write D. For example for the above array, the signature would be "DDIIDI". The signature thus has a length of N-1. Now the question is given a signature, compute the lexicographically smallest permutation of [1,2,....n]. Write the below function in language of your choice.

 vector* FindPermute(const string& signature);
 */
public class FindPermute {
    public int[] findPermute(String pattern){
        if (pattern == null || pattern.length()<=0){
            return null;
        }
        int [] res = new int [pattern.length()+1];

        char[] cs = pattern.toCharArray();
        int i=0;
        for (; i<res.length; i++){
            res[i]=i+1;
        }

        i=0;
        int dCount=0;
        while (i< cs.length){
            if (cs[i] == 'i'){
                if (dCount>0){
                    revert(res, i - dCount, i);
                    dCount=0;
                }
            }
            else {
                dCount++;
            }
            i++;
        }
        if (dCount>0){
            revert(res, res.length - dCount-1, res.length - 1); ;
        }
        return res;
    }

    private void revert(int[] res, int l, int r){
        int temp=0;
        while (l<r){
            temp = res[l];
            res[l] = res[r];
            res[r] = temp;
            l++;
            r--;
        }
    }

    private static void test(String pattern, FindPermute fp){
        System.out.println("pattern: "+pattern);
        System.out.println( "array: "+ Arrays.toString(fp.findPermute(pattern)));
    }

    public static void main(String[] args){
        FindPermute fp = new FindPermute();
        test("i", fp);
        test("iii", fp);
        test("ddddd", fp);
        test("d", fp);
        test("iidd", fp);
        test("ididdi", fp);
        test("iiddi", fp);

    }

    private static class YourIterator <E> implements Iterator<E> {
        private final Iterator<E> it;
        E nextElem;
        public YourIterator(Iterator<E> sourceIterator) {
            this.it = sourceIterator;
            getNext();
        }

        private void getNext(){

            nextElem=null;
            while (it.hasNext()){
                nextElem=it.next();
                if (nextElem!=null){
                    break;
                }
            }

        }

        @Override
        public E next() {
            E toBeReturn = nextElem;
            getNext();
            return toBeReturn;
        }

        @Override
        public boolean hasNext() {
            return nextElem!=null;
        }
    }
}
