package com.online.handler.before;

import com.online.InServiceController;
import com.online.OnlineTransactionMode;
import com.online.ServiceType;
import com.online.handler.IOETBeforeHandler;
import com.online.handler.OETHandlerContext;
import com.util.springfactory.SPIMeta;
import org.springframework.core.annotation.Order;

/**
 * 实现交易 超时时间动态设置
 * @author xiehongwei
 * @date 2022/6/23 3:32 下午
 */
@SPIMeta(id="initTransaction")
@Order(10)
public class OETInitTransactionHandler implements IOETBeforeHandler {
    @Override
    public void handler(OETHandlerContext context) {
        InServiceController controller = context.getInServiceController();
        OnlineTransactionMode transactionMode = controller.getTransactionMode();
        ServiceType serviceType = controller.getServiceType();
        //事物设置


    }
}
