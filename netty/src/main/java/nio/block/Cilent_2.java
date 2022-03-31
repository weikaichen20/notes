package nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created on 2022/3/7.
 *
 * @author Weikaichen
 */
public class Cilent_2 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",1234));
        System.out.println("waiting >>>>>>>>>");
        System.in.read();
    }
}
