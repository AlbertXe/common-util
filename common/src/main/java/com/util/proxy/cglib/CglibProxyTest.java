package com.util.proxy.cglib;

import com.util.proxy.jdk.UserServiceImpl;
import org.junit.Test;

/**
 * @author xiehongwei
 * @date 2022/8/6 9:50 下午
 */
public class CglibProxyTest {

    @Test
    public void m(){
        UserServiceImpl userService = (UserServiceImpl) CglibProxy.proxy(UserServiceImpl.class);
        userService.name();
        userService.age();
    }
}
