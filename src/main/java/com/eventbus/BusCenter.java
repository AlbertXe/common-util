package com.eventbus;

import com.google.common.eventbus.EventBus;

public class BusCenter {
    private static EventBus eventBus = new EventBus();

    public static void  register(Object obj) {
        eventBus.register(obj);
    }

    public static void unRegister(Object obj) {
        eventBus.unregister(obj);
    }

    public static void post(Object obj) {
        eventBus.post(obj);
    }


}
