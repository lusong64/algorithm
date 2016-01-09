package org.song.java8;

/**
 * Purpose of this class is to
 */
public class NumberOfCodings {
    private static final int START=1;
    private static final int END=26;
    /**
     * Should use Dynamic Programming
     * ways[k] : how many way of decode for a string ending with cs[k];
     * ways[n] = ways[0] // if wasy[0] !=0 and cs[1-n] is legit
     *          + ways[1] // if ways [1] != 0 and cs[2-n] is legit.
     *          ....
     *          + ways[n-1] // if ways [n-1] !=0 and cs[n] is legit;
     */
    public int numDecodings(String s) {
        if (s ==null || s.length() <1){
            return 0;
        }
        char[] cs = s.toCharArray();
        int[] ways = new int[cs.length];
        for (int i=0; i<ways.length; i++){
            if (cs[i] != '0'){
                ways[i]=1;
            }
        }
        for (int i=1; i< cs.length; i++){
            int score=0;

            int candidate=0;

            if ( i-1>=0 && cs[i-1] !='0'){

                candidate = (cs[i-1] - '0')*10 + (cs[i]-'0');
                if (candidate>=START && candidate<=END){
                    score += i-2>=0?ways[i-2]:1;
                }
            }

            if (cs[i] != '0' && ways[i-1]>0){
                candidate = cs[i] - '0';
                if (candidate >=START && candidate <=END){
                    score += ways[i-1];
                }
            }

            ways[i]=score;
        }
        return ways[cs.length-1];
    }

    public static void main(String[] args){
        String s = "11";
        NumberOfCodings nc = new NumberOfCodings();
        System.out.println("The result is: " + nc.numDecodings(s));
    }
}
