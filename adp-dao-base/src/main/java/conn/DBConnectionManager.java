package conn;

import java.sql.Connection;

/**
 * @author xiehongwei
 * @date 2022/6/24 2:16 下午
 */
public interface DBConnectionManager {
    void getDatabaseId();

    void beginTransaction();

    void commit();

    void rollback();

    Connection getConnection();

    void close();
}
