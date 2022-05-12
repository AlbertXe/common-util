package com.util.netty;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 21:28
 */
@Data
public class RpcRequest {
    private static final AtomicLong INVOKE_ID = new AtomicLong(0L);
    private SysHeader sysHeader;
    private String content;
    private Long timeout=0L;

    
}
