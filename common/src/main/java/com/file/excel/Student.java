package com.file.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2023-06-06 21:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    /**
     * 写的时候有用
     */
    @ExcelProperty(value = "姓名", converter = MyConverter.class)
    private String name;
    @ExcelProperty("年龄")
    private int age;
    @ExcelProperty("地址")
    private String address;
    private BigDecimal money;
}
