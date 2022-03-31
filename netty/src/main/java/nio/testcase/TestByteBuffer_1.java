package nio.testcase;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created on 2022/2/28.
 *
 * @author Weikaichen
 */
@Slf4j
public class TestByteBuffer_1 {
    //FileChannel
    //1.输入输出流 2.RandomAccessFile
    //.twr
    public static void main(String[] args) {
        try (FileChannel channel = new FileInputStream("netty/data.txt").getChannel()) {
            //准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocateDirect(10);
            while (true) {
                //从channel读取数据，向buffer写入
                int len = channel.read(buffer);
                log.debug("读到的字节数 {}",len);
                if (len == -1) {
                    break;
                }

                //打印buufer内容
                buffer.flip();//切换至读模式
                while (buffer.hasRemaining()) {//是否还有剩余数据未读
                    byte b = buffer.get();
                    log.debug("实际字节 {}",(char) b);
                }
                buffer.compact();//切换为写模式
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * ByteBuffer正确使用
    * 1.向buffer写入数据，例如调用channel.read(buffer)
    * 2.调用filp()切换至读模式
    * 3.从buffer读取数据，例如调用buffer.get()
    * 4.调用clear()或者compact()切换至写模式
    * 重复1-4
    *
    * */

}
