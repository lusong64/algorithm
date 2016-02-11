package org.song.java8;

/**
 * Purpose of this class is to
 */
public class ReverseWord {
    public String reverseWords(String s) {
        if (s==null || s.length()==0){
            return s;
        }
        s = s.trim();
        if (s.length() == 0){
            return "";
        }
        else if (s.indexOf(" ") == -1){
            return s;
        }

        s=reverse(s);
        String[] ss = s.split("[ ]+");
        StringBuilder bldr = new StringBuilder();
        for (int i=0; i<ss.length; i++){
            bldr.append(reverse(ss[i].trim())).append(" ");
        }
        return bldr.toString().trim();
    }

    private String reverse(String str){
        char [] cs = str.toCharArray();
        int s = 0;
        int e = cs.length-1;
        while (s<e){
            char t = cs[s];
            cs[s] = cs[e];
            cs[e] = t;
            s++;
            e--;
        }
        return new String(cs);
    }

    public static void main(String[] args){
        ReverseWord rw = new ReverseWord();
        String s1 = "   a   b ";
        System.out.println("reverting ["+ s1 + "] is "+ rw.reverseWords(s1));
        String s2 = "hello world!";
        System.out.println("reverting ["+ s2 + "] is "+ rw.reverseWords(s2));
    }
}
