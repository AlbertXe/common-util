package com.online.datasource;

import com.util.netty.ResponseData;

/**
 * @author xiehongwei
 * @date 2022/6/24 10:11 上午
 */
public interface OnlineGlobalTransaction {
    void beginTransaction();

    void commitTransaction(ResponseData v1);

    void rollbackTransaction(ResponseData v1);

    void close();

    /**
     * 创建分支事物
     */
    default void createBranchTransaction(OnlineBranchTransactionContext ctx){};

    default void closeBranchTransaction(){};

}
