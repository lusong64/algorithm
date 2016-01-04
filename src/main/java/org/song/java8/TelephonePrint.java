package org.song.java8;

import java.util.LinkedList;
import java.util.List;

/**
 * Purpose of this class is to
 */
public class TelephonePrint {
    private final static char[][] table= {{' '}, {}, {'a','b','c'},
        {'d','e','f'}, {'g','h','i'}, {'j','k','l'}, {'m','n','o'},
        {'p','q','r','s'}, {'t','u','v'}, {'w','x','y','z'}};

    public static List<String> letterCombinations(String digits) {

        List<String> result = new LinkedList<String>();
        if (digits == null || digits.isEmpty()){
            return result;
        }
        doBuild(result, digits, "", 0);
        return result;

    }

    private static void doBuild(List<String> result, String digits, String building, int i){

        int tableIndex = Integer.parseInt(digits.substring(i, i+1));
        if (i==digits.length()-1){
            if (table[tableIndex].length==0){
                // '1'
                result.add(building);
            }
            else{
                for (char c: table[tableIndex]){
                    result.add(String.format("%s%c", building, c));
                }

            }
            return;
        }

        if (table[tableIndex].length==0){
            doBuild(result, digits, building, i+1);
        }
        else{
            for (char c: table[tableIndex]){
                doBuild(result, digits, String.format("%s%c", building, c), i+1);
            }
        }

    }

    public static void main (String[] strings){
        List<String> result = letterCombinations("234");
        for (String s : result){
            System.out.println(s);
        }
    }
}
