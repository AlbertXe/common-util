package com.eventbus;


import java.time.OffsetDateTime;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 18:23
 */
public abstract class AbstractExecutor<I, O, Q extends Request, P extends Response>
        implements Executor<I, O, Q, P>, Timed {

    private boolean failed = false;
    private long startTime;
    private long endTime;
    private BusinessContext ctx;
    private Event event;
    private I input;
    private O output;
    private Q request;
    private P response;

    @Override
    public O execute(I input) {
        OffsetDateTime now = OffsetDateTime.now();
        startTime = now.toInstant().toEpochMilli();

        this.input = input;
        if (ctx == null) {
            ctx = new BusinessContext();
        }
        beforeExecute(ctx, input);

        initContext(ctx);
        
        return null;
    }

    protected void initContext(BusinessContext ctx) {
        
    }

    private void beforeExecute(BusinessContext ctx, I input) {
        
    }

    @Override
    public void attachContext(BusinessContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public BusinessContext context() {
        return ctx;
    }

    @Override
    public I input() {
        return input;
    }

    @Override
    public O output() {
        return output;
    }

    @Override
    public Q request() {
        return request;
    }

    @Override
    public P response() {
        return response;
    }

    @Override
    public Event event() {
        return event;
    }
}
