package com.util.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * java代理
 * @description:
 * @author: AlbertXe
 * @create: 2022-08-06 09:48
 */
public class JavaProxy {

    public static Object getLogProxy(Object service) {
        InvocationHandler enhance = new LogHandler(service);
        Object o = Proxy.newProxyInstance(service.getClass().getClassLoader()
                , service.getClass().getInterfaces(), enhance);
        return o;
    }
}
