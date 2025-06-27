package com.cps;

import com.eventbus.SpringUtils;
import io.netty.channel.ChannelHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 12:51
 */
@ChannelHandler.Sharable
public class CpsInDoHandler {

    private static CpsTransServiceImpl cpsTransService;

    static {
        cpsTransService = SpringUtils.getBean(CpsTransServiceImpl.class);
    }



    public CommMessager doHandler(CommMessager msg, TcpConfig tcpConfig) {
        //TODO 解包
        byte[] body = msg.getBody();
        Map<String, Object> unpack = new HashMap<>();

        // 交易处理
        Map<String, Object> pack = cpsTransService.sendAuthMsg(unpack);
        String actionCode = (String) pack.get("actionCode");


        CommMessager commMessager = new CommMessager();
        //TODO 转成byte
//        commMessager.setBody(Unpooled.);
        return null;
    }

}
