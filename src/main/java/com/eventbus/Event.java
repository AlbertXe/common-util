package com.eventbus;


import java.util.List;

public interface Event extends Executable {
    String eventNo();

    EventTbl event();

    List<ActivityTbl> activities();

    static Event createEvent(String eventNo) {
        //按事件号查询事件
        EventTbl eventTbl = new EventTbl();
        return createEvent(eventTbl);
    }

    static Event createEvent(EventTbl eventTbl) {
        AbstractEvent
    }

}
