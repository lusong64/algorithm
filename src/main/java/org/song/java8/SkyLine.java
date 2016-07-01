package org.song.java8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * Purpose of this class is to
 */
public class SkyLine {
    public List<int[]> getSkyline(int[][] buildings) {

        List<int[]> views = new LinkedList<>();
        if (buildings == null || buildings.length==0 || buildings[0].length==0){
            return views;
        }

        Building[] blds = new Building[buildings.length<<1];
        int i=0;
        for (int [] building : buildings){
            blds[i++] = new Building(building[2], building[0], BuildingStatus.LeftEdge);
            blds[i++] = new Building(building[2], building[1], BuildingStatus.RightEdge);
        }
        Arrays.sort(blds, (b1, b2)-> b1.x!=b2.x?
                b1.x-b2.x:
                b1.status.rank-b2.status.rank);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int currentHeight = 0;
        int j=0;
        heightMap.put(0, 1);
        for (i=0; i< blds.length; i=j ){
            for (j=i; j<blds.length && blds[i].x == blds[j].x; j++){
                Integer height = blds[j].height;
                // left is to add this height into map
                if (blds[j].status == BuildingStatus.LeftEdge){
                    if (heightMap.containsKey(height)){
                        Integer count = Integer.valueOf(heightMap.get(height).intValue()+1);
                        heightMap.put(height, count);
                    }
                    else{
                        heightMap.put(height, Integer.valueOf(1));
                    }
                }
                else{
                    // right edge
                    int count = heightMap.get(height).intValue();

                    if (count == 1){
                        heightMap.remove(height);
                    }
                    else{
                        heightMap.put(height, Integer.valueOf(count-1));
                    }

                }
            }

            int maxHeight = heightMap.lastKey();
            if  (currentHeight != maxHeight){
                views.add(new int[]{blds[i].x, maxHeight});
                currentHeight = maxHeight;
            }
        }
        return views;
    }

    private static enum BuildingStatus{
        LeftEdge(0), RightEdge(1);
        private final int rank;
        private BuildingStatus(int rank){
            this.rank = rank;
        }
    }
    private static class Building{
        private final Integer height;
        private final int x;
        private final BuildingStatus status;
        public Building(int height, int x, BuildingStatus status){
            this.height = Integer.valueOf(height);
            this.x = x;
            this.status = status;
        }

        @Override
        public String toString(){
            StringBuilder bldr = new StringBuilder();
            return bldr.append("x=").append(x)
                .append(", height=").append(height)
                .append(", status=").append(status).toString();

        }

    }

    public static void main(String[] args){

        SkyLine sl = new SkyLine();
        int[][] skylines1 = {{0,2,3},{2,5,3}};
        System.out.println(Arrays.deepToString(skylines1) + ": ");
        List<int[]> res1 = sl.getSkyline(skylines1);
        for (int[] res : res1){
            System.out.println(Arrays.toString(res));
        }

        int[][] skylines2 = {{1,2,1},{1,2,2},{1,2,3}};
        System.out.println(Arrays.deepToString(skylines2) + ": ");
        List<int[]> res2 = sl.getSkyline(skylines2);
        for (int[] res : res2){
            System.out.println(Arrays.toString(res));
        }
    }
}
