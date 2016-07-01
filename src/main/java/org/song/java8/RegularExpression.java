package org.song.java8;


/**
 * Purpose of this class is to show DP and Recursion way to do regular expression match
 */
public class RegularExpression {

    public boolean isMatch(String s, String p) {
        if (p==null || p.length()==0){
            return s==null || s.length() ==0;
        }

        if (p.length()>=2 && p.charAt(1)=='*'){
            // not using this * with the previous one
            return isMatch(s, p.substring(2)) ||
            //  using it
            !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p);
        }
        else{

            return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
        }


    }

    /**
     * DP version
     */
    public boolean isMatchDP(String s, String p) {
        if ((s == null || s.length()==0) && (p==null || p.length()==0) ){
            return true;
        }

        int ls = s.length();
        int lp = p.length();
        char [] cs = s.toCharArray();
        char [] cp = p.toCharArray();
        boolean [][] score = new boolean [lp+1][ls+1];
        score[0][0]=true;

        for (int i=1; i<=lp; i++){
            score[i][0]=i>=2&&score[i-2][0]&&cp[i-2]!='*'&&cp[i-1]=='*';
            for (int j=1; j<=ls; j++){
                if (cp[i-1] != '*'){
                    // compare the cp[i-1] with cs[j-1] if score[i-1][j-1] is legitimate and true;
                    score[i][j] = score[(i-1)][j-1] && (cp[i-1] == '.' || cp[i-1] == cs[j-1]);
                }
                else{
                    // for *, either
                    // 1. make cp[i-2] and c[i-1] zero appearance and score[i-2][j] is true, the following must be true:
                    // cp[i-3]  == cs[i-3] --> score[i-2][j];
                    // 2. or the cp[i-1]* already meets cs[j-2] and cp[i-1]* meets cs[j-1]
                    score[i][j] = (i>=2 && score[i-2][j])  // make cp[j-2] zero appearance and score[i][j-2]
                        || (j>0 && score[i][j-1] && (cp[i-2] == '.'|| cp[i-2] == cs[j-1]));
                }
            }
        }

        return score[lp][ls];
    }

    private static void test(RegularExpression re, String s, String p){
        System.out.println("The match of " + s + " with Pattern " + p + " is " + re.isMatch(s, p));
    }

    public static void main (String[] args){
        RegularExpression re = new RegularExpression();
        test(re, "aaa", ".*");
        test(re, "aab", "c*a*b");

    }

}
