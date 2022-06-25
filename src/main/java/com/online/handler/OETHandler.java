package com.online.handler;

import com.util.springfactory.FactoriesLoader;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author xiehongwei
 * @date 2022/6/23 1:25 下午
 */
public class OETHandler {
    private final List<IOETBeforeHandler> beforeHandlerList;
    private final List<IOETAfterHandler> afterHandlerList;
    private final List<IOETExceptionHandler> exceptionHandlerList;
    private final List<IOETFinallyHandler> finallyHandlerList;

    public OETHandler(ServiceCategory serviceCategory) {
        beforeHandlerList = getHandlers(IOETBeforeHandler.class, serviceCategory);
        afterHandlerList = getHandlers(IOETAfterHandler.class, serviceCategory);
        exceptionHandlerList = getHandlers(IOETExceptionHandler.class, serviceCategory);
        finallyHandlerList = getHandlers(IOETFinallyHandler.class, serviceCategory);
    }

    public  <T> List<T> getHandlers(Class<T> type, ServiceCategory serviceCategory) {
        List<T> factories = FactoriesLoader.getAllFactories(type);
        // 可以通过服务策略在做分组处理
        return CollectionUtils.isEmpty(factories) ? Collections.EMPTY_LIST : Collections.unmodifiableList(factories);
    }

    public void beforeHandler(OETHandlerContext context) {
        handler(beforeHandlerList,context,OETHandlerType.BEFORE,false);
    }
    public void afterHandler(OETHandlerContext context) {
        handler(afterHandlerList,context,OETHandlerType.AFTER,false);
    }
    public void exceptionHandler(OETHandlerContext context) {
        handler(exceptionHandlerList,context,OETHandlerType.EXCEPTION,false);
    }
    public void finallyHandler(OETHandlerContext context) {
        handler(finallyHandlerList,context,OETHandlerType.FINALLY,false);
    }

    private void handler(List<? extends IOETHandler> handlers, OETHandlerContext context, OETHandlerType type, boolean ignoreError) {
        try {
            for (IOETHandler handler : handlers) {
                try {
                    handler.handler(context);
                } catch (Exception e) {
                    if (!ignoreError) {
                        throw new RuntimeException();
                    }
                } finally {

                }

            }
        } finally {

        }
    }
}
