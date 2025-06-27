package com.cps;

import lombok.Data;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 10:08
 */
@Data
public class TcpConfig {
    private String host;
    private Integer port;
    private Integer bossGroup;
    private Integer workGroup;
    private CpsInDoHandler handler;
    private boolean async;
    private boolean shortLink;
    private String commName;
    
    
    
    
}
