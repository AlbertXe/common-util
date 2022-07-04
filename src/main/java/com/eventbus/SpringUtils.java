package com.eventbus;

import org.springframework.context.ApplicationContext;

/**
 * Spring Bean工具
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 21:21
 */
public class SpringUtils {
    private static ApplicationContext applicationContext;

    public static <T>T getBean(String name, Class<T> clz) {
        return applicationContext.getBean(name, clz);
    }
}
