package org.song.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Purpose of this class is to
 */
public class GrayCode {
    /*
    public List<Integer> grayCode(int n) {
        if (n==0){
            return Arrays.asList(new Integer[]{0});
        }

        int [] result = new int [1<<n];
        Set<Integer> added = new HashSet<>();
        added.add(Integer.valueOf(0));
        helper(result, 0, 0, added);

        return Arrays.stream(result)
            .boxed()
            .collect(Collectors.toList());
    }
    */

    public List<Integer> grayCode(int n) {
        if (n==0){
            return Arrays.asList(new Integer[]{0});
        }

        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i=0; i<n; i++){
            int size = res.size();
            for (int k=size-1; k>=0; k--){
                res.add(res.get(k) | 1<<i);
            }
        }

        return res;
    }
    private boolean helper(int[] res, int lastIndex, int n, Set<Integer> added){
        if (lastIndex >= res.length-1){
            return true;
        }

        // fliping
        int target = res[lastIndex];
        for (int i=0; i<n; i++){
            int next = target ^ 1<<i;
            if (added.contains(Integer.valueOf(next))){
                continue;
            }
            boolean isValid = false;
            if (i<n-1 && validGrayCode(target, next)){
                isValid = validGrayCode(target, next);
            }
            else{
                isValid = validGrayCode(target, next) && validGrayCode(res[0], next);
            }
            if (isValid){
                added.add(next);
                res[lastIndex+1] = next;
                if (helper(res, lastIndex+1, n, added)){
                    return true;
                }
                else{
                    res[lastIndex+1] = 0;
                    added.remove(next);
                }
            }


        }
        return false;
    }

    private boolean validGrayCode(int code1, int code2){
        int diff = code1 ^ code2;
        return diff >0 && (diff & (diff-1)) == 0;
    }

    public static void main (String[] args){
        GrayCode gc = new GrayCode();
        int n1 = 1;
        System.out.println(n1+": " + gc.grayCode(n1));
        int n2 = 2;
        System.out.println(n2+": " + gc.grayCode(n2));
        int n3 = 10;
        System.out.println(n3+": " + gc.grayCode(n3));

    }
}
