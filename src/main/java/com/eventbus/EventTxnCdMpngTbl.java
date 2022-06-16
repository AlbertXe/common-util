package com.eventbus;

import lombok.Data;

/**
 * 交易处理码与事件映射表
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-13 20:05
 */
@Data
public class EventTxnCdMpngTbl {
    private String txnPcsTpCd;
    private String eventId;
    /**
     * 只读交易标识
     */
    private String readOnlyFlg;
    /**
     * 非金融交易标识
     */
    private String nonFinTxnFlg;
    /**
     * 等级通讯日志模式
     * 0 不登记
     * 1 等级不检查
     * 2 登记防重处理
     * 3 等级幂等处理
     */
    private String registerMode;

    
    
    
    
    
}
