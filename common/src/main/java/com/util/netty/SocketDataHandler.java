package com.util.netty;

public interface SocketDataHandler {
    void init(String v1, String v2);

    String handle(String v1);

    void destroy();
}
