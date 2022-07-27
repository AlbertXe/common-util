package com.util.netty;

import lombok.Data;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 21:38
 */
@Data
public class RpcResponse {
    private String content;
    private String reqId;
    private String retCode = "000";
    private String retMsg;
    private String status;
    private String inner;

}
