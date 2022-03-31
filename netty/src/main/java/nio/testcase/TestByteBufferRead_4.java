package nio.testcase;

import java.nio.ByteBuffer;

/**
 * Created on 2022/3/3.
 *
 * @author Weikaichen
 */
public class TestByteBufferRead_4 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a','b','c','d'});
        buffer.flip();

        //rewind 将position置为1，从头开始读
//        System.out.println((char)buffer.get());
//        buffer.rewind();//从头开始读


        //mark & reset
        //mark 做一个标记 记录 position位置，reset是将 position 重置到mark位置
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        buffer.mark();//加标记，索引为2
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        buffer.reset();//将position 置为2
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());

        //get(i) 根据索引去拿，不会改变读索引的位置
    }
}
