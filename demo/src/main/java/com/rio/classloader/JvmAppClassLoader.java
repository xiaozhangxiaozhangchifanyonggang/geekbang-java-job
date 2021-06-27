package com.rio.classloader;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 添加引用类
 * 1、放到JDK的lib/ext下，或者-Djava.ext.dirs
 * 2、java -classpath(cp) 或者class文件放到当前路径
 * 3、自定义ClassLoader加载
 * 4、拿到当前执行类的ClassLoader,反射调用addUrl方法添加jar或路径(JDK9无效)
 *
 * @author Rio
 * @date 2021/6/26
 */
public class JvmAppClassLoader {
    public static void main(String[] args) {
        String addPath = "file:/d:/app/";
        URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoader.class.getClassLoader();
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(addPath);
            addURL.invoke(urlClassLoader, url);
            //效果跟Class.forName("com.rio.Hello").newInstance()一样
            Class.forName("com.rio.Hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
