package com.util;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:生成对象工具
 * @author: AlbertXe
 * @create: 2025-06-17 21:28
 */
public class MySqlGenerateUtil {
    
    @Test
    public void test1() throws IOException, SQLException {
        List<Map<String, Object>> maps = JDBCUtil.executeSql("show tables;");
        for (Map<String, Object> map : maps) {
            String tableName = (String) map.get("TABLES_IN_STUDY");
            processTable(tableName);

        }

    }

    private void processTable (String tableName) throws SQLException {
        Map<String, Object> stringObjectMap = JDBCUtil.executeSql("show create table " + tableName + ";").get(0);
        String createSql = (String) stringObjectMap.get("CREATE TABLE");

        if (tableName.startsWith("tsp_")) {
            System.out.println(createSql);
            Pattern compile = Pattern.compile("`[a-zA-Z_]+`");
            Matcher matcher = compile.matcher(createSql);
            while (matcher.find()) {
                String group = matcher.group();
                System.out.println(group);

            }

        }

    }
}
