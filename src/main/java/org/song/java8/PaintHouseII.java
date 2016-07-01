package org.song.java8;

/**
 * Purpose of this class is to
 */
public class PaintHouseII {
    //https://leetcode.com/discuss/60625/fast-dp-java-solution-runtime-o-nk-space-o-1
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length==0 || costs[0].length==0){
            return 0;
        }
        int prevMin=0;
        int preSecondMin=0;
        int preMinIndex=-1;
        for (int i=0; i<costs.length; i++){
            int val = 0;
            int min=Integer.MAX_VALUE;
            int secondMin=Integer.MAX_VALUE;
            int minIndex=-1;
            for (int j=0; j<costs[0].length; j++){
                val = costs[i][j] + (preMinIndex==j? preSecondMin : prevMin);
                if (min > val){
                    secondMin = min;
                    min=val;
                    minIndex=j;
                }
                else if (secondMin > val){
                    secondMin=val;
                }

            }
            prevMin=min;
            preSecondMin=secondMin;
            preMinIndex=minIndex;
        }
        return prevMin;
    }

    public static void main(String[] args){
        PaintHouseII pI = new PaintHouseII();
        int[][] costs1={{1,5,3},{2,9,4}};
        System.out.println(pI.minCostII(costs1));

        int[][] costs2 = {{4,16},{15,5},{18,17},{10,12},{14,10},{3,10},{2,11},{18,14},{9,1},{14,13}};
        System.out.println(pI.minCostII(costs2));
    }
}
