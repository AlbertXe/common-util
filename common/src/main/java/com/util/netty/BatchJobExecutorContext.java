package com.util.netty;

import java.util.Map;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 22:21
 */
public class BatchJobExecutorContext {
    private Map<String,Object> inputData ;
    private Map<String,Object> systemData ;
    private Map<String,Object> commReqData ;

    private BatchDataProcessorWrapper<Object,Object,Object> dataProcess;


}
