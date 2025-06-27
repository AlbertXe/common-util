package com.cps;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 12:52
 */
@ChannelHandler.Sharable
public class ExceptionCaughtHandler extends ChannelInboundHandlerAdapter {
    private String ipPattern;

    public ExceptionCaughtHandler(String ipPattern) {
        this.ipPattern = ipPattern;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetAddress address = socketAddress.getAddress();

        Pattern compile = Pattern.compile(ipPattern);
        Matcher matcher = compile.matcher(address.getHostAddress());
        if (matcher.find()) {
            ctx.fireExceptionCaught(cause);
        }

    }
}
