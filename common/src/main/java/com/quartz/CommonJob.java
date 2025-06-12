package com.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

/**
 * @author xiehongwei
 * @date 2021/10/16 9:58 上午
 */
public class CommonJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        System.out.println(Thread.currentThread().getName()+"===============>>>doing");

    }
}
