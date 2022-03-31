package nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * Created on 2022/3/14.
 *
 * @author Weikaichen
 */
public class WriteServer_5 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(8000));

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    SelectionKey sckeys = sc.register(selector, 0, null);
                    sckeys.interestOps(SelectionKey.OP_READ);

                    //1.向客户端发送大量数据
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < 30000000; i++) {
                        stringBuilder.append("a");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(stringBuilder.toString());

                    //返回值代表实际写入的字节数
                    int write = sc.write(buffer);
                    System.out.println(write);

                    //3.判断是否剩余内容
                    if (buffer.hasRemaining()) {
                        //4.关注可写事件 原来有读事件，不想破坏读事件，相加既有读事件，也有写事件
                        sckeys.interestOps(sckeys.interestOps() + SelectionKey.OP_WRITE);
                        sckeys.attach(buffer);
                    }

                } else if (key.isWritable()) {
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    SocketChannel sc = (SocketChannel) key.channel();
                    int write = sc.write(buffer);
                    System.out.println(write);
                    //6.清除操作
                    if (!buffer.hasRemaining()){
                        key.attach(null);//内容写完了清除buffer，清除可写事件
                        key.interestOps(key.interestOps()-SelectionKey.OP_WRITE);
                    }
                }
            }
        }

    }
}
