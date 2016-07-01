package org.song.java8;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Purpose of this class is to
 */
public class RepeatedDnaSequence {
    private static final int MASK=0x3FFFFFFF; // length == 30
    private static final int SHIFT=3;
    private static final int SINGLE_MASK=0x7;

    // https://leetcode.com/discuss/24478/i-did-it-in-10-lines-of-c
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new LinkedList<>();
        if (s==null || s.length()<10){
            return res;
        }
        int bitMap =0;
        Map<Integer, Integer> map = new HashMap<>();
        int i=0;
        char [] cs = s.toCharArray();
        while (i<=9){
            bitMap = bitMap<<SHIFT & MASK | (cs[i] & SINGLE_MASK);
            i++;
        }
        map.put(Integer.valueOf(bitMap), 0);
        while (i<cs.length){
            bitMap = bitMap<<SHIFT & MASK | (cs[i] & SINGLE_MASK);
            Integer key = Integer.valueOf(bitMap);
            if (map.containsKey(key)){
                int index = map.get(key).intValue();
                res.add(s.substring(index, index+10));
            }
            else{
                map.put(key, i-9);
            }
            i++;
        }

        return res;

    }

    public static void main(String[] args){
        RepeatedDnaSequence rds = new RepeatedDnaSequence();
        String dna1 = "AAAAAAAAAAA";
        System.out.println(dna1 + ": " + rds.findRepeatedDnaSequences(dna1));

    }
}
