package com.online.handler;

import com.online.InServiceController;
import com.online.datasource.OnlineGlobalTransaction;
import com.util.netty.RequestData;
import com.util.netty.ResponseData;
import lombok.Data;

/**
 * @author xiehongwei
 * @date 2022/6/23 1:28 下午
 */
@Data
public class OETHandlerContext {
    private final RequestData request;
    private final ResponseData response;
    private final InServiceController inServiceController;
    private OnlineGlobalTransaction transaction;

    private Throwable exception;
}
