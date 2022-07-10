package com.acctparent.fee;

import com.eventbus.BusinessContext;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 收费项目实例 计算费用
 * @author xiehongwei
 * @date 2022/4/11 1:33 下午
 */
public class FeeItemInstance {
    /**
     * 固定金额F  费用矩阵M
     */
    private static final String CRG_MTHD_F = "F";
    private static final String CRG_MTHD_M = "M";
    /**
     * S 全额套档  P 超额累进
     */
    private static final String FEE_MTRX_CALC_MTHD_S = "S";
    private static final String FEE_MTRX_CALC_MTHD_P = "P";

    private String instCd;
    private String feeItemCd;
    private FeeItem feeItem;
    private AcFeeItemInsttTbl acFeeItemInsttTbl;

    public FeeItemInstance(FeeItem feeItem, AcFeeItemInsttTbl acFeeItemInsttTbl) {
        this.feeItem = feeItem;
        this.acFeeItemInsttTbl = acFeeItemInsttTbl;
        this.instCd = acFeeItemInsttTbl.getInstCd();
        this.feeItemCd = acFeeItemInsttTbl.getFeeItemCd();
    }

    public FeeCalcFlow calcTxnFee(BusinessContext ctx, BigDecimal txnAmt, LocalDate txnDate) {
        return calcTxnFee(ctx, acFeeItemInsttTbl, txnAmt, txnDate);
    }

    private FeeCalcFlow calcTxnFee(BusinessContext ctx, AcFeeItemInsttTbl acFeeItemInsttTbl, BigDecimal txnAmt, LocalDate txnDate) {
        //TODO
//        FeeCalcFlow flow = new FeeCalcFlow();
        return null;
    }
}
