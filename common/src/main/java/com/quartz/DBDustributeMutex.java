package com.quartz;

import com.ruoyi.system.domain.TspMutex;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-12 22:13
 */
public class DBDustributeMutex {
    private String name;
    private String group;

    public DBDustributeMutex(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public void init() {
        // 初始化 tsp_mutex
    }

    public void acquire() {
        // select * from tsp_mutex for update
        TspMutex tspMutex = new TspMutex();


    }
}
