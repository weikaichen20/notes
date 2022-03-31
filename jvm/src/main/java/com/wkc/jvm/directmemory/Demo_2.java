package com.wkc.jvm.directmemory;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created on 2022/1/20.
 *
 * @author Weikaichen
 */
/*
* 直接内存溢出
* */
public class Demo_2 {
    static final int _100MB=1024*1024*100;

    public static void main(String[] args) {
        int i=1;
        final ArrayList<ByteBuffer> byteBuffers = new ArrayList<>();
        try {
        while (true){
            final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_100MB);
            byteBuffers.add(byteBuffer);
            i++;
        }

        }finally {
            System.out.println(i);
        }
    }
}
