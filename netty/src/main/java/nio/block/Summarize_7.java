package nio.block;

/**
 * Created on 2022/3/14.
 *
 * @author Weikaichen
 */
public class Summarize_7 {
    /*
    * 网络编程
    * 非阻塞 vs 阻塞
    * 阻塞
    *   阻塞模式下，相关方法都会导致线程暂停
    *       ServerSocketChannel.accept会在没有连接建立时让线程暂停
    *       SocketChannel.read会在没有数据可读的时候让线程暂停
    *       阻塞的表现其实就是线程暂停了，暂停期间不会占用CPu，但线程相当于闲置
    *   单线程下，阻塞方法之间相互影响，几乎不能正常工作，需要多线程支持
    *
    *   但多线程下，有新的问题，
    *       32位JVM一个线程320K，64位jvm一个线程1024K，如果连接次数过多，必然导致OOM，并且线程
    *       太多，会因为频繁的上下文切换导致性能降低
    *
    *       可以采用线程池技术来减少线程和线程上下文切换，治标不治本，如果有很多连接建立，但长时间
    *       inactive，会阻塞线程池中所有的线程，因此不适合长连接，只适合短连接
    *
    * 非阻塞
    *   非阻塞模式下，相关方法都不会让线程暂停
    *       在ServerSocketChannel.accept在没有连接建立时，会返回null，继续运行
    *       SocketChannel.read在没有数据可读时，会返回0，线程不比阻塞，可以去执行其他
    *       SocketChannel的read或是去执行ServerSocketChannel.accept
    *       写数据时，线程只是等待数据写入Channel即可，无需等待Channel通过网络把数据发送出去
    *
    *   非阻塞模式下，即便没有连接建立，和可读数据，线程任然在不断运行，浪费CPu
    *   数据在复制过程中，线程实际还是阻塞的（AIO改进的地方）
    *
    *
    * 多路复用
    *   单线程可以配合Selector完成对多个Channel可读写事件的监控，这称为多路复用
    *       多路复用仅针对网络IO，普通文件IO没法利用多路复用
    *       如果不同的Selector的非阻塞模式，线程大部分时间无用功，而Selector能够保证
    *           有可连续事件时才去连接
    *           有可读事件才去读取
    *           有可写事件才去写入
    *               限于网络传输能理，Channel未必时时可用可写，一旦Channel可写，会触发Selector的可写事件
    *
    *
    * */
}
