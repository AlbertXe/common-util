package com.online.handler.before;

import com.online.ConfigReloaderFactory;
import com.online.handler.IOETBeforeHandler;
import com.online.handler.OETHandlerContext;
import com.util.springfactory.SPIMeta;
import org.springframework.core.annotation.Order;

/**
 * @author xiehongwei
 * @date 2022/6/24 8:57 上午
 */
@SPIMeta(id = "initCache")
@Order(11)
public class OETInitCacheHandler implements IOETBeforeHandler {

    @Override
    public void handler(OETHandlerContext context) {
        ConfigReloaderFactory.allBeginNewSession(1,true);
    }
}
