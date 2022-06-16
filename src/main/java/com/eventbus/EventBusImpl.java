package com.eventbus;


import java.util.Map;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 18:15
 */
public class EventBusImpl implements EventBus {
    @Override
    public void handleEvent() {
        Map<String,Object> request = null;
        Map<String,Object> response = EventExecutors.executeOnlineEvent(request);

    }
}
