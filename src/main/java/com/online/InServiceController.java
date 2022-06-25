package com.online;

import com.online.handler.ServiceCategory;
import lombok.Data;

/**
 * @author xiehongwei
 * @date 2022/6/23 3:37 下午
 */
@Data
public class InServiceController {
    private String innerServiceCode;
    private String innerServiceImplCode;
    private ServiceCategory serviceCategory;
    private ServiceType serviceType;
    private OnlineTransactionMode transactionMode;
    private String logLevel;
    private long timeout;
    private long startTime;

}
