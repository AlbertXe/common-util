package com.util;

import lombok.Data;

/**
 * @description: 通过xml配置插件 将xml转化为bean
 * @author: AlbertXe
 * @create: 2022-05-07 10:10
 */
@Data
public class PluginInfo {
    private String id;
    private String name;
    private String classFullName;

    
}
