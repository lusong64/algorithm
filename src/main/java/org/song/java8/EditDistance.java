package org.song.java8;

import java.util.Arrays;

/**
 * Purpose of this class is to
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1==null || word1.length()==0){
            return word2!=null?word2.length():0;
        }
        if (word2==null || word2.length()==0){
            return word1!=null?word1.length():0;
        }
        int len1=word1.length();
        int len2=word2.length();
        int[][] d = new int[2][len2+1];
        char [] cs1 = word1.toCharArray();
        char [] cs2 = word2.toCharArray();

        for (int j=0; j<d[0].length; j++){
            d[0][j] = j;
        }
        for (int i=0; i<len1; i++){
            d[(i+1)&1][0]=i+1;
            for (int j=0; j<len2; j++){
                if (cs1[i]==cs2[j]){
                    d[(i+1)&1][j+1]=d[i&1][j];
                }
                else{
                    d[(i+1)&1][j+1]= 1+ Math.min(d[i & 1][j], Math.min(d[i & 1][j + 1], d[(i + 1) & 1][j]));
                }
            }

        }

        return d[(len1)&1][len2];

    }

    public int [][] setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length==0){
            return matrix;
        }

        boolean firstColZero = false;
        boolean firstRowZero = false;

        // init firstColZero
        for (int i=0; i<matrix.length; i++){
            if (matrix[i][0] ==0){
                firstColZero=true;
                break;
            }
        }
        // init firstRowZero
        for (int j=0; j<matrix[0].length; j++){
            if (matrix[0][j] ==0){
                firstRowZero=true;
                break;
            }
        }

        // using first row and col to record whether the correspondent row or col
        // should be set to zero or not.
        for (int i=1; i<matrix.length; i++){
            for (int j=1; j<matrix[0].length; j++){
                if (matrix[i][j]==0){
                    matrix[0][j] = matrix[i][0] = 0;
                }
            }
        }
        // setting rows
        for (int i=1; i<matrix.length; i++){
            if (matrix[i][0] == 0){
                for (int j=1; j<matrix[0].length; j++){
                    matrix[i][j] = 0;
                }
            }
        }

        // setting cols
        for (int j=1; j<matrix[0].length; j++){
            if (matrix[0][j] ==0){
                for (int i=1; i<matrix.length; i++){
                    matrix[i][j]=0;
                }
            }
        }

        if (firstRowZero){
            for (int j=0; j<matrix[0].length; j++){
                matrix[0][j]=0;
            }
        }

        if (firstColZero){
            for (int i=0; i<matrix.length; i++){
                matrix[i][0]=0;
            }
        }
        return matrix;
    }

    public static void main(String[] args){
        String s1_1="sea";
        String s2_1="eat";

        EditDistance es = new EditDistance();

        System.out.println(s1_1 + "->" + s2_1 +" = " +  es.minDistance(s1_1, s2_1));

        int[][] matrix1= {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
        System.out.println(Arrays.deepToString(es.setZeroes(matrix1)));

    }
}
