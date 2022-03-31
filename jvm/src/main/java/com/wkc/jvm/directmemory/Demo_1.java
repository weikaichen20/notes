package com.wkc.jvm.directmemory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created on 2022/1/20.
 *
 * @author Weikaichen
 */
/*
* 演示ByteBuffer 作用
* */
public class Demo_1 {
    static final String FROM="Frompathname";
    static final String TO="topathname";
    static final int _1MB=1024*1024;

    public static void main(String[] args) {
        io();
        directBuffer();
    }

    private static void directBuffer() {
        long strat=System.currentTimeMillis();
        try (
              FileChannel from = new FileInputStream(FROM).getChannel();
              FileChannel to = new FileOutputStream(TO).getChannel()
        ){
             ByteBuffer bb = ByteBuffer.allocateDirect(_1MB);
            while (true){
                 int read = from.read(bb);
                 if (read==-1){
                     break;
                 }
                 bb.flip();
                 to.write(bb);
                 bb.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis()-strat);
    }

    private static void io() {
        long strat=System.currentTimeMillis();
        try (
                 FileInputStream from = new FileInputStream(FROM);
                 FileOutputStream to = new FileOutputStream(TO);
        ){
             byte[] bytes = new byte[_1MB];
            while (true){
                int read = from.read(bytes);
                if (read==-1){
                    break;
                }
                to.write(bytes, 0, read);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(System.currentTimeMillis()-strat);
    }
}
