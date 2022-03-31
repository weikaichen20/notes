package nio.testcase;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static nio.testcase.ByteBufferUtil.debugAll;

/**
 * Created on 2022/3/3.
 *
 * @author Weikaichen
 */
public class TestByteBufferExam_8 {
    public static void main(String[] args) {
        /*
        网络上有多条数据发送给服务器，数据之间使用\n进行分割
        但是由于某种原因这些数据在接受时，被进行了重新组合，例如
        原始数据有3条为
            hello,world\n
            Im Zhangsan\n
            How are you?\n
        变成了下面两个byteBuffer（黏包，半包）
            hello,world\nIm Zhangsan\nHo
            w are you?\n
        现在要求编写程序将错乱的的数据恢复成原始按\n分割的数据
         */
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);

    }
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


    /*
    *  ByteBuffer buffer = ByteBuffer.wrap("hello,world\\nIm Zhangsan\\nHo".getBytes());
        ByteBuffer buffer1 = ByteBuffer.wrap("w are you?\\n".getBytes());

        StringBuilder sb = new StringBuilder();
        String[] split = sb.append(StandardCharsets.UTF_8.decode(buffer).toString()).append(StandardCharsets.UTF_8.decode(buffer1).toString()).toString().split("\\\\n");
        for (String s : split) {
            System.out.println(s);
        }
    * */
}
