package com.quartz;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定时任务现场管理器
 * @author xiehongwei
 * @date 2021/10/16 10:41 上午
 */
public class PollThreadManager {
    private static final PollThreadManager instance = new PollThreadManager();
    private static final Map<String, AbstractPollThread> threads = new ConcurrentHashMap<>();

    public static PollThreadManager getInstance() {
        return instance;
    }

    public void register(String threadName, AbstractPollThread thread) {
        threads.put(threadName, thread);
    }

    public AbstractPollThread getThread(String threadName) {
        return threads.get(threadName);
    }
}
