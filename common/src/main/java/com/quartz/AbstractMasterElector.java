package com.quartz;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-12 22:12
 */
public abstract class AbstractMasterElector {
    volatile boolean result = false;

    public abstract DBDustributeMutex getMutex();

    public void init() {
        getMutex().init();
    }

    public void becomeMaster() {
        result = false;
        getMutex().acquire();

        // 查询库表
        // tsp_named_service_master

    }

}
