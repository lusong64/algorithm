package org.song.java8;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Purpose of this class is to
 */
public class ConcatSubString {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new LinkedList<>();
        if (words == null || words.length==0){
            return list;
        }
        int wLen=words[0].length();
        if (s==null || s.length()<wLen){
            return list;
        }

        Map<String, Integer> dic = new HashMap<>();

        for (String w : words){
            Integer count = dic.get(w);
            if (count == null){
                count = Integer.valueOf(1);
            }
            else{
                count = Integer.valueOf(count.intValue()+1);
            }
            dic.put(w, count);
        }

        Map<String, Queue<Integer>> working = initWorkingMap(dic);

        int start = -1;
        int i=0;
        int count = 0;
        while (i < s.length()-wLen+1){
            if (count == words.length){
                list.add(Integer.valueOf(start));
                String firstWord = s.substring(start, start+wLen);
                int[] meta = decrease(working, firstWord, wLen, dic, s);
                start = meta[0];
                count = meta[1];
            }

            String word = s.substring(i, i+wLen);
            Integer countInDic = dic.get(word);
            if (countInDic != null){
                if (start <0){
                    start = i;
                }

                Queue<Integer> q = working.get(word);
                q.offer(Integer.valueOf(i));
                if (q.size() > countInDic.intValue()){
                    // restart
                    int [] meta = decrease(working, word, wLen, dic, s );
                    start = meta[0];
                    count= meta[1];
                }
                else{
                    count++;
                }
                i+=wLen;

            }
            else{
                if (count >0){
                    clear(working);
                    start =-1;
                    count=0;
                }
                i++;
            }
        }

        if (count == words.length){
            list.add(Integer.valueOf(start));
        }
        return list;
    }

    private Map<String, Queue<Integer>> initWorkingMap(Map<String, Integer> dic){
        Map<String, Queue<Integer>> working = new HashMap<>();
        for (String s : dic.keySet()){
            working.put(s, new LinkedList<>());
        }
        return working;
    }

    private void clear(Map<String, Queue<Integer>> working){
        for (Queue<Integer> q : working.values()){
            q.clear();
        }
    }
    private int[] decrease(Map<String, Queue<Integer>> working, String key, int wLen, Map<String, Integer> dic, String s){
        Queue<Integer>  keyQ = working.get(key);
        int index = keyQ.poll().intValue()+wLen;

        /*
        while(index<s.length()-wLen+1 && !dic.containsKey(s.substring(index, index+wLen))){
           index++;
        }
        */
        int count= 0;
        for (Queue<Integer> q : working.values()){
            while (!q.isEmpty() && q.peek().intValue()<index){
                q.poll();
            }
            count += q.size();
        }
        int[] result = new int[2];
        result[0] = count>0?index:-1;
        result[1] = count;
        return result;
    }


    public static void main(String[] args){
        ConcatSubString css = new ConcatSubString();
        String s0 = "barfoothefoobarman";
        String[] words_0 = {"foo","bar"};
        System.out.println(css.findSubstring(s0, words_0));
        String s1 = "mississippi";
        String[] words_1 = {"is"};
        System.out.println(css.findSubstring(s1, words_1));
        String s2 = "wordgoodgoodgoodbestword";
        String[] words_2= {"word","good","best","good"};
        System.out.println(css.findSubstring(s2, words_2));

        String s3 = "foobarfoobar";
        String[] words_3= {"foo","bar"};
        System.out.println(css.findSubstring(s3, words_3));

        String s4 = "aaa";
        String[] words_4= {"a","b"};
        System.out.println(css.findSubstring(s4, words_4));

        String s5 = "barfoofoobarthefoobarman";
        String[] words_5={"bar","foo","the"};
        System.out.println(css.findSubstring(s5, words_5));

        String s6 = "aaaaaaaa";
        String[] words_6 = {"aa","aa","aa"};
        System.out.println(css.findSubstring(s6, words_6));
    }
}
