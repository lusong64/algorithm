package org.song.java8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {

    private static class Count {
        private int count = 0;

        public void increase() {
            count ++;
        }

        public int count() {
            return count;
        }
    }

    public int leastBricks(int[][] wall) {
        if (wall == null || wall.length == 0) {
            return 0;
        }

        Map<Integer, Count> map = new HashMap<>();

        for (int[] row : wall) {
            int keyByLength = 0;
            int index = -1;
            for (Integer i : row) {
                keyByLength += i;
                if (++ index < row.length - 1) {
                    map.computeIfAbsent(keyByLength, k -> new Count()).increase();
                }
            }
        }

        int height = wall.length;
        int min = height;
        for (Count count: map.values()) {
            min = Math.min(min, height - count.count());
        }
        return min;
    }

    public static void main(String[] args) {
        int[][] wall = new int[][] {{1,2,2,1},{3,1,2},{1,3,2},{2,4},{3,1,2},{1,3,1,1}};

        BrickWall brickWall = new BrickWall();
        System.out.print("least: " + brickWall.leastBricks(wall));
    }
}
