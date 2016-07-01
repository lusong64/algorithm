package org.song.java8;

/**
 * Purpose of this class is to
 */
public class SmallestRectangle {
    private char[][] image;
    // https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/
    public int minArea(char[][] iImage, int x, int y) {
        image = iImage;
        int m = image.length, n = image[0].length;
        int left = searchColumns(0, y, 0, m, true);
        int right = searchColumns(y + 1, n, 0, m, false);
        int top = searchRows(0, x, left, right, true);
        int bottom = searchRows(x + 1, m, left, right, false);
        return (right - left) * (bottom - top);
    }
    private int searchColumns(int i, int j, int top, int bottom, boolean opt) {
        while (i != j) {
            int k = top, mid = (i + j) / 2;
            while (k < bottom && image[k][mid] == '0'){
                ++k;
            }
            if (k < bottom == opt){
                j = mid;
            }
            else{
                i = mid + 1;
            }
        }
        return i;
    }
    private int searchRows(int i, int j, int left, int right, boolean opt) {
        while (i != j) {
            int k = left, mid = (i + j) / 2;
            while (k < right && image[mid][k] == '0'){
                ++k;
            }
            if (k < right == opt){
                j = mid;
            }
            else{
                i = mid + 1;
            }
        }
        return i;
    }

    public static void main (String[] args){
        SmallestRectangle sr = new SmallestRectangle();
        char[][] image1 = {"0010".toCharArray(),"0110".toCharArray(),"0100".toCharArray()};
        int x1=0;
        int y1=2;
        System.out.println(sr.minArea(image1, x1, y1));

        char[][] image2 = {"1".toCharArray(),"1".toCharArray()};
        int x2=1;
        int y2=0;
        System.out.println(sr.minArea(image2, x2, y2));
    }
}
