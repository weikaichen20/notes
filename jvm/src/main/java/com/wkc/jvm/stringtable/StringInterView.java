package com.wkc.jvm.stringtable;

/**
 * Created on 2022/1/20.
 *
 * @author Weikaichen
 */
public class StringInterView {
    public static void main(String[] args) {
        String s1="a";
        String s2="b";
        String s3="a"+"b";//编译器优化
        String s4=s1+s2;//new String("ab")
        String s5="ab";//["ab"]
        String s6=s4.intern();

        System.out.println(s3==s4);
        //1.8   false
        System.out.println(s3==s5);
        //1.8   true
        System.out.println(s3==s6);
        //1.8   true


        String x2=new String("c")+new String("d");
        String x1="cd";
        x2.intern();

        System.out.println(x1==x2);
        //1.8 false
    }
}
