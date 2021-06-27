package com.rio.classloader;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * jvm类加载器路径
 * %s	字符串类型	“喜欢请收藏”
 * %c	字符类型	‘m’
 * %b	布尔类型	true
 * %d	整数类型（十进制）	88
 * %x	整数类型（十六进制）	FF
 * %o	整数类型（八进制）	77
 * %f	浮点类型	8.888
 * %a	十六进制浮点类型	FF.35AE
 * %e	指数类型	9.38e+5
 * %g	通用浮点类型（f和e类型中较短的）	不举例(基本用不到)
 * %h	散列码	不举例(基本用不到)
 * %%	百分比类型	％(%特殊字符%%才能显示%)
 * %n	换行符	不举例(基本用不到)
 * %tx	日期与时间类型（x代表不同的日期与时间转换符)	不举例(基本用不到)
 * +	为正数或者负数添加符号	(“%+d”,15)	+15
 * 0	数字前面补0(加密常用)	(“%04d”, 99)	0099
 * 空格	在整数之前添加指定数量的空格	(“% 4d”, 99)	99
 * ,	以“,”对数字分组(常用显示金额)	(“%,f”, 9999.99)	9,999.990000
 * (	使用括号包含负数	(“%(f”, -99.99)	(99.990000)
 * #	如果是浮点数则包含小数点，如果是16进制或8进制则添加0x或0	(“%#x”, 99)(“%#o”, 99)	0x63 0143
 * <	格式化前一个转换符所描述的参数	(“%f和%<3.2f”, 99.45)	99.450000和99.45
 * d,%2$s”, 99,”abc”)	99,abc
 * c	包括全部日期和时间信息	星期六 十月 27 14:21:20 CST 2007
 * F	“年-月-日”格式	2007-10-27
 * D	“月/日/年”格式	10/27/07
 * r	“HH:MM:SS PM”格式（12时制）	02:25:51 下午
 * T	“HH:MM:SS”格式（24时制）	14:28:16
 * R	“HH:MM”格式（24时制）	14:28
 *
 * @author Rio
 * @date 2021/6/26
 */
public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {
        //启动类加载器
        URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for (URL urL : urLs) {
            System.out.println(String.format("==========>%s", urL.toExternalForm()));
        }
        //扩展类加载器
        printClassLoader("扩展类加载器", JvmClassLoaderPrintPath.class.getClassLoader().getParent());
        //应用类加载器
        printClassLoader("应用类加载器", JvmClassLoaderPrintPath.class.getClassLoader());
    }

    private static void printClassLoader(String name, ClassLoader classLoader) {
        if (null != classLoader) {
            System.out.println(String.format("%s ClassLoader -> %s", name, classLoader.toString()));
            printURLForClassLoader(classLoader);
        } else {
            System.out.println(String.format("%s Classloader -> null", name));
        }
    }

    private static void printURLForClassLoader(ClassLoader classLoader) {
        Object ucp = insightField(classLoader, "ucp");
        Object path = insightField(ucp, "path");
        List paths = (List) path;
        assert paths != null;
        paths.forEach(System.out::println);

    }

    private static Object insightField(Object obj, String fName) {
        Field f = null;
        try {
            if (obj instanceof URLClassLoader) {
                f = URLClassLoader.class.getDeclaredField(fName);
            } else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
