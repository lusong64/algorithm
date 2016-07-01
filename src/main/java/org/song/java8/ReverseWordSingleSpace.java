package org.song.java8;

/**
 * Purpose of this class is to
 */
public class ReverseWordSingleSpace {
    public String reverseWords(char[] s) {
        if (s == null || s.length<=1){
            return new String(s);
        }
        reverse (s, 0, s.length-1);
        int space=0;
        int start = 0;

        while (start<s.length && space < s.length){
            if (s[space] != ' '){
                space ++;
            }
            else{
                reverse(s, start, space-1);
                start = space+1;
                space = start;
            }

        }
        if (start < s.length){
            reverse(s, start, space-1);
        }

        return new String(s);

    }

    private void reverse(char[] s, int start, int end){
        while (start<end){
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start ++;
            end --;
        }
    }

    public static void main(String[] args){
        ReverseWordSingleSpace rw = new ReverseWordSingleSpace();
        String s1= "a b";
        System.out.println("Reversing " + s1 + " : " + rw.reverseWords(s1.toCharArray()));

    }
}
