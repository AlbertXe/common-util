package com.online.datasource;


import com.util.springfactory.FactoriesLoader;

/**
 * @author xiehongwei
 * @date 2022/6/24 10:22 上午
 */
public class EdspCoreBeanUtil {

    public static DBConnectionManager getDBConnectionManager() {
        return FactoriesLoader.getNewestFactory(DBConnectionManager.class);
    }

}
