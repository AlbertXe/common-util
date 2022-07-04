package com.eventbus;


import lombok.SneakyThrows;
import org.springframework.cglib.beans.BeanMap;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Map;

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
        event = createEvent(ctx);
        request = inputToRequest(ctx, event, input);
        response = doExecute(ctx, event, request);
        output = responseToOutput(ctx, event, response);
        afterExecute(ctx, event, input, output);
        return output;
    }

    protected void afterExecute(BusinessContext ctx, Event event, I input, O output) {
    }

    protected abstract Q inputToRequest(BusinessContext ctx, Event event, I input);

    protected abstract O responseToOutput(BusinessContext ctx, Event event, P response);

    @SneakyThrows
    protected P doExecute(BusinessContext ctx, Event event, Q request) {
        if (request != null) {
            ctx.setRequest(request);
            Map<String, Object> input = requestToMap(ctx, request);
            if (input != null) {
                event.input(ctx).putAll(input);
                ctx.putAll(input);
            }
        }
        ExecuteAction action = event.execute(ctx);
        if (action.failed()) {
            onEventFailure(ctx, event, request, action.getException());
            failed = true;
            throw action.getException();
        }
        onEventSucceed(ctx, event, request);
        if (ctx.getResponse() != null) {
            return (P) ctx.getResponse();
        }
        if (ctx.getResponseSupplier() != null) {
            return (P) ctx.getResponseSupplier().apply(ctx);
        }
        return null;
    }

    protected void onEventSucceed(BusinessContext ctx, Event event, Q request) {

    }

    protected void onEventFailure(BusinessContext ctx, Event event, Q request, Exception exception) {

    }

    protected Map<String, Object> requestToMap(BusinessContext ctx, Q request) {
        if (request == null) {
            return Collections.emptyMap();
        }
        Map<String, Object> map = BeanMap.create(request);
        DataMap dataMap = new DataMap();
        dataMap.putAll(map);
        ctx.setRequestMap(dataMap);
        return map;
    }


    protected Event createEvent(BusinessContext ctx) {
        //按照交易处理码 查询交易事件映射表
        EventTxnCdMpngTbl mapping = new EventTxnCdMpngTbl();
        if (mapping.getReadOnlyFlg().equals("0")) {
            // 上锁
            // select * from isf_cust_inf for update
        }
        return Event.createEvent(ctx.getEventId());
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

    @Override
    public long startTime() {
        return startTime;
    }

    @Override
    public long endTime() {
        return endTime;
    }
}
