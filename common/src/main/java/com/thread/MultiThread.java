package com.thread;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.jdbc.SqlRunner;

import javax.sql.DataSource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-04-02 21:19
 */
public class MultiThread {
    private static AtomicInteger total = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            run(executorService);
        }
    }

    private static void run(ExecutorService executorService) {
        long start = System.currentTimeMillis();
        List<Future<Result>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Future<Result> future = executorService.submit(new Callable<Result>() {
                @Override
                public Result call() throws Exception {
                    Result result = new Result();
                    result.setSum(finalI);
                    // 如果有数据库操作
                    String dirverClass = "com.mysql.cj.jdbc.Driver";
                    String url = "jdbc:mysql://localhost:3306/study?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
                    String username = "root";
                    String password = "root";
                    DataSource dataSource = new PooledDataSource(dirverClass, url, username, password);

                    if (finalI % 4 == 0) {
                        Thread.sleep(50);
                    }

                    SqlRunner sqlRunner = new SqlRunner(dataSource.getConnection());
                    sqlRunner.update("update book set name='user" + total.getAndIncrement() + "' where id=1");
                    Map<String, Object> select_id_from_book_ = sqlRunner.selectOne("select * from book ");
                    System.out.println(select_id_from_book_);
                    return result;
                }
            });
            futures.add(future);
        }
        int sum = 0;
        for (int i = futures.size()-1; i >= 0; i--) {
            Future<Result> future = futures.get(i);
            try {
                Result result = future.get();
                sum += result.getSum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        String msg = MessageFormat.format("sum={0},耗时：{1}ms", sum, System.currentTimeMillis() - start);
        System.out.println(msg);

    }

}

class Result {

    private Integer sum;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
