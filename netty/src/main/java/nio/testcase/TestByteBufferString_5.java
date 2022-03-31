package nio.testcase;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static nio.testcase.ByteBufferUtil.debugAll;

/**
 * Created on 2022/3/3.
 *
 * @author Weikaichen
 */
public class TestByteBufferString_5 {
    public static void main(String[] args) {
        /*
        * 字符串转成ByteBuffer
        * */
        //1.字符串转为ByteBuffer   还在写模式
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("hello".getBytes());

        //2.CharSet   自动转到读模式
        ByteBuffer buffer1 = Charset.defaultCharset().encode("hello");
        debugAll(buffer1);
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer2);


        //3.wrap  自动转到读模式
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer3);


        String s = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(s);

        buffer.flip();
        String s1 = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(s1);
    }
}
