package com.quartz;

import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author xiehongwei
 * @date 2021/10/16 10:02 上午
 */
public class BatchScheduleServerTest {

    @SneakyThrows
    @Test
    public void m1() {
        BatchScheduleServer server = new BatchScheduleServer();
        server.start();
        server.addJob();
        Thread.sleep(10000);
        System.out.println("end");


    }
}
