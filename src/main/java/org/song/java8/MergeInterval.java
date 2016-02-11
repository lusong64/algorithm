package org.song.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Purpose of this class is to
 */
public class MergeInterval {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()){
            return new ArrayList<Interval>();
        }
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        List<Interval> result = new LinkedList<>();
        Interval lastIn = null;
        while (!intervals.isEmpty()){
            Interval interval = intervals.remove(0);
            if (result.isEmpty()){
                result.add(interval);
                lastIn = interval;
            }
            else{
                if (interval.start <= lastIn.end){
                    lastIn.end = Math.max(interval.end, lastIn.end);
                }
                else{
                    result.add(interval);
                    lastIn = interval;
                }
            }

        }

        return result;
    }

    private static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
        @Override
        public String toString(){
            return String.format("%d->%d", start, end);
        }

    }

    public static void main(String [] args){
        List<Interval> its = new ArrayList<Interval>();
        its.add(new Interval(1, 4));
        its.add(new Interval(5, 6));
        MergeInterval mergeInterval = new MergeInterval();
        System.out.println("After merge: " + mergeInterval.merge(its));
    }
}
