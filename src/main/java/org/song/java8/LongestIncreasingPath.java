package org.song.java8;

import java.util.PriorityQueue;

/**
 * Purpose of this class is to
 */
public class LongestIncreasingPath {

    private static final int[] dx={0, 0, 1, -1};
    private static final int[] dy={1, -1, 0, 0};
    public int longestIncreasingPath(int[][] matrix) {
        int result =0;
        if (matrix == null || matrix.length==0){
            return result;
        }

        int[][] scoreBoard= new int[matrix.length][matrix[0].length];
        boolean [][] visiting = new boolean[matrix.length][matrix[0].length];
        PriorityQueue<Point> q = new PriorityQueue<>((p1, p2)->p1.value - p2.value);
        // Finding the smallest element and start from there.
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                q.add(new Point(i, j, matrix[i][j]));
            }
        }

        while (!q.isEmpty()){
            Point p = q.poll();

            if (scoreBoard[p.x][p.y] !=0){
                // this point is on a path that has been explored.
                continue;

            }
            int temp = dfs(matrix, scoreBoard, visiting, p.x, p.y );
            if (temp>result){
                result=temp;
            }

        }

        return result;
    }

    private int dfs(int[][] matrix, int[][] scoreBoard,
                    boolean[][] visiting,
                    int startX, int startY ){


        int currentValue = matrix[startX][startY];
        visiting[startX][startY] = true;

        int maxLength=1;
        for (int i=0; i<dx.length; i++){
            int nextX= startX + dx[i];
            int nextY= startY + dy[i];
            if (nextX<0 || nextX > matrix.length-1 || nextY<0 || nextY>matrix[0].length-1){
                continue;
            }
            if (matrix[nextX][nextY] > currentValue
                && !visiting[nextX][nextY] ){
                int length=0;
                if (scoreBoard[nextX][nextY] !=0){
                    length = 1+scoreBoard[nextX][nextY];
                }
                else{
                    length = 1+ dfs(matrix, scoreBoard, visiting,  nextX, nextY );
                }

                if (length > maxLength){
                    maxLength = length;
                }
            }


        }

        visiting[startX][startY] = false;
        scoreBoard[startX][startY] = maxLength;
        return maxLength;
    }

    private static class Point{
        private final int x;
        private final int y;
        private final int value;
        public Point(int x, int y, int value){
            this.x=x;
            this.y=y;
            this.value=value;
        }
    }

    public static void main(String[] args){
        int [][]nums1 = {
        {9,9,4},
        {6,6,8},
        {2,1,1}
        };

        int [][] nums2 = {
        {3,4,5},
        {3,2,6},
        {2,2,1}
        };

        int [][] nums3 = {
            {7,6,1,1},
            {2,7,6,0},
            {1,3,5,1},
            {6,6,3,2}};

        int [] [] nums4= {
            {0,1,2,3,4,5,6,7,8,9},
            {19,18,17,16,15,14,13,12,11,10},
            {20,21,22,23,24,25,26,27,28,29},
            {39,38,37,36,35,34,33,32,31,30},
            {40,41,42,43,44,45,46,47,48,49},
            {59,58,57,56,55,54,53,52,51,50},
            {60,61,62,63,64,65,66,67,68,69},
            {79,78,77,76,75,74,73,72,71,70},
            {80,81,82,83,84,85,86,87,88,89},
            {99,98,97,96,95,94,93,92,91,90},
            {100,101,102,103,104,105,106,107,108,109},
            {119,118,117,116,115,114,113,112,111,110},
            {120,121,122,123,124,125,126,127,128,129},
            {139,138,137,136,135,134,133,132,131,130},
            {0,0,0,0,0,0,0,0,0,0}
        };
        LongestIncreasingPath lp = new LongestIncreasingPath();
        System.out.println("The max increasing Path for nums1 is: " + lp.longestIncreasingPath(nums1));
        System.out.println("The max increasing Path for nums2 is: " + lp.longestIncreasingPath(nums2));
        System.out.println("The max increasing Path for nums3 is: " + lp.longestIncreasingPath(nums3));
        System.out.println("The max increasing Path for nums4 is: " + lp.longestIncreasingPath(nums4));

    }
}
