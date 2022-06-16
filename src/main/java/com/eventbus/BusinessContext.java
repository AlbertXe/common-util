package com.eventbus;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 18:27
 */
@Data
public class BusinessContext extends DataMap {
    private final Map<String, Integer> eventCounter = new HashMap<>();


    private String custId;
    private String companyCust;
    private String eventId;
    private Object request;
    private Object response;
    private DataMap requestMap;
    private Function<BusinessContext,?> responseSupplier;



    private String currEventId;
    private Object currEvent;
    private String currActivityId;
    private Object currActivity;





}
