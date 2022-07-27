package com.eventbus;

/**
 * 事件总线
 */
public interface EventBus {
    void handleEvent();

    void handleLimitEvent();

    void handleBatchEvent();

    void handleCutOverEvent();


}
