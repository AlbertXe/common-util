package com.quartz;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-12 21:43
 */
public class BatchSchedulerPollThread extends AbstractPollThread {
    private BatchScheduleServer server;
    private boolean master = false;

    public BatchSchedulerPollThread(String threadId, String threadName, long delay, long interval) {
        super(threadId, threadName, delay, interval);
    }

    public BatchSchedulerPollThread(String threadId, String threadName, long delay, long interval,BatchScheduleServer server) {
        super(threadId, threadName, delay, interval);
        this.server = server;
    }

    @Override
    protected void process() {
        // 成为主节点

        if (true) {
            List<String> list = new ArrayList<>();
            if (!master) {
                master = true;
            }
            process(list);
        }else {
            if (master) {
                server.deleteAll();
                master = false;
            }
        }
    }

    public void init() {
        //成为主节点
        if (true) {
            master = true;
            // 查询定时任务表 所有定时任务
            List<String> list = new ArrayList<>();
            process(list);
        }

    }

    private void process(List<String> list) {
        for (String task : list) {
            switch (task) {
                case "START":
                    server.addJob(task);
                    break;
                case "SCHEDULING":
                    server.addJob(task);
                    break;
                case "STOPPING":
                    server.deleteJob(task);
                    break;
                case "DELETE":
                    server.deleteJob(task);
                    break;
            }
        }
    }
}
