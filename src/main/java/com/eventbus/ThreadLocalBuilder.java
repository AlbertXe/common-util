package com.eventbus;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 20:23
 */
public class ThreadLocalBuilder {
    private static final int MAX = 1 << 14;
    private static final ThreadLocal<ThreadLocalBuilder> LOCAL =
            ThreadLocal.withInitial(ThreadLocalBuilder::new);
    private StringBuilder stringBuilder;

    public ThreadLocalBuilder() {
        stringBuilder = init(128);
    }

    private static StringBuilder init(int capacity) {
        StringBuilder builder = new StringBuilder(capacity).append('\u0100');
        builder.setLength(0);
        return builder;
    }

    public static StringBuilder builder() {
        ThreadLocalBuilder builder = LOCAL.get();
        StringBuilder stringBuilder = builder.stringBuilder;
        if (stringBuilder.capacity()>MAX) {
            return builder.stringBuilder = init(MAX);
        }
        stringBuilder.setLength(0);
        return stringBuilder;
    }
}
