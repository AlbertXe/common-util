package com.file.excel;

import com.google.common.collect.Table;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

public class ExcelUtil {

    @SneakyThrows
    public static void write(File csv, Table<Integer,Integer,String> table) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int i = 0;
        for (Integer rowKey : table.rowKeySet()) {
            Map<Integer, String> row = table.row(rowKey);
            Row sheetRow = sheet.createRow(i);
            int j = 0;
            for (Integer key2 : row.keySet()) {
                Cell cell = sheetRow.createCell(j);
                cell.setCellValue(table.get(rowKey,key2));
                j++;
            }
            i++;
        }
        workbook.write(new FileOutputStream(csv));
    }
}
