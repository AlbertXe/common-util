package com.file.excel;

import com.google.common.collect.HashBasedTable;
import org.junit.Test;

import java.io.File;

public class ExcelUtilTest {
    @Test
    public void test() {
        HashBasedTable<Integer, Integer, String> table = HashBasedTable.create();
        table.put(-1, -1, "head");
        table.put(0, 0, "00");
        table.put(0, 1, "01");
        table.put(1, 0, "10");
        table.put(1, 1, "11");
        ExcelUtil.write(new File("excelTest.xlsx"), table);
    }
}
