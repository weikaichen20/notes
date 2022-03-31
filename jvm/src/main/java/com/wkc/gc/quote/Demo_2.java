package com.wkc.gc.quote;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022/1/27.
 *
 * @author Weikaichen
 */
/*
* 演示软引用，配合引用队列
* -Xmx20m -XX:+PrintGCDetails -verbose:gc
* */
public class Demo_2 {
    public static final int _4MB=4*1024*1024;

    public static void main(String[] args) throws IOException {
        soft();
    }

    private static void soft() throws IOException {
        //List->SoftReference->byte[]

        //引用队列
        ReferenceQueue<byte[]> queue=new ReferenceQueue<>();

        ArrayList<SoftReference<byte[]>> softReferences = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            //关联了引用队列，当软引用所关联的byte[]被回收时，软引用自己加入到queue中去
            SoftReference<byte[]> softReference = new SoftReference<>(new byte[_4MB],queue);
            System.out.println(softReference.get());
            softReferences.add(softReference);
            System.out.println(softReferences.size());
        }

        //删除队列中的软引用对象
        Reference<? extends byte[]> poll = queue.poll();
        while (poll!=null){
            softReferences.remove(poll);
            poll= queue.poll();
        }

        softReferences.forEach(s->{
            System.out.println(s.get());
        });
        System.in.read();
    }
}
