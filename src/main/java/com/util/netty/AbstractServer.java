package com.util.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.SneakyThrows;

import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 20:51
 */
public abstract class AbstractServer {
    private int port;
    private Thread thread;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;

    public AbstractServer(int port) {
        this.port = port;
    }

    protected abstract ThreadPoolExecutor getHandlePool();

    protected abstract ChannelInitializer<SocketChannel> getNettyChannelInitializer();

    @SneakyThrows
    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(getNettyChannelInitializer())
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.AUTO_READ, true);
        bootstrap.bind(port).sync();
        Runtime.getRuntime()
                .addShutdownHook(new NetShutdownHook());

        thread = new Thread(() -> {

        }, "netty-hold-thread");
        if (!thread.isAlive()) {
            thread.setDaemon(false);
            thread.start();
        }
    }

    public void initNettyPool(int parentSize, int childSize) {
        bossGroup = new NioEventLoopGroup(parentSize);
        workGroup = new NioEventLoopGroup(childSize);
    }

    public void shutdown() {
        getHandlePool().shutdown();
        bossGroup = null;
        workGroup = null;
        thread.interrupt();
        thread = null;
    }

    protected class NetShutdownHook extends Thread{
        @Override
        public void run() {
            AbstractServer.this.shutdown();
        }
    }

}
