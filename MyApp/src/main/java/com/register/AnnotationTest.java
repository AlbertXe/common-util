package com.register;

import com.pojo.AnnotationBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-07-20 22:35
 */
public class AnnotationTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(AnnotationBean.class);
    }
}
