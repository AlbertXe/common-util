package com.acctparent.fee;

import com.eventbus.BusinessContext;
import com.eventbus.DimensionMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiehongwei
 * @date 2022/4/11 11:21 上午
 */
public class FeeItem {
    public static final String DEFAULT_INSTT_CODE = "----";
    public static final String DEFAULT_INSTT_VALUE = "--------";

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
        String v1 = DEFAULT_INSTT_VALUE;
        String v2 = DEFAULT_INSTT_VALUE;
        String v3 = DEFAULT_INSTT_VALUE;
        String v4 = DEFAULT_INSTT_VALUE;
        String v5 = DEFAULT_INSTT_VALUE;
        String v6 = DEFAULT_INSTT_VALUE;
        String v7 = DEFAULT_INSTT_VALUE;
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
            } else {
                value = ctx.getDimensionMap().getInstanceValue(dimensionCode, fuzzyMatching);
            }
            if (i == 0) {
                v1 = value;
            } else if (i == 1) {
                v2 = value;
            } else if (i == 2) {
                v3 = value;
            } else if (i == 3) {
                v4 = value;
            } else if (i == 4) {
                v5 = value;
            } else if (i == 5) {
                v6 = value;
            } else if (i == 6) {
                v7 = value;
            }

        }
        return Arrays.asList(v1, v2, v3, v4, v5, v6, v7);
    }

    /**
     * @param ctx
     * @param tbl
     * @param dimensions
     * @return
     */
    public static List<String> matchInstanceValue(BusinessContext ctx, AcFeeItemTbl tbl, Set<DimensionMap.Dimension> dimensions) {
        String v1 = DEFAULT_INSTT_VALUE;
        String v2 = DEFAULT_INSTT_VALUE;
        String v3 = DEFAULT_INSTT_VALUE;
        String v4 = DEFAULT_INSTT_VALUE;
        String v5 = DEFAULT_INSTT_VALUE;
        String v6 = DEFAULT_INSTT_VALUE;
        String v7 = DEFAULT_INSTT_VALUE;
        for (int i = 0; i < tbl.getInsttDmsnCnt(); i++) {
            String dimensionCode = null;
            DimensionMap.Dimension dimension;
            if (i == 0) {
                dimension = DimensionMap.Dimension.from(tbl.getInsttDmsn1());
                if (dimensions.contains(dimension)) {
                    dimensionCode = tbl.getInsttDmsn1();
                }
            }
            if (i == 1) {
                dimension = DimensionMap.Dimension.from(tbl.getInsttDmsn2());
                if (dimensions.contains(dimension)) {
                    dimensionCode = tbl.getInsttDmsn2();
                }
            }
            if (i == 2) {
                dimension = DimensionMap.Dimension.from(tbl.getInsttDmsn3());
                if (dimensions.contains(dimension)) {
                    dimensionCode = tbl.getInsttDmsn3();
                }
            }
            String value;
            if (dimensionCode == null) {
                value = null;
            } else if (dimensionCode.equals(DEFAULT_INSTT_CODE)) {
                value = DEFAULT_INSTT_VALUE;
            } else {
                value = ctx.getDimensionMap().getInstanceValue(dimensionCode, false);
            }
            if (i == 0) {
                v1 = value;
            } else if (i == 1) {
                v2 = value;
            } else if (i == 2) {
                v3 = value;
            } else if (i == 3) {
                v4 = value;
            } else if (i == 4) {
                v5 = value;
            } else if (i == 5) {
                v6 = value;
            } else if (i == 6) {
                v7 = value;
            }

        }
        return Arrays.asList(v1, v2, v3, v4, v5, v6, v7);
    }

    public AcFeeItemTbl getAcFeeItemTbl() {
        //按照feeItemCd查询收费项目表
        return new AcFeeItemTbl();
    }

    public FeeItemInstance getFeeItemInstance(BusinessContext ctx) {
        AcFeeItemTbl acFeeItemTbl = getAcFeeItemTbl();
        List<String> values = matchInstanceValue(ctx, acFeeItemTbl, false);
        String key = values.stream().collect(Collectors.joining(":", "[", "]"));
        if (instance == null) {
            // 按照 feeItemCd 和 values查询收费实例表
            AcFeeItemInsttTbl acFeeItemInsttTbl = new AcFeeItemInsttTbl();
            if (acFeeItemInsttTbl == null) {
                throw new RuntimeException();
            }
            instance = new FeeItemInstance(this, acFeeItemInsttTbl);
        }
        return instance;
    }

    /**
     * 多条收费实例查询
     *
     * @param ctx
     * @param dimensions
     * @return
     */
    public List<FeeItemInstance> queryFeeItemInstances(BusinessContext ctx, Set<DimensionMap.Dimension> dimensions) {
        AcFeeItemTbl acFeeItemTbl = getAcFeeItemTbl();
        List<String> values = matchInstanceValue(ctx, acFeeItemTbl, dimensions);
        //按照多维度查询收费实例
        List<AcFeeItemInsttTbl> acFeeItemInsttTbls = new ArrayList<>();
        return acFeeItemInsttTbls.stream().map(t -> new FeeItemInstance(this, t))
                .collect(Collectors.toList());
    }

}
