package nio.testcase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created on 2022/3/4.
 *
 * @author Weikaichen
 */
public class TestFileChannelTransferTo_9 {
    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("netty/data.txt").getChannel();
           FileChannel to= new FileOutputStream("netty/to.txt").getChannel();
        ) {
            //效率高，底层会使用操作系统的零拷贝进行优化,最多传输2g数据
//            from.transferTo(0, from.size(), to);
//            改进
            long size = from.size();
            for (long left=size;left>0;){
                left-=from.transferTo((size-left), left, to);
            }
        } catch (IOException e) {
        }
    }
}
