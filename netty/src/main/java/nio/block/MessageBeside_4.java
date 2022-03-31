package nio.block;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static nio.testcase.ByteBufferUtil.debugAll;
import static nio.testcase.ByteBufferUtil.debugRead;

/**
 * Created on 2022/3/10.
 *
 * @author Weikaichen
 */
/*
 * 处理消息的边界
 *
 * 一种思路是固定消息长度，数据包大小一样，服务器按预定长度读取，
 * 缺点就是浪费带宽
 *
 * 另一种就是按分隔符拆分，缺点效率低
 *
 * TLV，即Type类型，Length长度，Value数据，类型和长度已知的情况下，
 * 就可以方便获取消息大小，分配合适的buffer，缺点是buffer需要提前分配
 * ，如果内容过大，则影响server吞吐量
 * Http1.1是 TLV格式
 * Http2.0是 LTV格式
 *
 * */
@Slf4j
public class MessageBeside_4 {
    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            // 找到一条完整消息
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                // 把这条完整消息存入新的 ByteBuffer
                ByteBuffer target = ByteBuffer.allocate(length);
                // 从 source 读，向 target 写
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                debugAll(target);
            }
        }
        source.compact();
    }
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();


        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(1234));

        SelectionKey sscKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {//accept
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();//attachment
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(16);//
                    sc.register(selector, SelectionKey.OP_READ, buffer);
                } else if (key.isReadable()) {//read
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int read = channel.read(buffer);//如果是正常断开，read的方法返回值是-1
                        if (read == -1) {
                            key.cancel();
                        } else {
                           split(buffer);
                           if (buffer.position()==buffer.limit()){
                               ByteBuffer byteBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                               buffer.flip();
                               byteBuffer.put(buffer);
                               key.attach(byteBuffer);
                           }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();//以为客户端断开了，因此需要将key取消（从selectedKeys中删除）
                    }
                }
            }
        }
    }
    /*
    * ByteBuffer大小分配
    * 每个channel都需要记录可能被切分的消息，因为ByteBuffer不能被多个channel共同使用
    * 因此需要为每个channel维护一个ByteBuffer
    *
    * ByteBuffer不能太大，比如一个ByteBuffer 1MB，要支持百万连接就要1TB内存，因此需要设计
    * 大小可变的ByteBuffer
    *
    *
    *
    *
    * */
}
