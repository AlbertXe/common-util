package com.util.proxy.jdk;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-08-06 09:58
 */
public class JavaProxyTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserService service = (UserService) JavaProxy.getLogProxy(userService);

        service.name();

    }
}
