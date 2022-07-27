package com.online.handler.exception;

import com.online.EngineContext;
import com.online.handler.IOETFinallyHandler;
import com.online.handler.OETHandlerContext;
import com.util.springfactory.SPIMeta;
import org.springframework.core.annotation.Order;

/**
 * 上下文重置
 * @author xiehongwei
 * @date 2022/6/24 8:57 上午
 */
@SPIMeta(id = "resetContext")
@Order(11)
public class OETResetContextHandler implements IOETFinallyHandler {

    @Override
    public void handler(OETHandlerContext context) {
        EngineContext.pop();

    }
}
