package com.eventbus;

import lombok.Data;

/**
 * 活动触发事件表
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-13 09:44
 */
@Data
public class ActyTrgrEvntTbl {
    private String initEventId;
    /**
     * 活动编号
     */
    private String actyNo;
    /**
     * 识别因子
     */
    private String idfyInsttCd;
    /**
     * 触发事件号
     */
    private String trgrEventId;

    /**
     * 触发方式代码
     */
    private String trgrMthdCd;

    /**
     * 触发模式代码 SYNC同步  异步ASYNC
     */
    private String trgrModeCd;

    
}
