package com.cps;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 15:26
 */
@Component
public class ApiGatewayMaster {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${api.gateway:}")
    private String listOfServers;


    public Map<String,Object> postApi(String channelId, String cpsUrl, Map<String, Object> tranMap,Map<String,String> headMap) {
        ServiceInstance instance = loadBalancerClient.choose(listOfServers);

        HttpHeaders httpHeaders = new HttpHeaders();
        headMap.forEach((k, v) -> httpHeaders.put(k, Lists.newArrayList(v)));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map> httpEntity = new HttpEntity<>(tranMap, httpHeaders);
        StringBuilder url = new StringBuilder();
        url.append(instance.getUri()).append(cpsUrl);
        ResponseEntity<Map> response = restTemplate.postForEntity(url.toString(), httpEntity, Map.class);
        return response.getBody();

    }
}
