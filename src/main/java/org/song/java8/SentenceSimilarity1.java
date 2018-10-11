package org.song.java8;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SentenceSimilarity1 {

    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null && words2 == null) {
            return true;
        } else if (words1 == null || words2 == null) {
            return false;
        }
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, Set<String>> map = new HashMap<>();
        for (String[] pair : pairs){
            map.computeIfAbsent(pair[0], s -> new HashSet<>()).add(pair[1]);
            map.computeIfAbsent(pair[1], s -> new HashSet<>()).add(pair[0]);
        }

        for (int i = 0; i < words1.length; i ++){
            if (words1[i].equals(words2[i])){
                continue;
            }
            if (!map.containsKey(words1[i]) || !map.containsKey(words2[i])) {
                return false;
            }
            if (!map.get(words2[i]).contains(words1[i])
                    && !map.get(words1[i]).contains(words2[i]) ) {
                System.out.println("i=" + i + " and words1: " + words1[i] + " words2: " + words2[i]);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
       /*
        String[] words1 = {"this","summer","thomas","get","actually","actually","rich","and","possess","the","actually",
                "great","and","fine","vehicle","every","morning","he","drives","one","nice","car","around","one",
                "great","city","to","have","single","super","excellent","super","as","his","brunch","but","he","only",
                "eat","single","few","fine","food","as","some","fruits","he","wants","to","eat","an","unique","and",
                "actually","healthy","life"};

        String[] words2 = {"this","summer","thomas","get","very","very","rich","and","possess","the","very","fine",
                "and","well","car","every","morning","he","drives","a","fine","car","around","unique","great","city",
                "to","take","any","really","wonderful","fruits","as","his","breakfast","but","he","only","drink","an",
                "few","excellent","breakfast","as","a","super","he","wants","to","drink","the","some","and","extremely",
                "healthy","life"};

        String[][] pairs = {{"good","nice"},{"good","excellent"},{"good","well"},{"good","great"},{"fine","nice"},
                {"fine","excellent"},{"fine","well"},{"fine","great"},{"wonderful","nice"},{"wonderful","excellent"},
                {"wonderful","well"},{"wonderful","great"},{"extraordinary","nice"},{"extraordinary","excellent"},
                {"extraordinary","well"},{"extraordinary","great"},{"one","a"},{"one","an"},{"one","unique"},
                {"one","any"},{"single","a"},{"single","an"},{"single","unique"},{"single","any"},{"the","a"},
                {"the","an"},{"the","unique"},{"the","any"},{"some","a"},{"some","an"},{"some","unique"},{"some","any"},
                {"car","vehicle"},{"car","automobile"},{"car","truck"},{"auto","vehicle"},{"auto","automobile"},
                {"auto","truck"},{"wagon","vehicle"},{"wagon","automobile"},{"wagon","truck"},{"have","take"},
                {"have","drink"},{"eat","take"},{"eat","drink"},{"entertain","take"},{"entertain","drink"},
                {"meal","lunch"},{"meal","dinner"},{"meal","breakfast"},{"meal","fruits"},{"super","lunch"},
                {"super","dinner"},{"super","breakfast"},{"super","fruits"},{"food","lunch"},{"food","dinner"},
                {"food","breakfast"},{"food","fruits"},{"brunch","lunch"},{"brunch","dinner"},{"brunch","breakfast"},
                {"brunch","fruits"},{"own","have"},{"own","possess"},{"keep","have"},{"keep","possess"},
                {"very","super"},{"very","actually"},{"really","super"},{"really","actually"},{"extremely","super"},
                {"extremely","actually"}};
                */

       String[] words1 = {"great","acting","skills"};
       String[] words2 = {"fine","painting","talent"};
       String[][] pairs = {{"great","fine"},{"drama","acting"},{"skills","talent"}};

       SentenceSimilarity1 s1 = new SentenceSimilarity1();
       s1.areSentencesSimilar(words1, words2, pairs);

    }
}
