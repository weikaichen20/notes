package nio.block;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static nio.testcase.ByteBufferUtil.debugRead;

/**
 * Created on 2022/3/7.
 *
 * @author Weikaichen
 */
@Slf4j
public class ServerBySelector_3 {
    public static void main(String[] args) throws IOException {
        //1.创建selector，管理多个channel
        Selector selector = Selector.open();


        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(1234));

        //2.建立selector 和 channel 的联系（注册）
        //SelectionKey 就是将来事件发生后，通过它可以知道事件和那个channel的事件
//        SelectionKey sscKey = ssc.register(selector, 0);
        SelectionKey sscKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
        //key只关注accept事件
//        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        /*
        * 四种事件
        * accept-会在有连接请求时触发
        * connect-是客户端，连接建立后触发
        * read-可读事件
        * write-可写事件
        * */

        while (true){
            //3.select方法，没有事件发生，线程阻塞，有事件，线程才会回复运行
            //todo selector在事件未处理时，他不会阻塞，事件发生后，要么处理，要么取消
            selector.select();
            //4.处理事件，selectedKeys()内部包含所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //todo 处理key时，要从selectedKeys集合中删除，否则下次处理就会有问题
                iterator.remove();
                //5.区分事件类型
                if (key.isAcceptable()){//accept
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    /*
                     * key.cancel() 不进行处理事件，相当于丢弃消息
                     * */
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }else if (key.isReadable()){//read
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        int read = channel.read(buffer);//如果是正常断开，read的方法返回值是-1
                        if (read==-1){
                            key.cancel();
                        }else {
                            buffer.flip();
                            debugRead(buffer);
                            buffer.clear();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();//以为客户端断开了，因此需要将key取消（从selectedKeys中删除）
                    }
                }
            }
        }
    }
}
