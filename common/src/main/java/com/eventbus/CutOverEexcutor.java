package com.eventbus;

import org.junit.Assert;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-07-04 00:13
 */
public class CutOverEexcutor extends DefaultExecutor {

    @Override
    protected Event createEvent(BusinessContext ctx) {
        EventTxnCdMpngTbl mapping = new EventTxnCdMpngTbl();
        ctx.setEventId(mapping.getEventId());
        return Event.createEvent(ctx.getEventId());
    }

    @Override
    protected void initContext(BusinessContext ctx) {
        Assert.assertNotNull(ctx.getTxnCd());
    }
}
