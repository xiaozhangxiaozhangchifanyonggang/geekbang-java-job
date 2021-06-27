package com.rio.bytecode;

/**
 * 字节码demo
 * javac [-g] HelloByteCode.java
 * javap [-verbose] -c HelloByteCode.class
 *
 * @author Rio
 * @date 2021/6/26
 */
public class HelloByteCode {
    public static void main(String[] args) {
        //new一个HelloByteCode对象赋值给helloByteCode对象
        HelloByteCode helloByteCode = new HelloByteCode();
    }
}
