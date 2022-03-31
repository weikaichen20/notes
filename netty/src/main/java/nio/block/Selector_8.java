package nio.block;

import java.nio.channels.Selector;

/**
 * Created on 2022/3/14.
 *
 * @author Weikaichen
 */
public class Selector_8 {
    public static void main(String[] args) {
        /*
         * Selector
         * 一个线程配合Selector就可以监控多个channel事件，事件发生线程才去处理，避免非阻塞模式下所作无用功
         * 线程被充分利用
         * 节约线程数量
         * 减少了线程上下文切换
         *
         * */

        /*
         * 绑定channel事件
         * 注册事件，绑定的事件Selector才会关心
         *
         * channel.configureBlocking(false);
         * SelectionKey key = channel.register(selector, 绑定事件);
         *
         * channel必须工作在非阻塞模式
         * FileChannel 没有非阻塞模式，因此不能配合 selector 一起使用
         *  connect - 客户端连接成功时触发
         *  accept - 服务器端成功接受连接时触发
         *  read - 数据可读入时触发，有因为接收能力弱，数据暂不能读入的情况
         *  write - 数据可写出时触发，有因为发送能力弱，数据暂不能写出的情况
         *
         * 监听Channel事件
         * 通过三种方法来监听是否有事件发生，方法的返回值代表有多少 channel 发生了事件
         *
         * 方法1 阻塞直到绑定事件发生
         * int count=selector.select();
         *
         * 方法2 阻塞直到绑定事件事件发生，或是超时（时间单位为ms）
         * int count=selector.select(long timeout)
         *
         * 方法3 不会阻塞，也就是不管有没有事件，立刻返回，自己根据返回值检查是否有事件
         * int count=selector.selectNow()
         * */

        /*
         * select 何时不阻塞
         * 事件发生时
         *   客户端发起连接请求，会触发accept事件
         *   客户端发送数据过来，客户端正常，异常关闭时，都会触发read事件，另外如果发送的数据大于
         *   buffer缓冲区，会触发多次读取事件
         *   channel可写，会触发write事件
         *   在linux下 nio bug发生
         * 调用selector.warkup()
         * selector.close()
         * selector所在线程interrupt
         *
         *
         * 事件发生后能否不处理
         * 事件发生后要么处理，要么取消，不能什么都不做，否则下次该事件还会触发，因为nio底层使用的是水平触发
         *   水平触发是状态达到后，可以多次取数据。这种模式下要注意多次读写的情况下，效率和资源利用率情况。
         *   边缘触发是状态改变一次，取一次数据。这种模式下读写数据要注意一次是否能读写完成。
         * */


        /*
         * 为何要 iter.remove()
         * 因为select在事件发生后，就会将相关key放入selectedKeys集合，但不会在处理完后selectedKeys集合中移除，
         * 需要我们自己编码删除，例如
         *   第一次触发了 ssckey 上的 accept 事件，没有移除 ssckey
         *   第二次触发了 sckey 上的 read 事件，但这时 selectedKeys 中还有上次的 ssckey ，在处理时因为没有真正的 serverSocket 连上了，就会导致空指针异常
         *
         * cancel 的作用
         * cancel 会取消注册在 selector 上的 channel，并从 keys 集合中删除 key 后续不会再监听事件
         * */
    }
}
