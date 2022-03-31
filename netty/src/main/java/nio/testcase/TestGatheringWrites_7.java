package nio.testcase;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import static nio.testcase.ByteBufferUtil.debugAll;

/**
 * Created on 2022/3/3.
 *
 * @author Weikaichen
 */
public class TestGatheringWrites_7 {
    public static void main(String[] args) {
        try (FileChannel channel = new RandomAccessFile("netty/word2.txt", "rw").getChannel()) {
            ByteBuffer buffer = StandardCharsets.UTF_8.encode("hello");
            ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("world");
            ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("!!!");
            channel.write(new ByteBuffer[]{buffer, buffer1, buffer2});

        } catch (IOException e) {
        }
    }
}
