package com.cps;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 10:09
 */
public class CpsAcceptor extends TcpAcceptor {

    private String ipPattern;

    public CpsAcceptor(TcpConfig tcpConfig, String ipPattern) {
        super(tcpConfig);
        this.ipPattern = ipPattern;
    }

    @Override
    public void start() {
        channelHandlers.add(new CommMessagerEncoder());
        channelHandlers.add(new CommMessagerDecoder());
        channelHandlers.add(new TcpAcceptorHandler<>(tcpConfig.getHandler(), tcpConfig));
        channelHandlers.add(new ExceptionCaughtHandler(ipPattern));

        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new CpsLengthFieldDecoder(Integer.MAX_VALUE,0,8));
                for (ChannelHandler channelHandler : channelHandlers) {
                    pipeline.addLast(channelHandler);
                }
            }
        });
        try {
            channelFuture = serverBootstrap.bind().sync();
            Runtime.getRuntime().addShutdownHook(new Thread(()->{
                channelFuture.channel().close();
                bossGroup.shutdownGracefully();
                workGroup.shutdownGracefully();
            }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
