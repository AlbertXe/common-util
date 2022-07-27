package com.online;

import com.online.handler.OETHandlerContext;
import org.apache.logging.log4j.ThreadContext;

/**
 * @author xiehongwei
 * @date 2022/6/23 6:21 下午
 */
public class OnlineLogEngineUtil {
    public static void setBizLog(String logLevel, OETHandlerContext context) {
        ThreadContext.put("busi_log", logLevel);
    }

}
