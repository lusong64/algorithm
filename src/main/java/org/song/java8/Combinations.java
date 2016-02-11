package org.song.java8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Purpose of this class is to
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {

        if (n==0 || k==0){
            return new ArrayList<List<Integer>> ();
        }

        List<List<Integer>> result = new LinkedList<>();
        boolean [] used = new boolean[n+1];
        combHelper(n, 1, result, used, new HashSet<Integer>(), k);
        return result;
    }

    private void combHelper(int n, int start, List<List<Integer>> res, boolean[] used, Set<Integer> work, int target){
        if (work.size()==target){
            res.add(new ArrayList(work));
            return;
        }

        for (int i=start; i<=n; i++){
            if (!used[i]){
                used[i] =true;
                Integer theI = Integer.valueOf(i);
                work.add(theI);
                combHelper(n, i + 1, res, used, work, target);
                used[i] = false;
                work.remove(theI);
            }
        }

    }


    public static void main(String[] args){
        Combinations cs = new Combinations();
        int n1=4;
        int k1=2;
        System.out.println(n1 + "->" + k1 + ":" + cs.combine(n1, k1));

        int n2=13;
        int k2=3;
        System.out.println(n2 + "->" + k2 + ":" + cs.combine(n2, k2));
    }
}
