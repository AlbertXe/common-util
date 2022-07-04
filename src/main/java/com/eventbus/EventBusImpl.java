package com.eventbus;


import com.online.datasource.EdspCoreBeanUtil;

import java.util.Map;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 18:15
 */
public class EventBusImpl implements EventBus {
    @Override
    public void handleEvent() {
        Map<String,Object> request = null;
        Map<String,Object> response = EventExecutors.executeOnlineEvent(request);

    }

    @Override
    public void handleLimitEvent() {
        Map<String,Object> request = null;
        Map<String,Object> response = EventExecutors.executeOnlineEvent(request);
    }

    @Override
    public void handleBatchEvent() {
        EdspCoreBeanUtil.getDBConnectionManager().beginTransaction();
        try {
            Map<String,Object> request = null;
            Map<String, Object> response = EventExecutors.executeOnlineEvent(request);
            // 分断事务提交 清空交易缓存
            try {
                EdspCoreBeanUtil.getDBConnectionManager().commit();
            } finally {
                TransactionCache.clear();
            }
        } catch (Exception e) {
            try {
                EdspCoreBeanUtil.getDBConnectionManager().rollback();
            } finally {
                TransactionCache.clear();
            }
        }
    }

    @Override
    public void handleCutOverEvent() {
        Map<String,Object> request = null;
        Map<String, Object> response = EventExecutors.executeCutOverEvent(request);

    }
}
