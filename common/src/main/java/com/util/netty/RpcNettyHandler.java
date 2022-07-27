package com.util.netty;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 21:25
 */
public class RpcNettyHandler extends ChannelInboundHandlerAdapter {
    private RpcServer server;
    public RpcNettyHandler(RpcServer server) {
        this.server = server;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (StringUtils.isEmpty(msg)) {
            return;
        }
        String reqJson = (String) msg;
        RpcRequest reqObj = JSON.parseObject(reqJson, RpcRequest.class);
        server.getHandlePool()
                .submit(()->{
                    server.getHandler()
                            .handle(reqObj);
                });


    }
}
