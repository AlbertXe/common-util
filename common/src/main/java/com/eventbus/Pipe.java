package com.eventbus;

/**
 * 管道接口
 */
public interface Pipe {
    DataMap input(BusinessContext ctx);

    DataMap output(BusinessContext ctx);
}
