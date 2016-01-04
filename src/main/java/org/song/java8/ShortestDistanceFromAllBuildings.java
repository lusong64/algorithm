package org.song.java8;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Purpose of this class is to
 */
public class ShortestDistanceFromAllBuildings {
    private final static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static int shortestDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        // Initialize building list and accessibility matrix `grid`
        List<Tuple> buildings = new ArrayList<>();
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    buildings.add(new Tuple(j, i, 0));
                }
                grid[i][j] = -grid[i][j];
            }
        // BFS from every building
        System.out.println("After initialization the grid is:\n");
        print2DArray(grid);
        System.out.println("And the buildings is: " + buildings);
        for (int k = 0; k < buildings.size(); ++k) {
            bfs(buildings.get(k), k, dist, grid, m, n);
            System.out.println("\nRound " + k + ": The dist is: \n");
            print2DArray(dist);
            System.out.println("And the grid is: \n");
            print2DArray(grid);
            System.out.println("-----------------------------------------: \n");
        }

        // Find the minimum distance
        int ans = -1;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == buildings.size() && (ans < 0 || dist[i][j] < ans))
                    ans = dist[i][j];
        return ans;
    }

    public static void bfs(Tuple root, int k, int[][] dist, int[][] grid, int m, int n) {
        Queue<Tuple> q = new ArrayDeque<>();
        q.add(root);
        System.out.println("\n k=" + k + " and root tuple is: "+root);
        while (!q.isEmpty()) {
            Tuple b = q.poll();
            dist[b.x][b.y] += b.dist;
            for (int i = 0; i < 4; ++i) {
                int x = b.x + dx[i], y = b.y + dy[i];
                if (y >= 0 && x >= 0 && x < m && y < n && grid[x][y] == k) {
                    grid[x][y] = k + 1;
                    q.offer(new Tuple(y, x, b.dist + 1));
                }
            }
        }
    }

    public static void main (String[] args){
        int [][] grid = {{1,0,2,0,1}, {0,0,0,0,0}, {0,0,1,0,0}};
        System.out.print("Before shortest, the grid is\n");
        print2DArray(grid);
        int shortest = shortestDistance(grid);
        System.out.print("After shortest, the grid is\n");
        print2DArray(grid);

        System.out.println("The shortest distance is " + shortest);
    }

    private static void print2DArray(int[][] grid){

        for (int i=0; i<grid.length; i++){
            System.out.println(Arrays.toString(grid[i]));
        }
    }
}

class Tuple {
    public int y;
    public int x;
    public int dist;

    public Tuple(int y, int x, int dist) {
        this.y = y;
        this.x = x;
        this.dist = dist;
    }

    @Override
    public String toString(){
        return String.format("[x=%d, y=%d, dist=%d]", x, y, dist);
    }
}