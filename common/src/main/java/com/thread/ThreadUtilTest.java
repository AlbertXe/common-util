package com.thread;

import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2023-01-01 17:35
 */
public class ThreadUtilTest {

    @Test
    public void m1() {
        ThreadUtil.getThreads();
    }

    @SneakyThrows
    @Test
    public void m2() {
        ThreadA threadA = new ThreadA("AAA");
        threadA.start();

        Thread.sleep(200L);
        threadA.interrupt();
    }
}
