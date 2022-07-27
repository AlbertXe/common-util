package com.healthcheck;

import org.junit.Test;

/**
 * @author xiehongwei
 * @date 2021/10/16 1:19 下午
 */
public class ThreadDeaLockHealthCheckTest {
    Object conditionA = new Object();
    Object conditionB = new Object();
    @Test
    public void m() throws InterruptedException {
        new Thread(()->{
            synchronized (conditionA) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName()+ i);
                }
                synchronized (conditionB) {
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
        new Thread(()->{
            synchronized (conditionB) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName()+ i);
                }
                synchronized (conditionA) {
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();

        Thread.sleep(5000);
        ThreadDeaLockHealthCheck check = new ThreadDeaLockHealthCheck();
        System.out.println(check.check());

        System.out.println("end");

    }
}
