package com.util.netty;

public abstract class RpcHandler {
    public final RpcResponse handle(RpcRequest request) {
        RpcResponse ret = doHandle(request);
        ret.setReqId(request.getSysHeader().getReqId());
        return ret;
    }

    protected abstract RpcResponse doHandle(RpcRequest request);
}
