package com.eventbus;

public class EventBusTest {
    public static void main(String[] args) {
        Activity activity = new Activity();
        BusCenter.register(activity);

        BusCenter.post("hello");
        BusCenter.post(12345);
    }
}
