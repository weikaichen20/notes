package com.wkc.classload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created on 2022/3/2.
 *
 * @author Weikaichen
 */
public class ClassLoader_3 {
    /*
     * 名称                                加载类                            说明
     * Bootstrap ClassLoader               JAVA_HOME/jre/lib                 无法直接访问
     * Extension ClassLoader               JAVA_HOME/jre/lib/ext             上级是Bootstrap ，显示为null
     * Application ClassLoader             classpath                         上级是Extension
     * 自定义类加载器                       自定义                             上级是Application
     *
     * */

    /*
     * 自定义类加载器
     *
     * 什么时候使用自定义类加载器
     * 想加载非classpath随意路径的类文件
     * 都是通过接口使用实现，希望解耦时，常用在框架设计
     * 这些类希望予以隔离，不同应用的同名类都可以进行加载，不冲突，常见于tomcat容器
     *
     * 步骤
     * 1.继承ClassLoader父类
     * 2.要遵从双亲委派机制，重写findClass方法
     *   注意不是重写loadClass方法，否则不会走双亲委派机制
     * 3.读取类文件的字节码文件
     * 4.调用父类的defineClass方法来加载类
     * 5.使用者调用该类加载器的loadClass方法
     * */
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader();
        myClassLoader.loadClass("hello");
    }
}

class MyClassLoader extends ClassLoader {
    @Override //name就是 类名称
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "E:\\myclasspath\\" + name + ".class";

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Files.copy(Paths.get(path), os);

            byte[] bytes = os.toByteArray();

            //byte[]-->class
            return defineClass(name, bytes, 0, bytes.length);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException("类未找到", e);
        }


    }
}
