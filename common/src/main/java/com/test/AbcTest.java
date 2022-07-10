package com.test;

import com.acctparent.fee.AcFeeItemTbl;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiehongwei
 * @date 2022/7/9 7:35 下午
 */
public class AbcTest {
    @Test
    public void m1() {
        List<AcFeeItemTbl> acFeeItemTblList = new ArrayList<>();
        AcFeeItemTbl fee1 = new AcFeeItemTbl();
        fee1.setInstCd("bocom1");
        fee1.setFeeEvntId("901");
        acFeeItemTblList.add(fee1);
        AcFeeItemTbl fee2 = new AcFeeItemTbl();
        fee2.setInstCd("bocom1");
        fee2.setFeeEvntId("902");
        acFeeItemTblList.add(fee2);
        AcFeeItemTbl fee3 = new AcFeeItemTbl();
        fee3.setInstCd("bocom3");
        fee3.setFeeEvntId("903");
        acFeeItemTblList.add(fee3);

        Map<String, List<AcFeeItemTbl>> map = acFeeItemTblList.stream().collect(Collectors.toMap(t -> t.getInstCd(),
                t -> {
                    List<AcFeeItemTbl> list = new ArrayList<>();
                    list.add(t);
                    return list;
                }, (t1, t2) -> {
                    t1.addAll(t2);
                    return t1;
                }));
        System.out.println(map);
        System.out.println(map.get("bocom1"));
        System.out.println(map.get("bocom2"));
        System.out.println(map.get("bocom3"));

        Map<String, List<AcFeeItemTbl>> map2 = new HashMap<>();
        acFeeItemTblList.stream().forEach(t->{
            map2.merge(t.getInstCd(), Lists.newArrayList(t),(a, b)->{
                a.addAll(b);
                return a;
            });
        });
        System.out.println(map2);

    }
}
