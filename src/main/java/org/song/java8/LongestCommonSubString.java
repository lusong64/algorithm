package org.song.java8;

/**
 * Purpose of this class is to
 */
public class LongestCommonSubString {

    public static void main(String[] args){
        String s1="MZJAWXU";
        String s2="XMJYAUZ";

        /*
        String s1="AGGTAB";
        String s2="GXTXAYB";
        */

        String lcs = findLcs(s1, s2);
        System.out.println("The LCS of "+s1 + " and " + s2 + " is: "+lcs);
    }

    private static String findLcs(String s1, String s2){
        String result = "";
        if (s1 == null || s1.isEmpty()){
            return result;
        }

        if (s2 == null || s2.isEmpty()){
            return result;
        }

        int lcs[][] = new int[s1.length()+1][s2.length()+1];
        char[] s1C = s1.toCharArray();
        char[] s2C = s2.toCharArray();

        for (int i=0; i<=s1.length(); i++){
            for (int j=0; j<=s2.length(); j++){
                if (i==0 || j==0){
                    lcs[i][j]=0;
                }
                else{
                    if (s1C[i-1] == s2C[j-1]){
                        lcs[i][j] = 1 + lcs[i-1][j-1];
                    }
                    else{
                        lcs[i][j] = Integer.max(lcs[i-1][j], lcs[i][j-1]);
                    }
                }
            }
        }

        // Composing the String
        int index=lcs[s1C.length][s2C.length];
        char[] resultArray = new char[index];
        int i=s1C.length;
        int j=s2C.length;

        while (i>0 && j>0){
            if (s1C[i-1] == s2C[j-1]){
                resultArray[--index]=s1C[i-1];
                i--;
                j--;
            }
            else if (lcs[i-1][j] > lcs[i][j-1]){
                i--;
            }
            else{
                j--;
            }

        }


        result = String.valueOf(resultArray);
        return result;
    }
}
