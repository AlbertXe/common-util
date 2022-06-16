package com.eventbus;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-13 22:20
 */
public abstract class AbstractResponse implements Response {
    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public String desc() {
        return name();
    }
}
