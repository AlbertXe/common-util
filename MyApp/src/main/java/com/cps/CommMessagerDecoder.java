package com.cps;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 12:33
 */
@ChannelHandler.Sharable
public class CommMessagerDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] bs = new byte[byteBuf.readableBytes()];
        byteBuf.getBytes(0, bs);
        CommMessager commMessager = new CommMessager();
        commMessager.setBody(bs);
        list.add(commMessager);
    }
}
