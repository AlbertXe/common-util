package com.quartz;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务模版
 * @author xiehongwei
 * @date 2021/10/16 10:30 上午
 */
public abstract class AbstractPollThread {
    private String threadId;
    private String threadName;
    private long delay;
    private long interval;

    private Timer timer;

    public AbstractPollThread(String threadId, String threadName, long delay, long interval) {
        this.threadId = threadId;
        this.threadName = threadName;
        this.delay = delay;
        this.interval = interval;
        // 将线程报告给线程管理器
        PollThreadManager.getInstance().register(threadName,this);
    }

    public void startup(boolean daemon) {
        timer = new Timer(threadName,daemon);

        timer.schedule(new TimerTask() {
            boolean initialized = false;
            @Override
            public void run() {
                if (!initialized) {
                    AbstractPollThread.this.init();
                    initialized = true;
                }

                AbstractPollThread.this.process();
            }
        },delay,interval);
    }

    protected abstract void process();

    protected void init(){};

    public void shutdown() {
        if (timer != null) {
            timer.cancel();
        }
    }






}
