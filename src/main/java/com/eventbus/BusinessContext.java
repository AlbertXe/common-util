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

    private String txnCd;
    private String custId;
    private boolean companyCust;
    private String eventId;
    private Object request;
    private Object response;
    private DataMap requestMap;
    private Function<BusinessContext,?> responseSupplier;

    private DimensionMap dimensionMap;

    private String currEventId;
    private Object currEvent;
    private String currActivityId;
    private Object currActivity;





}
