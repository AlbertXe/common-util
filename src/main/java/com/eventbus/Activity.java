package com.eventbus;

import com.google.common.eventbus.Subscribe;

public class Activity{
    @Subscribe
    public void handle(Integer msg) {
        System.out.println("start handler Integer msg " + msg);
    }

    @Subscribe
    public void handle(String msg) {
        System.out.println("start handler String msg " + msg);
    }

}
