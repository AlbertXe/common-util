package com.quartz;

import lombok.SneakyThrows;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 定时任务
 * @author xiehongwei
 * @date
 */
public class BatchScheduleServer {
    private Scheduler scheduler;

    public void start() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException e) {

        }
    }

    @SneakyThrows
    public void deleteAll() {
        scheduler.clear();
    }

    @SneakyThrows
    public void addJob(String jobName) {
        JobDetail jobDetail = JobBuilder.newJob().withIdentity(jobName).ofType(CommonJob.class).withDescription("job").build();
        CronTrigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *")).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
    public void deleteJob(String task) {
        JobKey jobKey = new JobKey(task);
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BatchScheduleServer server = new BatchScheduleServer();
        server.start();
        server.addJob("xhw-test");


    }


}
