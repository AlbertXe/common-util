package com.eventbus;

import java.util.function.Function;

/**
 * 维度manp
 * @author xiehongwei
 * @date 2022/6/21 4:49 下午
 */
public final class DimensionMap {


    public enum Dimension{
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
    }
}
