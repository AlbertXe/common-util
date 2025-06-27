package com.cps;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 12:34
 */
@ChannelHandler.Sharable
public class TcpAcceptorHandler<Conf extends TcpConfig> extends ChannelInboundHandlerAdapter {
    protected CpsInDoHandler handler;
    protected Conf config;
    protected Map<String, Channel> channelPool = new ConcurrentHashMap<>();
    private static boolean isReadFinish = false;

    public TcpAcceptorHandler(CpsInDoHandler handler, Conf tcpConfig) {
        this.handler = handler;
        this.config = tcpConfig;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CommMessager commMessager = (CommMessager) msg;
        if (!isReadFinish) {
            isReadFinish = true;
        }
        // 是否异步支持
        if (config.isAsync()) {

        }else {
            handler.doHandler(commMessager, config);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        if (isReadFinish) {
            // 短链接
            if (true) {
                isReadFinish = false;
            }
        }else {
            ctx.read();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 非短链接
        if (true) {
            channelPool.put(ctx.channel().id().asLongText(), ctx.channel());
        }
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 非短链接
        if (true) {
            channelPool.remove(ctx.channel().id().asLongText());
        }
        super.channelInactive(ctx);
    }
}
