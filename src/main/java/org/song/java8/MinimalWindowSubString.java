package org.song.java8;

/**
 * Purpose of this class is to
 */
public class MinimalWindowSubString {

    private static final int BASE=65;

    public String minWindow(String s, String t) {

        if (s==null || s.length()==0 ||t==null || t.length()==0
            || s.length()<t.length()){
            return "";
        }

        int [] tMap= new int[128];
        for (char c : t.toCharArray()){
            tMap[c-BASE] ++;
        }

        int[] workingMap= new int[128];
        int start=0;
        int end =0;
        int minS=0;
        int minE=0;
        int minLen = Integer.MAX_VALUE;
        int validCount = addToMap(s.charAt(start), workingMap, tMap);

        while (end <= s.length()-1){

            while(start <= end && validCount == t.length()){
                int newLen = end-start+1;
                if (minLen > newLen){
                    minS=start;
                    minE=end;
                    minLen=newLen;
                }

                validCount-=removeFromMap(s.charAt(start), workingMap, tMap);
                start++;

            }
            ++end;
            if (end == s.length()){
                break;
            }
            validCount +=addToMap(s.charAt(end), workingMap, tMap);

        }

        return minLen==Integer.MAX_VALUE?"": s.substring(minS, minE+1);

    }

    private int addToMap(char c, int[] map, int[] tMap){

        int countFromT = tMap[c-BASE];
        if (countFromT <=0){
            return 0;
        }
        int count = (++map[c-BASE]);
        return countFromT>=count?1:0;
    }

    private int removeFromMap(char c, int[] map, int[] tMap){
        if (tMap[c-BASE] <= 0){
            return 0;
        }
        int count = map[c-BASE]--;

        int countFromT = tMap[c-BASE];
        return countFromT>=count?1:0;
    }

    public static void main(String[] args){
        String s = "ADOBECODEBANC";
        String t = "ABC";
        /*
        String s = "AA";
        String t = "AA";
        */
        MinimalWindowSubString ms = new MinimalWindowSubString();
        System.out.println("Abstract: " + ms.minWindow(s, t));
    }
}

