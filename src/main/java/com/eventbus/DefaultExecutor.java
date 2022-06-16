package com.eventbus;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 18:22
 */

public class DefaultExecutor extends
        AbstractExecutor<Map<String,Object>,Map<String,Object>,AbstractRequest,AbstractResponse> {
    @Autowired
    private ApplicationContext context;

    @Override
    protected Map<String, Object> responseToOutput(BusinessContext ctx, Event event, AbstractResponse response) {
//        Serializer
        return null;
    }

    @Override
    protected AbstractRequest inputToRequest(BusinessContext ctx, Event event, Map<String, Object> input) {
        String requestName = event.event().getIntfcName() + "Request";

        AbstractRequest request = (AbstractRequest) context.getBean(requestName);
        BeanUtils.copyProperties(input,request);

//        BeanValidator.validate(request);
        return null;
    }
}
