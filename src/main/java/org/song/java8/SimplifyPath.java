package org.song.java8;

import java.util.Stack;

/**
 * Purpose of this class is to
 */
public class SimplifyPath {
    private final static String UP="..";
    private final static String CUR = ".";
    public String simplifyPath(String path) {
        if (path == null || path.trim().length()==0){
            return "";
        }
        Stack<String> stack = new Stack<>();

        String[] tokens = path.split("/");
        for (String s : tokens){
            s = s.trim();
            if (s.isEmpty()){
                continue;
            }
            if (CUR.equals(s)){
                continue;
            }
            else if (UP.equals(s)){
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else{
                stack.push(s);
            }
        }
        StringBuilder bldr = new StringBuilder();

        while (!stack.isEmpty()){
            bldr.insert(0, stack.pop());
            bldr.insert(0, "/");
        }

        return bldr.length()>0?bldr.toString():"/";
    }

    public static void main (String[] args){
        SimplifyPath sp = new SimplifyPath();
        String s1 = "/...";
        System.out.println(s1 + "-->" + sp.simplifyPath(s1));

        String s2 = "/..";
        System.out.println(s2 + "-->" + sp.simplifyPath(s2));
    }
}
