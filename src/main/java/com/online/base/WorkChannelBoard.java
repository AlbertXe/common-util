package com.online.base;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-07-04 13:19
 */
public class WorkChannelBoard {
    private final WokrChannelPool pool;

    private final OnlineEnginePoint point;

    public WorkChannelBoard(WokrChannelPool pool) {
        this.pool = pool;
        point = OnlineEngineConfigManager.get().getOnlineEnginePoint();
    }

    
}
