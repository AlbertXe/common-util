package com.util;

import org.apache.ibatis.jdbc.SqlRunner;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-18 19:51
 */
public class JDBCUtil {

    public static List<Map<String, Object>> executeSql(String sql,Object... args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("D:\\idea_study2\\common-util\\MyApp\\src\\main\\resources\\application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SqlRunner runner = new SqlRunner(connection);
        try {
            List<Map<String, Object>> maps = runner.selectAll(sql,args);
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.exe
            return maps;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
