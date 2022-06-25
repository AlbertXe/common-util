package com.online;

import lombok.Data;

import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiehongwei
 * @date 2022/6/24 9:22 上午
 */
@Data
public class DaoContext {
    private static ThreadLocal<DaoContext> instance = new ThreadLocal<DaoContext>() {
        @Override
        protected DaoContext initialValue() {
            DaoContext daoContext = super.initialValue();
            if (daoContext != null) {
                contextMap.put(Thread.currentThread().getName(), daoContext);
            }
            return daoContext;
        }

        @Override
        public void set(DaoContext value) {
            DaoContext.contextMap.put(Thread.currentThread().getName(), value);
            super.set(value);
        }

        @Override
        public void remove() {
            DaoContext.contextMap.remove(Thread.currentThread().getName());
            super.remove();
        }
    };

    private int rowCount;
    private long startTime;
    private long timeout;
    private long tranTimeout;
    private boolean tranTimeoutFlag = false;
    private Statement statement;
    private String datasourceId;
    private volatile boolean afterExceptionProcess;
    private boolean forceWithoutGlobalCache;

    private static final Map<String, DaoContext> contextMap = new ConcurrentHashMap<>();


    public static DaoContext get() {
        DaoContext daoContext = instance.get();
        if (daoContext == null) {
            daoContext = new DaoContext();
            instance.set(daoContext);
        }
        return daoContext;
    }
}
