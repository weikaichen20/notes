package com.wkc.gc.quote;

import com.sun.media.sound.SoftEnvelopeGenerator;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022/1/24.
 *
 * @author Weikaichen
 */


/*
* 演示软引用
* -Xmx20m -XX:+PrintGCDetails -verbose:gc
* */
public class Demo_1 {

    public static final int _4MB=4*1024*1024;

    public static void main(String[] args) throws IOException {
        ArrayList<byte[]> bytes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bytes.add(new byte[_4MB]);
        }
        System.in.read();
    }
}
