package com.wkc.jvm.directmemory;

/**
 * Created on 2022/1/20.
 *
 * @author Weikaichen
 */

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 禁用显式回收对直接内存的影响
 */
public class Demo_3 {

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        System.out.println("分配完毕");
        System.in.read();
        System.out.println("准备释放");
        byteBuffer=null;
        System.gc();
        System.in.read();
    }
}
