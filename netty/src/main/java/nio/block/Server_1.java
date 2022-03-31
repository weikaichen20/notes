package nio.block;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static nio.testcase.ByteBufferUtil.debugRead;

/**
 * Created on 2022/3/7.
 *
 * @author Weikaichen
 */
@Slf4j
public class Server_1 {
    public static void main(String[] args) throws IOException {
        //使用nio来理解阻塞模式，单线程
        //0.ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //1.创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //todo 非阻塞模式  影响的是accept的方法
        ssc.configureBlocking(false);

        //2.监听服务器端口
        ssc.bind(new InetSocketAddress(1234));

        //3.连接集合
        List<SocketChannel> channels=new ArrayList<>();
        while (true){
            //4.accpet建立与客户端连接，SocketChannel用来与客户端之间通信
//            log.debug("Connecting>>>>>>>>>>>>>>>>");
            SocketChannel sc = ssc.accept();//阻塞方法，线程停止运行
            if(sc!=null){
                log.debug("Connected>>>>>>>>>>>>>>>>{}",sc);
                //todo 非阻塞模式  影响的是read的方法
                sc.configureBlocking(false);
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {
                //5.接收客户端发送的数据
//                log.debug("before read >>> {}",channel);
                int read = channel.read(buffer);//阻塞方法，线程停止运行
                if (read>0) {
                    buffer.flip();
                    debugRead(buffer);
                    buffer.clear();
                    log.debug("after read >>> {}",channel);
                }
            }


        }


    }
}
