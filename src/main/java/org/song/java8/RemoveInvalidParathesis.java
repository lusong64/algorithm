package org.song.java8;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Purpose of this class is to
 */
public class RemoveInvalidParathesis {

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new LinkedList<>();
        if (s==null || s.trim().length() < 1){
            result.add("");
            return result;
        }

        s=s.trim();
        if (isValid(s)){
            result.add(s);
            return result;
        }
        // Counting total "(" and ")"
        int totalCount = 0;
        for (char c : s.toCharArray()){
            if (c == '(' || c== ')'){
                totalCount ++;
            }
        }

        Set<String> processed = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(s);
        while (!queue.isEmpty()){
            String word = queue.poll();
            if (processed.contains(word)){
                continue;
            }
            System.out.println("Processing: " + word);
            processed.add(word);

            for (int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                if (c != '(' && c!= ')'){
                    continue;
                }
                else{
                    String removedOne = word.substring(0,i).concat(word.substring(i+1));
                    if (!processed.contains(removedOne)){
                        if (isValid(removedOne)){
                            result.add(removedOne);
                            processed.add(removedOne);
                            System.out.println("Adding " + removedOne);
                        }
                        else{
                            queue.add(removedOne);
                        }

                    }
                }
            }
            System.out.println("Queue: " + queue);
            System.out.println("--------------------------");
            if (!result.isEmpty()){
                break;
            }
        }

        if (result.isEmpty()){
            result.add("");
        }
        return result;

    }

    private boolean isValid(String s){
        int countLeft=0;
        int countRight=0;

        for (char c: s.toCharArray()){
            if (c == '('){
                countLeft ++;
            }
            else if (c==')'){
                countRight++;
            }

            if (countLeft < countRight){
                return false;
            }
        }
        return countLeft == countRight;
    }

    public static void main (String[] args){
        RemoveInvalidParathesis r = new RemoveInvalidParathesis();
        String s = "(((k()((";
        System.out.println(r.removeInvalidParentheses(s));

    }

}
