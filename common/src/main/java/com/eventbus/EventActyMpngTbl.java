package com.eventbus;

import lombok.Data;

/**
 * 事件活动映射表
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 21:15
 */
@Data
public class EventActyMpngTbl {
    private String acTyId;
    private String evntId;
    private int actyExecSeqNo;
    /**
     * 会计用途标志
     */
    private int acctgPurpFlg;
    

    

}
