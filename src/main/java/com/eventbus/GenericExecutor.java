package com.eventbus;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.Collections;
import java.util.Map;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-07-04 00:29
 */
@Scope(value = "propotype",proxyMode = ScopedProxyMode.DEFAULT)
public class GenericExecutor<Q extends AbstractRequest,P extends AbstractResponse> extends AbstractExecutor<Q,P,Q,P> {

    @Override
    protected Q inputToRequest(BusinessContext ctx, Event event, Q input) {
        return input;
    }

    @Override
    protected P responseToOutput(BusinessContext ctx, Event event, P response) {
        return output();
    }

    @Override
    protected Map<String, Object> requestToMap(BusinessContext ctx, Q request) {
        return Collections.emptyMap();
    }
}
