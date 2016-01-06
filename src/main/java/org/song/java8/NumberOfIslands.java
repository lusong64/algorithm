package org.song.java8;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Purpose of this class is to
 */
public class NumberOfIslands {
    private static final int[] DX = {0, 0, -1, 1};
    private static final int[] DY = {-1, 1, 0, 0};

    /**
     * BFS
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length<1){
            return 0;
        }
        // 0 and 1 is used for island and water
        // using this Id to count how many islands found and also identify
        // whether we visited the grid element already.
        int islandId = 1;

        Queue<Pair> q = new LinkedList<>();
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[0].length; j++){
                if (grid[i][j] != '1'){
                    continue;
                }
                ++islandId;
                char id = (char)('0' + islandId);
                grid[i][j]=id;
                q.add(new Pair(i, j));
                while (!q.isEmpty()){
                    Pair p = q.poll();
                    for (int k=0; k<DX.length; k++){
                        int kx = p.x + DX[k];
                        int ky = p.y + DY[k];
                        if ( kx < 0 || kx >= grid.length
                            || ky < 0 || ky >= grid[0].length
                            || grid[kx][ky] != '1'){
                            continue;
                        }

                        grid[kx][ky] = id;
                        q.add(new Pair(kx, ky));
                    }
                }
            }

        }

        return islandId -1;

    }

    private static class Pair{
        private final int x;
        private final int y;
        public Pair(int x, int y){
            this.x=x;
            this.y=y;
        }
    }


    public static void main (String[] args){

        char[][] grid = {{'1'}};
        NumberOfIslands nm = new NumberOfIslands();
        System.out.println("The number of island is " + nm.numIslands(grid));
    }
}
