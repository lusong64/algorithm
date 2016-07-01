package org.song.java8;

import java.util.*;

/**
 * Purpose of this class is to
 */
public class IntervalMinus {
    private enum PointPosition{
        Start, End;
    }
    private final static char A='a';
    private final static char B='b';

    private static class Point{
        private final char group;
        private final int timestamp;
        private final PointPosition pp;

        public Point(char group, int timestamp, PointPosition pp ){
            this.group = group;
            this.timestamp = timestamp;
            this.pp = pp;
        }

        @Override
        public String toString(){
            StringBuilder bldr = new StringBuilder("[");
            bldr.append(group)
                .append(", ")
                .append(timestamp)
                .append(pp).append("]");

            return bldr.toString();

        }
    }

    private static class Interval{
        private int start=0;
        private int end=0;
        public Interval(int start, int end){
            this.start = start;
            this.end=end;
        }

        @Override
        public String toString(){
            StringBuilder bldr = new StringBuilder("[");
            bldr.append(start).append("-->").append(end).append("]");
            return bldr.toString();
        }

        //Make interval from string like 10-->20
        public static Interval makeInterval(String intervalString){
            String[] points = intervalString.split("-->");
            return new Interval(Integer.parseInt(points[0].trim()), Integer.parseInt(points[1].trim()));
        }
    }

    private Deque<Interval> mergeInterval(Interval[] a){
        Deque<Interval> ia = new LinkedList<>();
        Arrays.sort(a, (a1, a2)->a1.start-a2.start);
        for (Interval it : a){
            if (ia.isEmpty()){
                ia.add(it);
            }
            else{
                Interval last = ia.getLast();
                // full include
                if (last.start<=it.start && last.end>=it.end){
                    continue;
                }
                else if (last.end>it.start){
                    last.end=Math.max(last.end, it.end);
                }
                else{
                    ia.addLast(it);
                }
            }

        }
        return ia;

    }
    /**
     * Return the result of Interval [] a minus Interval[] b
     * @param a
     * @param b
     * @return
     */
    public List<Interval> aMinusB (Interval[] a, Interval[] b){
        if (a == null || a.length==0){
            return new ArrayList<>();
        }
        else if (b==null || b.length==0){
            return Arrays.asList(a);
        }

        PriorityQueue<Point> pq = new PriorityQueue<Point>((p1, p2)->p1.timestamp-p2.timestamp);
        for (Interval interval : a){
            pq.add(new Point(A, interval.start, PointPosition.Start));
            pq.add(new Point(A, interval.end, PointPosition.End));
        }

        for (Interval interval : b){
            pq.add(new Point(B, interval.start, PointPosition.Start));
            pq.add(new Point(B, interval.end, PointPosition.End));
        }
        int countA=0;
        int countB=0;
        int start=-1;
        // start the merge
        List<Interval> res = new LinkedList<>();
        while(!pq.isEmpty()){
            Point p = pq.poll();
            if (p.group==A){// A's points
                if (p.pp == PointPosition.Start){
                    // only when this is the first start and there is no B on the top.
                    if (countB==0 && countA==0){
                        start = p.timestamp;
                    }
                    countA++;
                }
                else{
                    countA--;
                    // ending
                    // only when there is no b and this is the rightest ending point for a.
                    if (countB==0 && countA==0){
                        addInterval(res, start, p);
                    }
//                    start =-1;
                }

            }
            else{ // B's points

                //
                if (p.pp == PointPosition.Start){
                    // only when there is A's interval and no B intervial previously
                    if (countA>0 && countB==0){
                        addInterval(res, start, p);
                       // start =-1;
                    }
                    countB++;
                }
                else{
                    // ending of B
                    countB--;
                    if (countA>0 && countB==0){
                        start = p.timestamp;
                    }
                }

            }

        }
        return res;
    }

    private void addInterval(List<Interval> list, int start, Point p){
        if (start >=p.timestamp){
            return;
        }
        Interval it = new Interval(start, p.timestamp);
        System.out.println("Seeing a/n " + p.pp + ": " + p.timestamp + ", for "+ p.group+", Adding an interval: "+it);
        list.add(it);
    }

    /**
     * Testing helper
     * @param sA: String should look like "2-->3, 4-->5,..."
     * @param sB: String should look like "2-->3, 4-->5,..."
     */
    private static void testIntervalMinus(IntervalMinus im, String sA, String sB){
        String[] pairsA = sA.split(",");
        String[] pairsB = sB.split(",");
        Interval[] a = new Interval[pairsA.length];
        Interval[] b = new Interval[pairsB.length];
        for (int i=0; i<pairsA.length; i++){
            a[i] = Interval.makeInterval(pairsA[i]);
        }
        for (int i=0; i<pairsB.length; i++){
            b[i] = Interval.makeInterval(pairsB[i]);
        }

        System.out.println("---------------------------------------------- ");
        System.out.println("Interval[] a is: "+ Arrays.toString(a));
        System.out.println("Interval[] b is: "+ Arrays.toString(b));

        List<Interval> result = im.aMinusB(a, b);
        System.out.println("The minus result is: " + result);
        System.out.println();
    }

    public static void main(String[] args){
        IntervalMinus im = new IntervalMinus();
        testIntervalMinus(im, "0-->2, 3-->5, 6-->12", "1-->3, 4-->8");
        testIntervalMinus(im, "0-->2, 3-->5, 1-->2, 6-->12, 2-->3", "1-->3, 4-->8");
        testIntervalMinus(im, "0-->2, 3-->5, 1-->2, 6-->12, 2-->3, 9-->18", "1-->3, 4-->8");

        testIntervalMinus(im, "0-->2, 3-->5, 1-->2, 6-->12, 2-->3, 13-->18", "1-->3, 4-->8");
        testIntervalMinus(im, "0-->2, 3-->5, 1-->2, 6-->12, 2-->3, 13-->18", "1-->3, 4-->8, 2-->4");
    }

}
