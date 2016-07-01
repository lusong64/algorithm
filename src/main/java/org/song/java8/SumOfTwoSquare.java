package org.song.java8;

/**
 * Purpose of this class is to
 */
public class SumOfTwoSquare {

    public boolean isSumOfTwoSquare(int n){
        if (n<=3){
            return n==2;
        }

        int[] work = new int[n];
        for (int i=0; i<n; i++){
            work[i]=-1;
        }

        for (int i=0; i<n>>>1; i++){
            int sqr=i*i;
            if (sqr>=n){
                break;
            }
            work[sqr] = i;
        }

        for (int i=4; i<=n; i++){
            int other = n-i;
            if (work[other] >0 && work[i]>0 ){
                return true;
            }

        }
        return false;
    }

    /*
    public boolean isSumOfTwoSquare(int n){
        if (n<=3){
            return n==2;
        }

        for (int i=4; i<=n; i++){
            int other = n-i;
            int otherRoot = (int) Math.sqrt(other);
            if (other != otherRoot * otherRoot){
                continue;
            }
            int iRoot = (int)Math.sqrt(i);
            if (iRoot * iRoot == i){
                return true;
            }


        }
        return false;
    }
    */

    private static void test(SumOfTwoSquare sts, int n){
        System.out.println("The number: "+n + " can " + (sts.isSumOfTwoSquare(n)?"":"not ") + "be formed by two sqr of numbers.");
    }

    public static void main (String[] args){
        SumOfTwoSquare sts = new SumOfTwoSquare();
        test(sts, 20);
        test(sts, 21);
        test(sts, 99);
        test(sts, 50);
        test(sts, 13);
        test(sts, Integer.MAX_VALUE);
    }
}
