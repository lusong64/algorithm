package org.song.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Purpose of this class is to
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length==0){
            return new ArrayList<List<String>>();
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs){
            String signature = getSignature(s);
            List<String> list = map.get(signature);
            if (list == null){
                list = new LinkedList<String>();
                map.put(signature, list);
            }
            list.add(s);
        }
        List <List<String>> result = new ArrayList<>(map.keySet().size());
        result.addAll(map.values());
        return result;
    }

    private String getSignature(String s){
        char [] cs = s.toCharArray();
        Arrays.sort(cs);
        return String.valueOf(cs);
    }

    public static void main(String[] args){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams ga = new GroupAnagrams();
        System.out.println(Arrays.toString(strs) + "--->" + ga.groupAnagrams(strs));
    }
}
