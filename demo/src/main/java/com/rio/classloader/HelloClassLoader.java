package com.rio.classloader;

import java.util.Base64;

/**
 * 自定义classloader
 *
 * @author Rio
 * @date 2021/6/26
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        new HelloClassLoader().findClass("com.rio.classloader.Hello").newInstance();
    }

    @Override
    protected Class<?> findClass(String name) {
        String helloBase64 = "yv66vgAAADQAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUB" +
                "AA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAH" +
                "AAgHABYMABcAGAEAF0hlbGxvIENsYXNzIEluaXRpYWxpemVkBwAZDAAaABsBABljb20vcmlvL2Ns" +
                "YXNzbG9hZGVyL0hlbGxvAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANv" +
                "dXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRs" +
                "bgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAdAAEAAQAA" +
                "AAUqtwABsQAAAAEACgAAAAYAAQAAAAsACAALAAgAAQAJAAAAJQACAAAAAAAJsgACEgO2AASxAAAA" +
                "AQAKAAAACgACAAAADQAIAA4AAQAMAAAAAgAN";
        byte[] bytes = decode(helloBase64);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);

    }
}
