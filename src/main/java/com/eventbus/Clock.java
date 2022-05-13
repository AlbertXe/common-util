package com.eventbus;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 20:41
 */
public interface Clock {
    long milliseconds();

    long nanoseconds();

    public Clock SYSTEM = new Clock() {
        @Override
        public long milliseconds() {
            return System.currentTimeMillis();
        }

        @Override
        public long nanoseconds() {
            return System.nanoTime();
        }
    };
}
