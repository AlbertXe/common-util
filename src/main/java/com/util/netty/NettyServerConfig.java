package com.util.netty;

import lombok.Data;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 20:45
 */
@Data
public class NettyServerConfig {
    private String name;
    private int port;
    private int dispatcherThreadSize;
    private int workThreadSize;
    private int maxThreadCount;

}
