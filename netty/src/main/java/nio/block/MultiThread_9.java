package nio.block;

import io.netty.channel.ServerChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static nio.testcase.ByteBufferUtil.debugAll;

/**
 * Created on 2022/3/14.
 *
 * @author Weikaichen
 */
@Slf4j
public class MultiThread_9 {
    /*
    * boss 专门接受链接
    * work 负责读写
    * */
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        Selector boss = Selector.open();
        SelectionKey bossKey = ssc.register(boss, 0);
        bossKey.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8000));
        //1.创建固定数量的worker并初始化
        Worker[] workers=new Worker[Runtime.getRuntime().availableProcessors()];//设置CPu核心数,docker中会有问题
        for (int i = 0; i < workers.length; i++) {
            workers[i]=new Worker("worker-"+i);
        }
        AtomicInteger index=new AtomicInteger();
        while (true){
            boss.select();
            Iterator<SelectionKey> iterator = boss.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()){
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.debug("connect...{}",sc.getRemoteAddress());
                    //2.关联selecotr
                    log.debug("before register...{}",sc.getRemoteAddress());
                    //round robin 轮询效果
                    workers[(index.getAndIncrement()%workers.length)].register(sc);//被boss线程执行
                    log.debug("after register...{}",sc.getRemoteAddress());
                }
            }
        }

    }

   static class Worker implements Runnable{
        private Thread thread;
        private Selector selector;
        private String name;
        private volatile boolean start=false;
        //两个线程间传递数据就可以使用队列
        private ConcurrentLinkedQueue<Runnable> queue=new ConcurrentLinkedQueue<>();


        public Worker(String name){
            this.name=name;
        }

        //初始化线程和Selector
        public void register(SocketChannel sc) throws IOException {
            if (!start){
                thread=new Thread(this,name);
                thread.start();
                selector = Selector.open();
                start=true;
            }
            //向队列中加了任务，但是这个任务没有立即执行
            queue.add(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ, null);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });
            selector.wakeup();//唤醒selector
            //wakeup类似发一张票，，当selector阻塞时，有票就不阻塞
        }

        @Override
        public void run() {
            while (true){
                try {
                    selector.select();
                    Runnable task = queue.poll();
                    if (task!=null){
                        task.run();//执行sc.register(selector, SelectionKey.OP_READ, null);
                    }
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel) key.channel();
                            channel.read(buffer);
                            buffer.flip();
                            debugAll(buffer);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
