package com.rio.must;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 自定义一个Classloader，加载一个Hello.xlass 文件，执行hello 方法
 * 文件内容是一个Hello.class 文件所有字节（x=255-x）处理后的文件
 *
 * @author Rio
 * @date 2021/6/26
 */
public class JobHelloClassLoader extends ClassLoader {
    final static String CLASS_NAME = "Hello";
    final static String METHOD_NAME = "hello";

    public static void main(String[] args) throws Exception {
        // 加载类文件
        // Class<?> aClass = new JobHelloClassLoader().findClass(CLASS_NAME);
        Class<?> aClass = new JobHelloClassLoader().loadClass(CLASS_NAME);
        //获取Hello对象
        Object o = aClass.newInstance();
        //获取无参hello方法
        Method method = aClass.getMethod(METHOD_NAME);
        method.setAccessible(true);
        //执行Hello的hello方法
        method.invoke(o);
    }

    /**
     * 查看类
     *
     * @param name 类的完全限定名
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 文件后缀
        final String suffix = ".xlass";
        // 获取输入流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name + suffix);
        try {
            // 读取数据
            int length = 0;
            while (length == 0) {
                length = inputStream.available();
            }
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            // 转换
            byte[] classBytes = decode(byteArray);
            // 通知底层定义这个类
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            close(inputStream);
        }
    }

    /**
     * 解码
     *
     * @param byteArray 处理前的文件数组
     * @return
     */
    private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

    /**
     * 关闭输入流
     *
     * @param is 输入流
     */
    protected static void close(InputStream is) {
        if (null != is) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
