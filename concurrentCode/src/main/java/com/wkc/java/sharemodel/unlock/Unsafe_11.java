package com.wkc.java.sharemodel.unlock;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created on 2022/3/28.
 *
 * @author Weikaichen
 */
public class Unsafe_11 {
    /*
     * Unsafe 对象提供非常底层，操作内存线程的方法，Unsafe对象不能直接调用，只能通过反射
     * */
    public static void main(String[] args) throws NoSuchFieldException {
        Unsafe unsafe = UnsafeAccessor.getUnsafe();

        //1.获取域的偏移量
        long idOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("id"));
        long nameOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("name"));

        Teacher teacher = new Teacher();
        //2.执行cas操作
        unsafe.compareAndSwapInt(teacher,idOffset,0, 1);
        unsafe.compareAndSwapObject(teacher, nameOffset, null, "hello");

        System.out.println(teacher);
    }
}

@Data
class Teacher {
    volatile int id;
    volatile String name;
}


class UnsafeAccessor {

    static Unsafe unsafe;

    static {
        try {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            unsafe = (Unsafe) declaredField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static Unsafe getUnsafe() {
        return unsafe;
    }


}
