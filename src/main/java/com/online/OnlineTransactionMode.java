package com.online;

public enum OnlineTransactionMode {
    READ_ONLY("R","只读模式"),
    ATOMIC("A","原子模式"),
    CHUNK("C","事物模块"),
    DISTRIBUTED("D","分布式事物"),
    ;
    private String id;
    private String name;

    OnlineTransactionMode(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
