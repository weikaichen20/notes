package nio.testcase;

import java.nio.ByteBuffer;


import static nio.testcase.ByteBufferUtil.debugAll;

public class TestByteBufferReadWrite_2 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61); // 'a'
        debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64}); // b  c  d
        debugAll(buffer);
//        System.out.println(buffer.get());
        buffer.flip();
        System.out.println(buffer.get());
        debugAll(buffer);
        buffer.compact();
        debugAll(buffer);
        buffer.put(new byte[]{0x65, 0x6f});
        debugAll(buffer);
    }
    /*
    * flip 切换到读模式
    * clear 切换到写模式，并且数据全部清空
    * compact 切换到写模式，将未读取的内容压缩，例如 abcd000000（ab已读==>cd00000000）
    * */
}
