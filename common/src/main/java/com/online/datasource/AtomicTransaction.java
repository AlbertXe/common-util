package com.online.datasource;

import com.util.netty.ResponseData;

/**
 * @author xiehongwei
 * @date 2022/6/24 10:19 上午
 */
public class AtomicTransaction implements OnlineGlobalTransaction {


    @Override
    public void beginTransaction() {
        EdspCoreBeanUtil.getDBConnectionManager().beginTransaction();
    }

    @Override
    public void commitTransaction(ResponseData v1) {

    }

    @Override
    public void rollbackTransaction(ResponseData v1) {

    }

    @Override
    public void close() {

    }
}
