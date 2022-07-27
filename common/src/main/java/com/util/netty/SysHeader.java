package com.util.netty;

import lombok.Data;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 21:30
 */
@Data
public class SysHeader {
    private String clientId;
    private String reqId;
    private String version;
}
