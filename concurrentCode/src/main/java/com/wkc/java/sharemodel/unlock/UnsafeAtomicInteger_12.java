package com.wkc.java.sharemodel.unlock;

import sun.misc.Unsafe;

/**
 * Created on 2022/3/30.
 *
 * @author Weikaichen
 */
public class UnsafeAtomicInteger_12 {

}

class MyAtomicInteger{
    private volatile int value;
    private static final long offset;
    private static final Unsafe UNSAFE;
    static {
        UNSAFE=UnsafeAccessor.getUnsafe();
        try {
            offset=UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int getValue(){
        return value;
    }


    public void decrement(int amount){
        while (true){
            int pre = this.value;
            int next=pre-amount;
            if (UNSAFE.compareAndSwapInt(this, offset, pre, next))
                break;
        }
    }

}
