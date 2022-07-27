package com.util.sequence;

import lombok.Data;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-10 18:57
 */
@Data
public class SequenceInfo {
    private String name;
    private int step;
    private int cacheSize;

    private Long max;
    private Long min;
    private Long current;

    
    
    
}
