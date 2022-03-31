package nio.testcase;

import java.nio.ByteBuffer;

/**
 * Created on 2022/3/3.
 *
 * @author Weikaichen
 */
public class TestByteBufferAllocate_3 {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(10).getClass());
        System.out.println(ByteBuffer.allocateDirect(10).getClass());
    }
    /*
    * class java.nio.HeapByteBuffer   -java堆内存，读写效率较低，受GC的影响
    * class java.nio.DirectByteBuffer  -直接内存，读写效率高（少一次拷贝），不会受GC影响，分配的效率低
    *
    * */
}
