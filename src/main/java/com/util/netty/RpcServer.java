package com.util.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 20:51
 */
@Getter
@Setter
public class RpcServer extends AbstractServer {
    private RpcHandler handler;
    private RpcNettyChannelInitializer channelInitializer;
    private String threadName;
    private ThreadPoolExecutor threadPool;

    public RpcServer(int port) {
        super(port);
    }

    @Override
    protected ThreadPoolExecutor getHandlePool() {
        return threadPool;
    }

    @Override
    protected ChannelInitializer<SocketChannel> getNettyChannelInitializer() {
        if (channelInitializer == null) {
            channelInitializer = new RpcNettyChannelInitializer();
        }
        return channelInitializer;
    }

    public void start() {
        super.start();
    }
}
