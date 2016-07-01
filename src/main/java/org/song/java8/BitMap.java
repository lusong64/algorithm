package org.song.java8;

/**
 * Purpose of this class is to
 */
public class BitMap {
    private static final long MASK=0Xffffffffffffffffl;
    private static final int SHIFT=64;

    private static final int DEFAULT_CAPACITY=1024;
    private final long[] bitmap;

    public BitMap(long capacity){
        if (capacity <=32){
            capacity = DEFAULT_CAPACITY;
        }
        int theCapacity = (int)(capacity>>>SHIFT);

        bitmap = new long [theCapacity];
    }

    private int getIndex(long num){
        return (int)(num>>>SHIFT);
    }

    public boolean isSet(long num){
        return (bitmap[getIndex(num)] & (1<<(num&MASK))) !=0;
    }

    public void set(long num){
        bitmap[getIndex(num)] |= 1<<(num&MASK);
    }

    public void clear(long num){
        bitmap[getIndex(num)] &= ~(1<<num&MASK);
    }

}
