package com.online;

public enum ServiceType {
    CHECK("check"),
    TRY("try"),
    CONFIRM("confirm"),
    CANCEL("cancel"),
    NOTIFY("notify")
    ;

    private String value;

    ServiceType(String value) {
        this.value = value;
    }
}
