package com.wkc.jvm.stringtable;

/**
 * Created on 2022/1/20.
 *
 * @author Weikaichen
 */
/*
* 演示StringTable垃圾回收
* -Xmx10m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
* */
public class StringTableGC {
    public static void main(String[] args) {
        int i=0;
        try {
            for (int j = 0; j <100000; j++) {
                String.valueOf(j).intern();
                i++;
            }
        }catch (Throwable t){
            t.printStackTrace();
        }finally {
            System.out.println(i);
        }
    }
}
