package com.cps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 13:20
 */
@Configuration

public class CpsConfig {
    @Value("#{'${logger.cpsField}'.split(',')}")
    private String cpfField;

}
