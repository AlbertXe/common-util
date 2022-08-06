package com.util.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xiehongwei
 * @date 2022/8/6 9:14 下午
 */
public class CglibProxy {

    public static Object proxy(Class<?> clz) {
        Object proxy = Enhancer.create(clz, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                if (method.getName().equals("name")) {
                    System.out.println("before  ");
                }
                result = methodProxy.invokeSuper(o,objects);

                if (method.getName().equals("name")) {
                    System.out.println("after  ");
                }
                return result;
            }
        });
        return proxy;
    }
}
