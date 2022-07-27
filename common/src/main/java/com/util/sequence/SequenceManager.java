package com.util.sequence;

import lombok.SneakyThrows;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-10 18:07
 */
public class SequenceManager {
    private static SequenceManager instance = new SequenceManager();
    private SequenceCallback callback;

    public static SequenceManager get() {
        return instance;
    }

    @SneakyThrows
    public void init(SequenceConfig config) {
        callback = (SequenceCallback) Class.forName(config.getCallBackClass(), true, Thread.currentThread().getContextClassLoader()).newInstance();
        callback.init();
    }

    public static Long nextVal(String type,String name) {
        return instance.callback.nextVal(type,name);
    }

}
