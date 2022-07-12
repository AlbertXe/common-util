package com.online.base;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-07-04 15:47
 */
public class OnlineEngineConfigManager {
    private static OnlineEngineConfigManager instance = new OnlineEngineConfigManager();

    public static OnlineEngineConfigManager get() {
        return instance;
    }

    public OnlineEnginePoint getOnlineEnginePoint() {
        return null;
    }
}
