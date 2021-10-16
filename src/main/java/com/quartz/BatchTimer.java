package com.quartz;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author xiehongwei
 * @date 2021/10/16 10:11 上午
 */
public class BatchTimer {

    public void run(long delay) {
        new Timer("batchTimer").scheduleAtFixedRate(new TimerTask() {
                                            @Override
                                            public void run() {
                                                System.out.println(Thread.currentThread().getId()+" running");
                                            }
                                        }
                , delay, delay);
    }

    public static void main(String[] args) {
        BatchTimer batchTimer = new BatchTimer();
        batchTimer.run(500);
    }
}
