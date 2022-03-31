package nio;

/**
 * Created on 2022/2/28.
 *
 * @author Weikaichen
 */
public class NioBase_1 {
    /*
    * NIO基础
    * non-blocking io 非阻塞IO
    * 1.三大组件
    * 1.1 Channel&Buffer
    * channal有一点类似于stream，它就是读写数据的双向通道，可以从channel将数据读入buffer
    * 也可以将buffer的数据写入channel，而之前的stream要么是输入，要么是输出，channel比stream
    * 更底层。
    *
    * 常见的Channel
    *   FileChannel
    *   DatagramChannnel
    *   SocketChannel
    *   ServerSocketChannel
    * Buffer则用来缓冲读写数据，常见的buffer
    *   ByteBuffer
    *       MappedByteBuffer
    *       DirectByteBuffer
    *       HeapByteBuffer
    *   ShortBuffer
    *   IntBuffer
    *   LongBuffer
    *   FloatBuffer
    *   DoubleBuffer
    *
    * 1.2 Selector
    *
    * 多线程版设计
    *   内存占用高
    *   线程上下文切换成本高
    *   只适合连接数少的场景
    *
    * 线程池版设计
    *   阻塞模式下，线程仅能处理一个socket连接
    *   仅适合短连接场景
    *
    * selector版设计
    *  selector的作用就是配合一个线程来管理多个channel，获取这些channel上发生的事件，这些channel
    * 工作在非阻塞模式下，不会让线程吊死在channel上，适合连接数特别多，但是流量低的场景
    * 调用selector的select()会阻塞直到channel发生了读写就绪事件，这些事件发生，select方法就会返回这些
    * 事件交给thread来处理
    *
    * */
}
