package org.song.java8;

import java.util.function.Function;

/**
 * Purpose of this class is to
 */
public class BineryIndexedTree2D {
    private static class Bit2D{
        private final int[][] bit;
        private final int[][] nums;

        public Bit2D(int [][] nums){
            this.nums=nums;
            bit = new int[nums.length+1][nums[0].length+1];
            for (int i=0; i<nums.length; i++){
                for (int j=0; j<nums[0].length; j++){
                    updateOrInit(i, j, nums[i][j], (val)-> val);
                }

            }
        }

        public void update(int i, int j, int value){
            updateOrInit(i, j, value, (val)->{
                int diff = val - nums[i][j];
                nums[i][j] = val;
                return diff;
            });
        }

        public int sumRegion(int row1, int col1, int row2, int col2){
            return getSum(row2, col2) + getSum(row1-1, col1-1) - getSum(row1-1, col2) - getSum(row2, col1-1);
        }

        private int getSum(int i, int j){
            int sum = 0;
            i++;
            while (i>0){
                int col = j+1;
                while (col>0){
                    sum+=bit[i][col];
                    col -= col & (-col);
                }
                i-= i & (-i);
            }
            return sum;
        }

        private void updateOrInit(int i, int j, int value, Function<Integer, Integer> consumeAndUpdater){
            int diff = consumeAndUpdater.apply(value);
            int xi=i+1;
            while (xi<bit.length){
                updateRow(xi, j, diff);
                xi += xi&(-xi);
            }
        }

        private void updateRow(int x, int j, int value){
            int yj = j+1;
            while (yj<bit[0].length){
                bit[x][yj]+=value;
                yj += yj & (-yj);
            }
        }
    }

    public static void main(String[] args){
        int [] [] matrix = {
        {3, 0, 1, 4, 2},
        {5, 6, 3, 2, 1},
        {1, 2, 0, 1, 5},
        {4, 1, 0, 1, 7},
        {1, 0, 3, 0, 5}
        };

        Bit2D bit2D = new Bit2D(matrix);
        int i1=2, i2=4, j1=1, j2=3;

        System.out.println("Result = "+ bit2D.sumRegion(i1, j1, i2, j2));
        bit2D.update(3, 2, 2);
        System.out.println("Result = "+ bit2D.sumRegion(i1, j1, i2, j2));

    }
}
