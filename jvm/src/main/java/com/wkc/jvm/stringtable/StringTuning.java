package com.wkc.jvm.stringtable;

import java.io.*;

/**
 * Created on 2022/1/20.
 *
 * @author Weikaichen
 */
/*
* 性能调优
*
* -XX:StringTableSize=200000 -XX:+PrintStringTableStatistics
* StringTableSize对应着Number of buckets大小
* StringTable对应着hash表，是由数组加链表组成的，将数组调大点减少hash碰撞
 * */
public class StringTuning {
    public static void main(String[] args) {
        try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\ideaproject\\java-promotion\\jvm\\src\\main\\java\\com\\wkc\\jvm\\stringtable\\linux.words"),"utf-8"))){
            String line=null;
            long start = System.currentTimeMillis();
            while (true){
                line=bufferedReader.readLine();
                if (line==null){
                    break;
                }
                line.intern();
            }
            System.out.println(System.currentTimeMillis()-start);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.gc();
    }
}
