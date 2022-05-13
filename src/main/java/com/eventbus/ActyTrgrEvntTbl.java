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
    private String actyNo;
    private String idfyInsttCd;
    private String eventId;
}
