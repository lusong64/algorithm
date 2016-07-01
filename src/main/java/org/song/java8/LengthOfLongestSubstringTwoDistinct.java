package org.song.java8;

import java.util.*;

/**
 * Purpose of this class is to
 */
public class LengthOfLongestSubstringTwoDistinct {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s==null || s.length()==0){
            return 0;
        }
        else if (s.length() == 1){
            return 1;
        }
        char [] cs = s.toCharArray();
        Map<Character, Deque<Integer>> map = new HashMap<>();
        int max=0;
        int start = 0;
        int end = 0;
        Character cStart=Character.valueOf(cs[start]);
        while (end < cs.length){
            Character c = Character.valueOf(cs[end]);
            if (map.entrySet().size() == 2 && !map.containsKey(c)){

                max = Math.max(max, end - start);
                Deque<Integer> startQ = map.get(cStart);
                Deque<Integer> otherQ = null;
                Character other = null;
                for (Map.Entry<Character, Deque<Integer>> entry : map.entrySet()) {
                    if (entry.getKey().equals(cStart)){
                        continue;
                    }
                    other=entry.getKey();
                    otherQ=entry.getValue();
                    break;
                }

                int threshold=0;
                if (otherQ.peekLast().intValue() > startQ.peekLast().intValue()){
                    map.remove(cStart);
                    threshold = startQ.peekLast().intValue();
                    while(!otherQ.isEmpty() && otherQ.peekFirst().intValue()<threshold){
                        otherQ.removeFirst();
                    }
                    start = otherQ.peekFirst().intValue();
                    cStart = other;
                }
                else{
                    map.remove(other);
                    threshold = otherQ.peekLast().intValue();
                    while(!startQ.isEmpty() && startQ.peekFirst().intValue()<threshold){
                        startQ.removeFirst();
                    }
                    start =startQ.peekFirst().intValue();
                }

                Deque<Integer> deque = new LinkedList<>();
                deque.add(end);
                map.put(c, deque);
            }
            else{
                Character cEnd = Character.valueOf(cs[end]);
                Deque<Integer> deque = map.get(cEnd);
                if (deque == null){
                    deque = new LinkedList<>();
                    map.put(cEnd, deque);
                }
                deque.add(end);
            }
            end ++;
        }

        if (map.size() <2){
            max = cs.length;
        }
        else if (map.size()==2){
            max = Math.max(max, cs.length-start);
        }


        return max;
    }

    public static void main(String[] args){
        LengthOfLongestSubstringTwoDistinct ll = new LengthOfLongestSubstringTwoDistinct();
        String s1= "ababffzzeee";
        System.out.println(s1 + ": " + ll.lengthOfLongestSubstringTwoDistinct(s1));
        String s2= "aa";
        System.out.println(s2 + ": " + ll.lengthOfLongestSubstringTwoDistinct(s2));
        String s3= "bacc";
        System.out.println(s3 + ": " + ll.lengthOfLongestSubstringTwoDistinct(s3));
        String s4= "abaccc";
        System.out.println(s4 + ": " + ll.lengthOfLongestSubstringTwoDistinct(s4));
    }
}
