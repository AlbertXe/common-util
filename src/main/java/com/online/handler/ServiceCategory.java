package com.online.handler;

import lombok.Getter;

/**
 * @author xiehongwei
 * @date 2022/6/23 1:40 下午
 */
@Getter
public enum ServiceCategory {
    T("flow"),
    S("service"),
    M("method"),
    F("bpl");
    private String id;

    ServiceCategory(String id) {
        this.id = id;
    }
}
