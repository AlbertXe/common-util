package com.eventbus;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 18:27
 */
@Data
public class BusinessContext extends DataMap {
    private final Map<String, Integer> eventCounter = new HashMap<>();

    private String custId;

    private String currEventId;
    private Object currEvent;
    private String currActivityId;
    private Object currActivity;





}
