package org.song.java8;

import java.util.LinkedList;
import java.util.List;

/**
 * Purpose of this class is to
 */
public class NumberOfIslandsII {

    private final static java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^(\\+|-)*((\\d*\\.\\d+)|(\\d+(\\.\\d*)?))([eE][+-]?(\\d)+)?$");
    public boolean isNumber(String s) {
        if (s==null){
            return false;
        }
        return pattern.matcher(s.trim()).matches();
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new LinkedList<>();
        if (m<=0 || n<=0 || positions == null || positions.length==0){
            return result;
        }

        Islands islands = new Islands(m, n);
        for (int i=0; i<positions.length; i++ ){
            islands.add(positions[i]);
            result.add(Integer.valueOf(islands.islandcount));
        }

        return result;
    }

    private static class Islands{
        private static final int[] dx = {0, 0, 1, -1};
        private static final int[] dy = {1, -1, 0, 0};

        private final Island[][] islands;
        private int islandcount = 0;

        public Islands(int m, int n) {
            islands = new Island[m][n];
        }

        public void add(int[] position) {
            int x = position[0];
            int y = position[1];
            Island island = null;
            if (islands[x][y] == null) {
                island = new Island(x, y);
                islands[x][y] = island;
            }
            else {
                return;
            }

            int combineCount = 0;
            boolean isLoneIsland = true;
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < islands.length && ny>=0 && ny<islands[0].length){
                    Island neighbour = islands[nx][ny];
                    if (neighbour == null){
                        continue;
                    }

                    Island pn = neighbour.findParent();
                    Island p = island.findParent();
                    if (!p.equals(pn)){
                        if (p.childCount >1){
                            combineCount++;
                        }
                        isLoneIsland = false;
                        Island.union(p, pn);

                    }

                }

            }

            if (isLoneIsland){
                islandcount ++;
            }
            else {
                islandcount -= combineCount;
            }

        }
    }

    private static class Island{
        Island parent;
        final private int x;
        final private int y;
        int childCount;

        public Island(int x, int y){
            this.x=x;
            this.y=y;
            this.parent = this;
            childCount=1;
        }

        public static void union(Island i1, Island i2){
            Island p1 = i1.findParent();
            Island p2 = i2.findParent();

            if (!p1.equals(p2)){
                if (p1.childCount>p2.childCount){
                    p2.setParent(p1);
                }
                else{
                    p1.setParent(p2);
                }
            }
        }

        public void setParent(Island p){
            this.parent = p;
            p.childCount+=this.childCount;
        }

        public Island findParent (){
            Island p = this;
            while (!p.equals(p.parent)){
                Island granpa = p.parent.parent;
                // path compression
                p.parent = granpa;
                p = p.parent;
            }
            return p;
        }

        @Override
        public int hashCode(){
            int result = 1;
            result = result * 13 + x;
            result = result * 13 + y;
            return result;
        }

        @Override
        public boolean equals(Object o){
            if (!(o instanceof Island)){
                return false;
            }
            Island i = (Island) o;
            return x==i.x && y==i.y;
        }

        public boolean hasSameParent(Island i){
            if (i == null){
                return false;
            }
            Island pi = i.findParent();
            Island p = findParent();
            return p.equals(pi);
        }
    }

    public static void main (String[] args){

        NumberOfIslandsII numberOfIslandsII = new NumberOfIslandsII();
        int[][] positions_1 ={{0,0},{0,1},{1,2},{2,1}};
        int m1=3;
        int n1=3;

        System.out.println(numberOfIslandsII.numIslands2(m1, n1, positions_1));

        int[][] positions_2 ={{0,0},{1,1},{0,1}};
        int m2=2;
        int n2=2;

        System.out.println(numberOfIslandsII.numIslands2(m2, n2, positions_2));

        System.out.println(numberOfIslandsII.isNumber("3"));
        System.out.println(numberOfIslandsII.isNumber("e"));
        System.out.println(numberOfIslandsII.isNumber(".1"));
        System.out.println(numberOfIslandsII.isNumber("e9"));
        System.out.println(numberOfIslandsII.isNumber("3."));
        System.out.println(numberOfIslandsII.isNumber("3.."));
        System.out.println(numberOfIslandsII.isNumber("6ee69"));

    }
}
