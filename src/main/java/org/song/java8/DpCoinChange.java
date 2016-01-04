package org.song.java8;

import java.util.Arrays;

/**
 * Purpose of this class is to
 */
public class DpCoinChange {

    /**
     * // noc(n) = min (noc(n-1), noc(n-2), noc(n-5),...);
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount==0){
            return 0;
        }
        if (coins == null || coins.length <1){
            return -1;
        }
        int [] noc = new int[amount+1];
        noc[0]=0;

        for (int i=1; i<=amount; i++){
            noc[i] = Integer.MAX_VALUE;
            for (int j=0; j<coins.length; j++){
                if (i-coins[j]>=0 && noc[i-coins[j]] != Integer.MAX_VALUE && noc[i] > noc[i-coins[j]] +1){
                    noc[i] = noc[i-coins[j]] +1;
                }
            }
            System.out.println(i + ": " + Arrays.toString(noc));
            System.out.println("----------------------\n");
        }

        System.out.println("the noc[amount]: "+noc[amount]);
        return noc[amount] == Integer.MAX_VALUE?-1:noc[amount];

    }

    public static void main (String[] args){
        int [] coins = {474, 83, 404, 3};
        int amount =264;
        coinChange(coins, amount);

    }
}
