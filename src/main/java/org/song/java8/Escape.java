package org.song.java8;

import java.util.Arrays;

/**
 * Purpose of this class is to
 */
public class Escape {

    public static void main(String[] args){
        String s = "3.5.4.6";
        String [] vWithoutEscape = s.split(".");
        String [] vWithEscape = s.split("\\.");

        System.out.println("Without escaping: " + Arrays.toString(vWithoutEscape));
        System.out.println("With escaping: " + Arrays.toString(vWithEscape));
        int v0=-1;
        System.out.println(String.format("unsigned shift %x: %x", v0, v0>>>31 ));
        System.out.println(String.format("signed shift %x: %x", v0, v0>>31 ));
        int v1 = Integer.MAX_VALUE;
        System.out.println(String.format("unsigned shift %x: %x", v1, v1>>>30 ));
        System.out.println(String.format("signed shift %x: %x", v1, v1>>30 ));
    }
}
