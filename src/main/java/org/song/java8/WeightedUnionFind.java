package org.song.java8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Purpose of this class is to
 */
public class WeightedUnionFind {
    private static class DisjoinSet{
        private final int [] parents;
        private final int [] size;
        private final int n;
        private boolean hasCycle = false;
        private final int disJoinSetCount;

        public DisjoinSet(int n, int[][] edges){
            if (n<0){
                throw new IllegalArgumentException("n must not be null.");
            }
            this.n = n;
            parents = new int[n];
            size=new int[n];

            //init
            for (int i=0; i<n; i++){
                parents[i]= i;
                size[i] =1;
            }

            for (int i=0; i<edges.length; i++){
                if (edges[i].length !=2){
                    throw new IllegalArgumentException(String.format("edges[%d]:%s is illegal.", i, Arrays.toString(edges[i])));
                }
                int start = edges[i][0];
                int end=edges[i][1];

                int ps = findParent(start);
                int pe = findParent(end);
                if (ps == pe && !hasCycle){
                    hasCycle=true;
                }
                union(start, end);
            }

            // counting disjoin sets
            Set<Integer> parentSet = new HashSet<>();
            for (int i=0; i<n; i++){
                int p = findParent(i);
                parentSet.add(Integer.valueOf(p));
            }
            disJoinSetCount = parentSet.size();
        }

        private void validate(int x){
            if (x<0 || x>=n){
                throw new IllegalArgumentException(String.format("Invalid number: %d given the size of disjoin set is %d", x, n));
            }
        }
        private void setParent(int toBeParent, int toBeSon){
            parents[toBeSon] = toBeParent;
            size[toBeParent] += size[toBeSon];
        }

        public void union(int x, int y){
            validate(x);
            validate(y);
            int px = findParent(x);
            int py = findParent(y);

            if (px != py){
                if (size[px] <= size[py]){
                    setParent(py, px);
                }
                else{
                    setParent(px, py);
                }
            }
        }

        public int countDisjoinSet(){
            Set<Integer> parentSet = new HashSet<>();
            for (int i=0; i<n; i++){
                int p = findParent(i);
                parentSet.add(Integer.valueOf(p));
            }

            return parentSet.size();
        }



        public int findParent(int x){
            int p = x;
            while (parents[p] != p){
                // path compression
                parents[p] = parents[parents[p]];
                p = parents[p];
            }
            return p;
        }


    }

    public static void main (String [] args){
        int [][] edges1 =  {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        int [][] edges2 =  {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        DisjoinSet ds1 = new DisjoinSet(5, edges1);
        DisjoinSet ds2 = new DisjoinSet(5, edges2);

        System.out.println("ds1: hasCycle: "+ds1.hasCycle + " disjointSet count: " + ds1.countDisjoinSet());
        System.out.println("ds2: hasCycle: "+ds2.hasCycle + " disjointSet count: " + ds2.countDisjoinSet());
    }
}
