package com.service;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * 统一个线程拿到的bean是一样的
  @description:
 * @author: AlbertXe
 * @create: 2022-07-20 21:45
 */
public class MyScope implements Scope {
    ThreadLocal local = new ThreadLocal<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (local.get() != null) {
            return local.get();
        }
        local.set(objectFactory.getObject());
        return local.get();
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
