package com.acctparent.fee;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiehongwei
 * @date 2022/7/2 1:43 上午
 */
@Data
public class FeeCalcFlow {
    private static final String SPACE2 = "  ";
    private static final String SPACE4 = SPACE2+SPACE2;
    private static final String SPACE8 = SPACE4 + SPACE4;

    private final BigDecimal txnAmt;
    private final LocalDate txnDate;
    private final String  cardNo;
    private final String  custNo;
    
    private boolean replay;
    private BigDecimal baseFeeAmt;
    private BigDecimal baseFeeAmtBeforeMinMaxCheck;
    private BigDecimal finalFeeAmt;
    private BigDecimal baseFeeRate;
    private BigDecimal finalFeeRate;


    @Data
    public abstract static class CalcStep{
        protected String input;
        protected String output;
        protected List<String> formulas = new ArrayList<>();
        private boolean replay;

        /**
         * 添加公式
         * @param template
         * @param args
         */
        public void addFormulas(String template, Object... args) {
            if (replay) {
                String format = String.format(template, args);
                formulas.add(format);
            }
        }

        public abstract void petty(StringBuilder sb);

    }

    @Data
    public static class TxnFeeCalcStep extends CalcStep{
        private BigDecimal txnAmt;
        private BigDecimal feeeAmt;
        private BigDecimal feeRate;
        private BigDecimal feeAmtBeforeMinMaxCheck;
        private String crsMthd;
        private String feeMtrxCalcMthdCd;


        @Override
        public void petty(StringBuilder sb) {
            sb.append("----------TxnFee----------\n");
            sb.append("input-->").append(this.toString()).append("\n");
            for (String formula : formulas) {
                sb.append(SPACE8).append(formula).append("\n");
            }
            sb.append("output--> [feeAmtBeforeMinMaxCheck=" + feeAmtBeforeMinMaxCheck
                    + ",feeAmt=" + feeeAmt
                    + ",feeRate=" + feeRate+"\n");
        }
    }

    /**
     * 折扣计算步骤
     */
    public static class PromotionCalcStep extends CalcStep{
        private BigDecimal inFeeAmt;
        private BigDecimal feeAmt;
        private BigDecimal promnPctg;


        @Override
        public void petty(StringBuilder sb) {
            sb.append("----------Promotion----------\n");
            sb.append("input-->").append(this.toString()).append("\n");
            for (String formula : formulas) {
                sb.append(SPACE8).append(formula).append("\n");
            }
            sb.append("output--> [feeAmt=" + feeAmt+"\n");
        }
    }

    public static class MerchantSubsidyCalcStep extends CalcStep {
        private BigDecimal txnAmt;
        private BigDecimal feeAmt;
        private BigDecimal totalFeeAmt;

        @Override
        public void petty(StringBuilder sb) {

        }
    }

}
