package com.util.netty;

import groovy.json.internal.Charsets;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import lombok.Data;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 20:57
 */
@Data
public class RpcNettyChannelInitializer extends ChannelInitializer<SocketChannel> {
    private RpcServer server;
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("frameDecoder",
                new LengthFieldBasedFrameDecoder(2147483647, 0, 8, 0, 8, false));
        pipeline.addLast("frameEncode",
                new LengthFieldPrepender(8));
        pipeline.addLast("decoder",
                new StringDecoder(Charsets.UTF_8));
        pipeline.addLast("encode",
                new StringDecoder(Charsets.UTF_8));
        pipeline.addLast("handler",
                new RpcNettyHandler(server));

    }
}
