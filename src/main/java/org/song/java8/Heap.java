package org.song.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Purpose of this class is to
 */
public class  Heap <E extends Object>{
    private static final int DEFAULT_SIZE=128;

    // always start with queue[1]. WE are going to waste queue[0] but it saves a lot of computation
    private static final int HEAD=1;
    private transient E[] queue;
    private int size = 0;
    private final Comparator<? super E> comparator;

    public Heap(Comparator<? super E> comparator) {
        this(DEFAULT_SIZE, comparator);
    }

    public Heap(int initSize, Comparator<? super E> comparator){
        if (initSize<=0){
            throw new IllegalArgumentException("initSize must be greater than 0");
        }

        if (comparator == null){
            throw new IllegalArgumentException("comparator must not be null.");

        }
        queue = (E[])(new Object[initSize]);
        this.comparator = comparator;
    }

    private void resizeQueue(){
        E[] temp = (E[]) new Object[queue.length<<1];
        for (int i=HEAD; i<queue.length; i++){
            temp[i] = queue[i];
        }
        queue = temp;
    }

    public void add(E o){
        if (size >= queue.length){
            resizeQueue();
        }
        queue[++size]=o;
        shifUp(size);
    }

    public E peek(){
        return queue[0];
    }

    public boolean isEmpty(){
        return size==0;
    }

    public E poll(){
        if (size==0){
            throw new IllegalStateException("Empty queue.");
        }
        E toBeremoved = queue[HEAD];
        queue[HEAD]=queue[size];
        // for mem leak;
        queue[size--]=null;
        shifDown(HEAD);
        return toBeremoved;
    }

    private void shifUp(int k){
        while (k>HEAD){
            // find its parent;
            int i = k>>>1;
            if (comparator.compare(queue[i], queue[k])<=0){
                break;
            }
            swap(queue, i, k);
            k=i;
        }
    }

    private void swap(E[] os, int index1, int index2){
        if (index1==index2){
            return;
        }
        E temp = os[index1];
        os[index1] = os[index2];
        os[index2] = temp;

    }

    private void shifDown(int k){
        int i=0;

        while ((i=k<<1)<size && i>0){
            // getting the small one from k's to children
            if (i+1<size && comparator.compare(queue[i], queue[i+1])>0){
                ++i;
            }

            if (comparator.compare(queue[k], queue[i])<0){
                break;
            }
            swap(queue, i, k);
            k=i;
        }
    }


    public static void main(String[] args){
        int [] testArray = {43, 29, 703, 920, 1, 302, 3, 4, 4, 22, 33};
        Heap <Integer> heap = new Heap<>(Integer::compare);
        for (int i : testArray){
            heap.add(Integer.valueOf(i));
        }

        List<Integer> res = new LinkedList<>();
        while (!heap.isEmpty()){
            res.add(heap.poll());
        }


        System.out.println("Before heap sort: "+ Arrays.toString(testArray));
        System.out.println("After heap sort: "+res);

    }
}
