package org.song.java8;

import java.util.*;

/**
 * Purpose of this class is to
 */
public class SubStringConcat {
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> res = new ArrayList<Integer>();
        if (S == null || L == null || L.length == 0) {
            return res;
        }
        int len = L[0].length(); // length of each word
        int totalLength=len*L.length;
        Set<String> set = new HashSet<>();
        for (String s : L){
            set.add(s);
        }

        // dp[i] contains the set that is the concat for all legit strings for s.sub ending with i
        Set<String>[] dp= new Set[S.length()+1];
        for (int i=0; i<dp.length; i++){
            dp[i]=new HashSet<>();
        }
        for (int i=len; i<dp.length; i+=len){

            String endWithI = S.substring(i-len, i);
            if (set.contains(endWithI)){
                // found a match,
                dp[i].addAll(dp[i-len]);
                dp[i].add(endWithI);
                if (dp[i].size()==set.size()){
                    res.add(i-totalLength);
                }
            }
        }
        return res;
    }

    private static void test(SubStringConcat ssc, String s, String[] ss){
        System.out.println("Finding substring from "+ s + " for set: "+ Arrays.toString(ss));
        System.out.println("The start of sub String are : " + ssc.findSubstring(s, ss));

    }

    public static void main(String[] args){
        SubStringConcat ssc = new SubStringConcat();
        test(ssc, "barfoothefoobarman", new String[]{"foo", "bar"});
    }

}
