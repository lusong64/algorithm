package org.song.java8;

/**
 * Purpose of this class is to
 */
public class Division {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }

        if (divisor == 0){
            return Integer.MAX_VALUE;
        }

        int result =0;

        boolean sign = (divisor>0 && dividend>0) || (divisor < 0 && dividend<0);


        long div  = dividend>0?dividend:-dividend;

        long dis = divisor>0?divisor:-divisor;

        if (dis==1){
            return sign?(int)div:(int)(-div);
        }
        else if (div < dis){
            return 0;
        }
        else if (div==dis){
            return sign?1:-1;
        }



        while (div >= dis){
            int power =32;
            long pr = dis<<power;
            while (pr > div){
                pr >>>=1;
                --power;
            }
            result += 1 << (power);
            div -= pr;
        }

        return sign?result:-result;

    }

    public static void main (String[] args){
        int dividend = -2147483648;
        int divisor = -1;

        Division division = new Division();
        System.out.println(dividend+"/"+divisor+"=" + division.divide(dividend, divisor));
    }
}
