package com.cps;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 10:08
 */
public abstract class TcpAcceptor {
    protected ServerBootstrap serverBootstrap;
    protected ChannelFuture channelFuture;
    protected EventLoopGroup bossGroup;
    protected EventLoopGroup workGroup;
    protected TcpConfig tcpConfig;
    protected List<ChannelHandler> channelHandlers = new ArrayList<>();

    public TcpAcceptor(TcpConfig tcpConfig) {
        this.tcpConfig = tcpConfig;
    }

    public void init() {
        bossGroup = new NioEventLoopGroup(tcpConfig.getBossGroup());
        workGroup = new NioEventLoopGroup(tcpConfig.getWorkGroup());
        serverBootstrap = new ServerBootstrap().group(bossGroup, workGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
    }

    public void start() {
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                for (ChannelHandler channelHandler : channelHandlers) {
                    pipeline.addLast(channelHandler);
                }
            }
        });
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        channelFuture = serverBootstrap.bind(tcpConfig.getHost(), tcpConfig.getPort());

    }

}
