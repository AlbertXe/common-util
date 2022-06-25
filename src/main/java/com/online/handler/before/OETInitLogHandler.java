package com.online.handler.before;

import com.online.OnlineLogEngineUtil;
import com.online.handler.IOETBeforeHandler;
import com.online.handler.OETHandlerContext;
import com.util.springfactory.SPIMeta;
import org.springframework.core.annotation.Order;

/**
 * 实现交易日志的级别动态更换
 * @author xiehongwei
 * @date 2022/6/23 3:32 下午
 */
@SPIMeta(id="initLog")
@Order(10)
public class OETInitLogHandler implements IOETBeforeHandler {
    @Override
    public void handler(OETHandlerContext context) {
        String logLevel = context.getInServiceController().getLogLevel();
        if (logLevel != null) {
            //TODO 具体怎么用的还待考虑
            OnlineLogEngineUtil.setBizLog(logLevel, context);
        }
    }
}
