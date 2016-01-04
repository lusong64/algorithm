package org.song.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Purpose of this class is to
 */
public class LongestIncreasingSequence {

    public static void main (String ... args){
        // working one
        Integer [] integers ={10, 22, 9, 33, 40, 50, 8, 7, 21, 50, 41, 60, 80};

        //Integer [] integers ={10,22,33,23,24,25,26,27,28};
        System.out.println("Finding the Longest Increasing List of " + Arrays.toString(integers) );

        LisResult result = computeLis(integers, integers.length);

        System.out.print("The Longest Increasing List is: " );
        result.printReversed();
    }
    /**
     * Returning the Longest Increasing Sequence
     * @param originalArray
     * @return
     */
    private static class LisResult {

        public int getLength() {
            return length;
        }

        private int length = 1;

        private final List<Integer> theList = new LinkedList<Integer>();

        public List<Integer> getTheList() {
            return theList;
        }

        public void addToEnd(Integer value) {
            theList.add(0, value);
        }

        public void adoptOtherLisResult(LisResult other, Integer theLast) {
            theList.clear();
            theList.addAll(other.getTheList());
            theList.add(0, theLast);
        }

        public void printReversed() {

            System.out.print("Lis=" + theList.size() + " and the Sequence: [ ");
            for (int i = theList.size() - 1; i >= 0; i--) {
                System.out.print(theList.get(i) + " ");

            }
            System.out.println("]");
        }

        public void setLength(int length) {
            this.length = length;
        }
    }

    private static LisResult computeLis(Integer[] originalArray, int index) {
        List<LisResult> results = new ArrayList<LisResult>(index);
        // initialize all the LIS to one
        for (int i = 0; i < index; i++) {
            results.add(i, new LisResult());
        }

        // initialize the fisrt LIS
        results.get(0).addToEnd(originalArray[0]);

        for (int i = 1; i < index; i++) {
            LisResult iResult = results.get(i);
            int candidateIndex = 0;
            for (int j = 0; j < i; j++) {
                LisResult jResult = results.get(j);
                if (originalArray[j]< originalArray[i]&& jResult.getLength() + 1 > iResult.getLength()) {
                    candidateIndex = j;
                    iResult.setLength(jResult.getLength() + 1);
                }

            }

            iResult.adoptOtherLisResult(results.get(candidateIndex), originalArray[i]);
        }


        LisResult finalResult = results.get(0);
        for (LisResult result : results) {
            if (result.length > finalResult.length) {
                finalResult = result;
            }
        }

        return finalResult;

    }
}
