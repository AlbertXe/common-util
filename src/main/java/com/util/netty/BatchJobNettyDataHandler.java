package com.util.netty;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 21:44
 */
public class BatchJobNettyDataHandler extends RpcHandler {
    @Override
    protected RpcResponse doHandle(RpcRequest request) {
        Facade facade = new JobServerTxnFacade();
        BatchServletHandler servlet ;
        return null;
    }
}
