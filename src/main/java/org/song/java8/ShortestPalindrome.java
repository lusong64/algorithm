package org.song.java8;

/**
 * Purpose of this class is to
 */
public class ShortestPalindrome {

    /**
     * Inspired by https://leetcode.com/discuss/59727/java-solution-using-manacher-algorithm
     * and all those Palindrome: http://articles.leetcode.com/longest-palindromic-substring-part-ii
     */
    public String shortestPalindrome(String s) {
        if (s == null || s.length() <=1){
            return s;
        }
        String suffix = findLongestPalindrome(s);
        if (suffix.length() == s.length()){
            return s;
        }
        StringBuilder bldr = new StringBuilder();
        for (int i=suffix.length(); i<s.length(); i++){
            bldr.insert(0, s.charAt(i));
        }
        return bldr.append(s).toString();
    }

    private String findLongestPalindrome(String s){
        char[] ms = makeMancherString(s);
        int [] p = new int[ms.length];
        // initial all character != '#' to 1
        for (int i=1; i<p.length; i+=2){
            p[i]=1;

        }

        int c = 0;
        int r = 0;
        for (int i=1; i<ms.length-1 && c+r<ms.length; i++){
            int iMirror = c-(i-c);
            p[i] = r>i?Math.min(r-i, p[iMirror]):0;
            while (i-p[i]-1 >=0 && i+p[i]+1 < ms.length && ms[i-p[i]-1] == ms[i+p[i]+1]){
                p[i]++;
            }
            if (i+p[i] > r){
                c=i;
                r=i+p[i];
            }
        }

        // find the center
        int maxLen=0;
        int center=0;
        for (int i=0; i<p.length-1; i++){
            if (p[i] > maxLen && i-p[i] == 0){
                center = i;
                maxLen=p[i];
            }
        }

        maxLen = Math.max(1, maxLen);
        int start = (center - maxLen -1)/2;
        start = Math.max(0, start);
        return s.substring(start, start + maxLen);

    }

    private char[] makeMancherString(String s){
        char [] cs = new char [2*s.length()+1];
        int j = 0;
        for (int i=0; i<cs.length; i++ ){
            if ((i&1) == 0){
                cs[i] = '#';
            }
            else{
                cs[i] = s.charAt(j++);
            }
        }
        return cs;
    }

    public static void main(String[] args){
        ShortestPalindrome sp = new ShortestPalindrome();
        String s1 = "ba";
        System.out.println(s1+": "+ sp.shortestPalindrome(s1));
        String s2 = "aba";
        System.out.println(s2+": "+ sp.shortestPalindrome(s2));
    }

}
