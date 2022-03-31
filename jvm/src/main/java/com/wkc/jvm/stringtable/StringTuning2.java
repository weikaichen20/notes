package com.wkc.jvm.stringtable;

import java.io.*;
import java.util.ArrayList;

/**
 * Created on 2022/1/20.
 *
 * @author Weikaichen
 */
/*
* 性能调优
*
 * */
public class StringTuning2 {
    public static void main(String[] args) throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        System.in.read();
        try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\ideaproject\\java-promotion\\jvm\\src\\main\\java\\com\\jvm\\wkc\\demo\\linux.words"),"utf-8"))){
            String line=null;
            long start = System.currentTimeMillis();
            while (true){
                line=bufferedReader.readLine();
                if (line==null){
                    break;
                }
                strings.add(line);
            }
            System.out.println(System.currentTimeMillis()-start);
        }
        System.in.read();
//        System.gc();
    }
}
