package com.online.handler.before;

import com.online.DaoContext;
import com.online.handler.IOETBeforeHandler;
import com.online.handler.OETHandlerContext;
import com.util.springfactory.SPIMeta;
import org.springframework.core.annotation.Order;

/**
 * 实现交易 超时时间动态设置
 * @author xiehongwei
 * @date 2022/6/23 3:32 下午
 */
@SPIMeta(id="initTimeout")
@Order(10)
public class OETInitTimeoutHandler implements IOETBeforeHandler {
    @Override
    public void handler(OETHandlerContext context) {
        long timeout = context.getInServiceController().getTimeout();
        DaoContext.get().setTimeout(timeout);
        DaoContext.get().setAfterExceptionProcess(false);
    }
}
