package com.acctparent.fee;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author xiehongwei
 * @date 2022/4/11 1:27 下午
 */
@Data
public class AcFeeItemTbl {
    private String instCd;
    private String feeItemCd;
    private String feeEvntId;
    private String feeItemTp;
    private LocalDate bgnDt;
    private LocalDate endDt;
    private int insttDmsnCnt;
    private String insttDmsn1;
    private String insttDmsn2;
    private String insttDmsn3;
    private String insttDmsn4;
    private String insttDmsn5;
    private String insttDmsn6;
    private String insttDmsn7;
    private String feeItemDesc;
    private String oprFlg;
    private int versionNo;
}
