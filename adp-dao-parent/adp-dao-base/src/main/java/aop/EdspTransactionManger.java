package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import conn.DBConnectionManager;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * 自定义事务注解功能
 * @author xiehongwei
 * @date 2022/12/29 4:52 下午
 */
@Aspect
@Component
public class EdspTransactionManger {
    @Pointcut("@annotation(annotation.EdspTransactional)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        DBConnectionManager connectionManager = null;
        Connection conn = connectionManager.getConnection();
        connectionManager.beginTransaction();
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        DBConnectionManager connectionManager = null;
        try {
            connectionManager.commit();
        } finally {
            connectionManager.close();
        }
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(JoinPoint joinPoint) {
        DBConnectionManager connectionManager = null;
        try {
            connectionManager.rollback();
        } finally {
            connectionManager.close();
        }
    }
}
