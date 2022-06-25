package com.online.handler;

import lombok.Getter;

/**
 * @author xiehongwei
 * @date 2022/6/23 1:40 下午
 */
@Getter
public enum OETHandlerType {
    BEFORE,
    AFTER,
    EXCEPTION,
    FINALLY

    ;
}
