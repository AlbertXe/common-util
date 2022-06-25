package com.online.handler.after;

import com.online.handler.IOETAfterHandler;
import com.online.handler.OETHandlerContext;
import com.util.springfactory.SPIMeta;
import org.springframework.core.annotation.Order;

/**
 * 超时检查
 * @author xiehongwei
 * @date 2022/6/24 8:57 上午
 */
@SPIMeta(id = "initCache")
@Order(11)
public class OETTimeoutCheckHandler implements IOETAfterHandler {

    @Override
    public void handler(OETHandlerContext context) {
        long end = System.currentTimeMillis();
        if (context.getInServiceController().getTimeout() > 0
                && end - context.getInServiceController().getStartTime() > context.getInServiceController().getTimeout()) {
            throw new RuntimeException();
        }
    }
}
