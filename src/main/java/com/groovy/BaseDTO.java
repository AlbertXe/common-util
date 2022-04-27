package com.groovy;

import lombok.Data;

import java.time.LocalDate;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-04-27 17:48
 */
@Data
public class BaseDTO {
    private int id;
    private String crtUser;
    private LocalDate crtDate;


}
