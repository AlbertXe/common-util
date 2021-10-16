package com.healthcheck;

import java.util.Set;

/**
 * 线程死锁健康检查
 * @author xiehongwei
 * @date 2021/10/16 11:22 上午
 */
public class ThreadDeaLockHealthCheck extends HealthCheck{
    private final ThreadHeadLockDetector detector;

    public ThreadDeaLockHealthCheck() {
        this.detector = new ThreadHeadLockDetector();
    }


    @Override
    public boolean check() {
        Set<String> locks = detector.getDeadLockedThreads();
        System.out.println(locks);
        return locks.isEmpty();
    }
}
