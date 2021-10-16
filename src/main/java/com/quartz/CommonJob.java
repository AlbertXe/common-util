package com.quartz;

import com.alibaba.fastjson.JSON;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author xiehongwei
 * @date 2021/10/16 9:58 上午
 */
public class CommonJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        System.out.println(JSON.toJSONString(jobDetail));
        System.out.println(Thread.currentThread().getName()+"doing");

    }
}
