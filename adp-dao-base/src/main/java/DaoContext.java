import lombok.Data;

import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程安全 每个线程有自己的daoContext
 * @author xiehongwei
 * @date 2022/12/29 2:12 下午
 */
@Data
public class DaoContext {
    private int rowCount;
    private long startTime;
    private long timeout;
    private long tranTimeout;
    private boolean timeoutFlag = false;
    private Statement statement;
    private String dataSourceId;
    private volatile boolean afterExceptionProcess;

    private boolean forceNoCacheFlag = false;

    private static final Map<String, DaoContext> CONTEXT_MAP = new ConcurrentHashMap<>();

    private static ThreadLocal<DaoContext> instance = new ThreadLocal<DaoContext>(){
        @Override
        protected DaoContext initialValue() {
            DaoContext daoContext = super.initialValue();
            if (daoContext != null) {
                CONTEXT_MAP.put(Thread.currentThread().getName(), daoContext);
            }
            return daoContext;
        }

        @Override
        public void set(DaoContext value) {
            CONTEXT_MAP.put(Thread.currentThread().getName(), value);
            super.set(value);
        }

        @Override
        public void remove() {
            CONTEXT_MAP.remove(Thread.currentThread().getName());
            super.remove();
        }
    };

    public static DaoContext get() {
        return instance.get();
    }



}
