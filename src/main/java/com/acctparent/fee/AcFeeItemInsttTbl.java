package com.acctparent.fee;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xiehongwei
 * @date 2022/4/11 1:35 下午
 */
@Data
public class AcFeeItemInsttTbl {
    private String instCd;
    private String feeItemCd;
    private String insttCd1;
    private String insttCd2;
    private String insttCd3;
    private String insttCd4;
    private String insttCd5;
    private String insttCd6;
    private String insttCd7;
    /**
     * 计费方式代码
     */
    private String crgMthdCd;
    private BigDecimal feeItemBaseFee;
    private BigDecimal minCrgAmt;
    private BigDecimal maxCrgAmt;
    private String fee;
}
