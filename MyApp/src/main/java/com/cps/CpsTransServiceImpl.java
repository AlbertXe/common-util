package com.cps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 15:19
 */
@Component
public class CpsTransServiceImpl {

    @Autowired
    private Environment environment;
    @Value("${cps.url:}")
    private String cpsUrl;
    @Autowired
    private ApiGatewayMaster apiGatewayMaster;


    public Map<String,Object> sendAuthMsg(Map<String, Object> tranMap) {

        Map<String, String> headMap = new HashMap<>();
        headMap.put("route_type", "card");
        headMap.put("route_value", (String) tranMap.get("pan"));
        Map<String, Object> cps = apiGatewayMaster.postApi("cps", cpsUrl, tranMap, headMap);
        return cps;
    }
}
