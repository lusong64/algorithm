package org.song.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * Purpose of this class is to
 */
public class TwoSum {
    private final Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    public void add(int number){
        Integer key = Integer.valueOf(number);
        Integer value = map.get(key);
        if (value == null){
            map.put(key, Integer.valueOf(1));
        }
        else{
            map.put(key, Integer.valueOf(value.intValue()+1));
        }
    }

    public boolean find(int value){
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            int key = entry.getKey().intValue();
            Integer target = Integer.valueOf(value -key);
            Integer numberOfTarget = map.get(target);
            if (numberOfTarget == null){
                continue;
            }
            if (target.intValue() != key || target.intValue() == key && numberOfTarget.intValue()>1){
                System.out.println("true;");
                return true;
            }
        }

        System.out.println("false;");
        return false;
    }

    public static void main(String[] args){
        TwoSum ts = new TwoSum();
        ts.add(1); ts.add(1);
        ts.find(0);
        ts.find(1);
        ts.find(2);
        ts.add(-1);
        ts.find(0);
        ts.find(1); ts.find(-2);
        ts.add(-1);
        ts.find(-2);
    }


}
