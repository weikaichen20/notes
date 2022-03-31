package nio;

/**
 * Created on 2022/3/4.
 *
 * @author Weikaichen
 */
public class FileChannel_2 {
    /*
    * Filechannel工作模式
    *
    * FileChannel只能工作在阻塞模式下
    *
    * */

    /*
    * 获取
    * 不能直接打开FileChannel，必须通过FileInputStream，FileOutputStream，或者RandomAccessFile
    * 来获取FileChannel，它们都有getChannel方法
    *
    * 通过FileInputStream获取Channel只能读
    * 通过FileOutputStream获取Channel只能写
    * 通过RandomAccessFile是否读写根据构造RandomAccessFile的读写模式确定（rw r w）
    * */

    /*
    * 读取
    * 会从channel读取数据填充ByteBuffer，返回值表示读到了多少字节，-1表示到达了文件的末尾
    * int readBytes=channel.read(buffer);
    * */

    /*
    * 写入
    * 写入正确姿势，SocketChannel和FileChannel不一样
    * ByteBuffer buffer=ByteBuffer.allcoect(10);
    * buffer.put();
    * buffer.filp();切换到读模式
    * while(buffer.hasRemaining()){查看是否还有剩于内容
    *      channel.wirte(buffer)
    * }
    * */

    /*
    * 关闭
    * channel必须关闭，不过调用了FileInputStream，FileOutputStream和RandomAccessFile的close方法
    * 会间接调用channel的colse方法
    *
    * */

    /*
    * 位置
    * 获取当前位置
    * long pos=channel.position();
    * */
}
