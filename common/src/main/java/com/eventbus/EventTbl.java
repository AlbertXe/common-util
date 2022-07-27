package com.eventbus;

import lombok.Data;

/**
 * 事件表
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 18:55
 */
@Data
public class EventTbl {
    private String eventId;

    /**
     * 借贷类型
     */
    private String debitCdtCd;
    /**
     * 余额对象类型
     */
    private String balObjCd;
    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 接口名称  请求对象
     */
    private String intfcName;

}
