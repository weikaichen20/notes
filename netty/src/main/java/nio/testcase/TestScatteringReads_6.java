package nio.testcase;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static nio.testcase.ByteBufferUtil.debugAll;

/**
 * Created on 2022/3/3.
 *
 * @author Weikaichen
 */
public class TestScatteringReads_6 {
    public static void main(String[] args) {
        try (FileChannel channel = new RandomAccessFile("netty/word.txt", "r").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(3);
            ByteBuffer buffer1 = ByteBuffer.allocate(3);
            ByteBuffer buffer2 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{buffer,buffer1,buffer2});
            buffer.flip();
            buffer1.flip();
            buffer2.flip();
            debugAll(buffer);
            debugAll(buffer1);
            debugAll(buffer2);

        } catch (IOException e) {
        }
    }
}
