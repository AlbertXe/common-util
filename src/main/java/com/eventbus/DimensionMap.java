package com.eventbus;

import com.acctparent.fee.FeeItem;

import java.util.EnumMap;
import java.util.Objects;
import java.util.function.Function;



/**
 * 维度manp
 * @author xiehongwei
 * @date 2022/6/21 4:49 下午
 */
public final class DimensionMap {

    private final EnumMap<Dimension, Object> enumMap = new EnumMap<Dimension, Object>(Dimension.class);

    public <T> T getDimension(Dimension dimension) {
        return (T) enumMap.get(dimension);
    }

    public String getInstanceValue(String dimensionCode, boolean fuzzyMatching) {
        Dimension dimension = Dimension.from(dimensionCode);
        Object obj  = getDimension(dimension);
        // 模糊匹配
        if (fuzzyMatching && obj == null) {
            return FeeItem.DEFAULT_INSTT_VALUE;
        }
        if (obj.toString().isEmpty() || Objects.equals(obj.toString(), FeeItem.DEFAULT_INSTT_VALUE)) {
            throw new RuntimeException();
        }
        return obj.toString();
    }

    public enum Dimension{
        D_CUST_LYR_RAND("INSC","客户分层标识及随机数"),
        D_INSTM_TERM("TERM","分期期数"),
        D_PRCIP_POST_MTHD("PRCI","本金入账方式"),
        D_INSTM_FEE_MTHD("FTYP","分期费用收取方式"),
        ;
        String value;
        String desc;
        Function<BusinessContext,Object> loader;

        Dimension(String value,String desc) {
            this.value = value;
            this.desc = desc;
        }

        Dimension() {

        }

        public static Dimension from(String dimensionCode) {
            if (dimensionCode == null) {
                return null;
            }
            for (Dimension dimension : values()) {
                if (dimensionCode.equals(dimension.value)) {
                    return dimension;
                }
            }
            throw new RuntimeException();
        }
    }
}
