package com.online.handler.exception;

import com.online.handler.IOETExceptionHandler;
import com.online.handler.OETHandlerContext;
import com.util.springfactory.SPIMeta;
import org.springframework.core.annotation.Order;

/**
 * 返回报文组装
 * @author xiehongwei
 * @date 2022/6/24 8:57 上午
 */
@SPIMeta(id = "initCache")
@Order(11)
public class OETExceptionHandler implements IOETExceptionHandler {

    @Override
    public void handler(OETHandlerContext context) {
        Throwable exception = context.getException();

        //TODO 组装错误报文
    }
}
