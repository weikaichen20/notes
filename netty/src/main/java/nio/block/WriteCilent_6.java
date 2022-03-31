package nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created on 2022/3/14.
 *
 * @author Weikaichen
 */
public class WriteCilent_6 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8000));

        int count = 0;
        while (true) {
            ByteBuffer allocate = ByteBuffer.allocate(1024 * 1024);
            count += socketChannel.read(allocate);
            System.out.println(count);
            allocate.clear();
        }

    }
}
