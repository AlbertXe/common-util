package com.healthcheck;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xiehongwei
 * @date 2021/10/16 11:24 上午
 */
public class ThreadHeadLockDetector {
    private final ThreadMXBean threadMXBean;
    private static int maxDepth = 100;
    public ThreadHeadLockDetector() {
        threadMXBean = ManagementFactory.getThreadMXBean();
    }

    public Set<String> getDeadLockedThreads() {
        long[] ids = threadMXBean.findDeadlockedThreads();
        if (ids == null) {
            return Collections.emptySet();
        }
        Set<String> result = new HashSet<>();
        for (int i = 0; i < ids.length; i++) {
            long id = ids[i];
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(id, maxDepth);
            StackTraceElement[] stackTrace = threadInfo.getStackTrace();

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < stackTrace.length; j++) {
                StackTraceElement element = stackTrace[i];
                sb.append("\t at ").append(element.toString()).append(String.format("%n"));
            }
            result.add(String.format("%s locked on %s (owner by %s):%n%s", threadInfo.getThreadName(), threadInfo.getLockName(), threadInfo.getLockOwnerName(), sb.toString()));
        }
        return result;
    }
}
