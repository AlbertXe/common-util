package com.cps;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 12:30
 */
@ChannelHandler.Sharable
public class CommMessagerEncoder extends MessageToMessageEncoder<CommMessager> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CommMessager commMessager, List<Object> list) throws Exception {
        list.add(Unpooled.wrappedBuffer(commMessager.getBody()));
    }
}
