package org.song.java8;

import java.util.HashSet;
import java.util.Set;

/**
 * Purpose of this class is to
 */
public class AndroidUnlockPattern {
    // for nine direction
    private static final int[] DX={-1, -1, -1, 0, 0, 1, 1, 1 };
    private static final int[] DY={-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    public int numberOfPatterns(int m, int n) {
        if (m<=0 || n<=0){
            return 0;
        }

        Set<String> res = new HashSet<>();
        boolean[][] visiting = new boolean[board.length][board[0].length];
        StringBuilder bldr = new StringBuilder();

        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                dfsFind(new Pair(i, j), board, visiting, bldr, res, m, n);

            }
        }

        return res.size();
    }

    private void dfsFind(Pair p, int[][] board, boolean [][] visiting, StringBuilder bldr, Set<String> res, int low, int high){


        int len = bldr.length();
        bldr.append(board[p.x][p.y]);

        if (bldr.length()>=low && bldr.length()<=high){
            String tobeAdded = bldr.toString();
            if (!res.contains(tobeAdded)){
                //System.out.println("Adding "+  tobeAdded);
                res.add(tobeAdded);

            }
        }

        if (bldr.length()==high){
            bldr.setLength(len);
            return;
        }


        visiting[p.x][p.y]=true;
        Set<Pair> neighbors = new HashSet<>();
        addToNeibors(p.x, p.y, neighbors, board, visiting, true);
        for (Pair child : neighbors){
            dfsFind(child, board, visiting, bldr, res, low, high);
        }
        visiting[p.x][p.y]=false;
        bldr.setLength(len);

    }

    private void addToNeibors(int px, int py, Set<Pair> neighbors, int[][] board, boolean[][] visiting, boolean getVisitingNeibbor){
        for (int i=0; i<DX.length; i++){
            int x=px+DX[i];
            int y=py+DY[i];
            if (x<0 || x>=board.length || y<0 || y>=board[0].length){
                continue;
            }

            if (!visiting[x][y]){
                neighbors.add(new Pair(x, y));
            }
            else if (getVisitingNeibbor){

                addToNeibors(x, y, neighbors, board, visiting, false);
            }
        }
    }

    private static class Pair{
        private final int x;
        private final int y;
        public Pair(int x, int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(Object o){
            if (o==this){
                return true;
            }
            if (!(o instanceof Pair)){
                return false;
            }
            Pair other = (Pair)o;
            return this.x==other.x && this.y==other.y;
        }

        @Override
        public int hashCode(){
            int result = 1;
            result = result *47 + x;
            result = result * 47 + y;
            return result;
        }
    }

    public static void main(String[] args){
        AndroidUnlockPattern aup = new AndroidUnlockPattern();
        System.out.println(aup.numberOfPatterns(1, 1));
        System.out.println(aup.numberOfPatterns(9, 9));
        System.out.println(aup.numberOfPatterns(1, 9));
    }
}
