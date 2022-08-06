package com.util.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-08-06 09:52
 */
public class LogHandler implements InvocationHandler {
    private Object proxy;

    public LogHandler(Object proxy) {
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(args);
        after();
        return null;
    }

    public void before() {
        System.out.println("方法调用开始时间："+new Date());
    }

    public void after() {
        System.out.println("方法调用结束时间："+new Date());
    }
}
