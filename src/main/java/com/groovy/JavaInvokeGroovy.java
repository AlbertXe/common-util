package com.groovy;

import groovy.lang.GroovyObject;
import lombok.SneakyThrows;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-02-19 15:08
 */
public class JavaInvokeGroovy {
    @SneakyThrows
    public static void main(String[] args) {
        GroovyObject groovyObject = (GroovyObject) JavaInvokeGroovy.class
                .getClassLoader().loadClass("HelloWord").newInstance();
        Integer[] aa = {1, 2};
        groovyObject.invokeMethod("plus",aa);
    }
}
