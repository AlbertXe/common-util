package com.eventbus;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-07-04 00:20
 */
public class PostingExecutor<Q extends AbstractRequest,P extends AbstractResponse> extends AbstractExecutor<Q,P,Q,P> {
    @Override
    protected Q inputToRequest(BusinessContext ctx, Event event, Q input) {
        return input;
    }

    @Override
    protected P responseToOutput(BusinessContext ctx, Event event, P response) {
        return response;
    }

    @Override
    protected Event createEvent(BusinessContext ctx) {
        // 尝试锁客户 for update
        ctx.setCompanyCust(false);
        return Event.createEvent(ctx.getEventId());
    }
}
