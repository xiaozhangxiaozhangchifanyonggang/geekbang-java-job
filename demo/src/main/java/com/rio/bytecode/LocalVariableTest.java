package com.rio.bytecode;

/**
 * 移动平均数测试
 * javac [-g] [-classpath G:\workSpace\geekbang-java-job\demo] LocalVariableTest.java
 * javap [-verbose] -c HelloByteCode.class > t.txt
 *
 * @author Rio
 * @date 2021/6/26
 */
public class LocalVariableTest {
    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        int num1 = 1;
        int num2 = 2;
        int num3 = 3;
        int num4 = 4;
        ma.submit(num1);
        ma.submit(num2);
        ma.submit(num3);
        ma.submit(num4);
        double avg = ma.gatavg();
    }
}
