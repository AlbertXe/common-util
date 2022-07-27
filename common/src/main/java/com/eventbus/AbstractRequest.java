package com.eventbus;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-13 22:19
 */
public abstract class AbstractRequest implements Request {
    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public String desc() {
        return name();
    }
}
