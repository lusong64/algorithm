package org.song.java8;

/**
 * Purpose of this class is to
 */
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if ((s==null || s.length() == 0) && (p==null || p.length()==0)){
            return true;
        }
        else if (s==null || s.length() == 0 || p == null || p.length() ==0){
            return false;
        }

        int sI=0;
        int star=-1;
        int pI=0;
        char [] cs = s.toCharArray();
        char [] cp = p.toCharArray();
        int matchIndex =-1;

        while (sI< cs.length){
            if (pI<cp.length && (cp[pI] == '?' || cp[pI] == cs[sI])){
                ++sI;
                ++pI;
            }
            else if(pI<cp.length && cp[pI] == '*'){
                matchIndex = sI;
                star = pI;
                pI++;
            }
            else if (star >=0){
                matchIndex ++;
                sI=matchIndex;
                pI=star+1;
            }
            else{
                return false;
            }
        }

        while (pI<cp.length && cp[pI] == '*'){
            pI++;
        }
        return pI >= cp.length;
    }

    public static void main(String[] args){
        WildcardMatching wm = new WildcardMatching();
        String s1 = "aa";
        String p1 = "*";
        System.out.println(wm.isMatch(s1, p1));
    }
}
