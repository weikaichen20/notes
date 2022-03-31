package com.wkc.jvm.directmemory;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created on 2022/1/20.
 *
 * @author Weikaichen
 */
public class Demo_4 {
    static final long _3GB= 1024L *1024*1024*3;
    public static void main(String[] args) throws IOException {
       Unsafe unsafe = getUnsafe();
       long l = unsafe.allocateMemory(_3GB);
       unsafe.setMemory(l, _3GB, (byte) 0);
       System.in.read();
       unsafe.freeMemory(_3GB);
       System.in.read();
    }

    public static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            return unsafe;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
