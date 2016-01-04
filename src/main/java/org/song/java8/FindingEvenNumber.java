package org.song.java8;


public class FindingEvenNumber {

    public static void main (String ... args){
        /*
        List<Integer> randomList = new ArrayList<Integer>();
        Random random = new Random();
        for (int i =0; i<500000; i++){
            randomList.add(Integer.valueOf(random.nextInt(i+1)));
        }
        */

        /*
        randomList.stream()
            .forEach((i) -> System.out.println("processing: " + i));
            */

        /*
        long start = System.nanoTime();
        List<Integer> events = coreF.apply(randomList.stream().sequential().sorted());
        long end = System.nanoTime();

        System.out.println("Sequential execution with sort in stream: " + (end-start)/1000 + " ms");

        start = System.nanoTime();
        coreF.apply(randomList.stream().parallel().sorted());
        end = System.nanoTime();

        System.out.println("Parallel execution with sort in stream: " + (end-start)/1000 + " ms");

        start = System.nanoTime();
        coreF.apply(randomList.stream().sequential());
        end = System.nanoTime();

        System.out.println("Sequential execution without sort in stream: " + (end-start)/1000 + " ms");

        start = System.nanoTime();
        coreF.apply(randomList.stream().parallel());
        end = System.nanoTime();

        //System.out.println("Parallel execution without sort in stream: " + (end-start)/1000 + " ms");
        //events.stream().forEach((i) -> System.out.println("We got " + i));

        */

    }


    /*
    private static final Function<Stream<Integer>, List<Integer>> coreF = (Stream<Integer> stream) ->
            stream.filter((i) -> i.intValue() % 2 == 0)
            .collect(toList());
            */



}