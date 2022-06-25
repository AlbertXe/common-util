package com.acctparent.fee;

import com.eventbus.BusinessContext;

import java.util.List;

/**
 * @author xiehongwei
 * @date 2022/4/11 11:21 上午
 */
public class FeeItem {
    private static final String DEFAULT_INSTT_CODE = "--------";
    private static final String DEFAULT_INSTT_VALUE = "--------";

    private String instCd;
    private String feeItemCd;
    private String instanceKey;
    private AcFeeItemTbl acFeeItemTbl;
    private FeeItemInstance instance;

    public FeeItem(String instCd, String feeItemCd) {
        this.instCd = instCd;
        this.feeItemCd = feeItemCd;
    }

    public FeeItem(AcFeeItemTbl acFeeItemTbl) {
        this.acFeeItemTbl = acFeeItemTbl;
    }

    public static List<String> matchInstanceValue(BusinessContext ctx, AcFeeItemTbl tbl, boolean fuzzyMatching) {
        for (int i = 0; i < tbl.getInsttDmsnCnt(); i++) {
            String dimensionCode = null;
            if (i == 0) {
                dimensionCode = tbl.getInsttDmsn1();
            }
            if (i == 1) {
                dimensionCode = tbl.getInsttDmsn2();
            }
            if (i == 2) {
                dimensionCode = tbl.getInsttDmsn3();
            }
            String value;
            if (DEFAULT_INSTT_CODE.equals(dimensionCode)) {
                value = DEFAULT_INSTT_VALUE;
            }else {
            }

        }
        return null;
    }
}
