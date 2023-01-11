import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.*;

/**
 * @author xiehongwei
 * @date 2022/12/29 2:35 下午
 */
public class JdbcProfile {
    private static final ThreadLocal<String> sqlId = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "";
        }
    };

    /**
     * 概述 剖面
     * @param conn
     * @return
     */
    public static Connection profile(Connection conn) {
        return (Connection) Proxy.newProxyInstance(JdbcProfile.class.getClassLoader(),
                new Class[]{Connection.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String profile = "";
                        // 性能统计开关
                        if (true) {
                            if (!"commit".equals(method.getName()) && !"rollback".equals(method.getName())
                                    && !method.getName().startsWith("prepare")) {
                                profile = null;
                            }else {
                                profile = "jdbc." + method.getName() + "_" + sqlId.get();
                            }
                        }
                        Object ret = method.invoke(conn, args);


                        if (ret instanceof CallableStatement) {
                            profile(CallableStatement.class, ret);
                        } else if (ret instanceof PreparedStatement) {

                        } else if (ret instanceof Statement) {

                        }
                        return null;
                    }
                });
    }

    private static Object profile(Class<?> clz, Object stmt) {
        return Proxy.newProxyInstance(JdbcProfile.class.getClassLoader(),
                new Class[]{clz}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String profile = "";

                        // 性能统计开关
                        if (true) {
                            if (!method.getName().startsWith("execute")) {
                                profile = null;
                            }else {
                                profile = "jdbc." + method.getName() + "_" + sqlId.get();
                            }
                        }

                        Object ret = method.invoke(stmt, args);
                        if (!(ret instanceof ResultSet)) {
                            return ret;
                        }
                        profileResult(ret);

                        return null;
                    }
                });

    }

    private static Object profileResult(Object resultSet) {
        return Proxy.newProxyInstance(JdbcProfile.class.getClassLoader(),
                new Class[]{ResultSet.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        String profile = "";
                        // 性能统计开关
                        if (true) {
                            if (!method.getName().startsWith("next")) {
                                profile = null;
                            }else {
                                profile = "jdbc." + method.getName() + "_" + sqlId.get();
                            }
                        }

                        return null;
                    }
                });
    }
}
