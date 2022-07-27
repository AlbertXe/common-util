package com.util.netty;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-11 22:20
 */
public class BatchJobBeforeAndAfterProcess {
    public void before(BatchJobExecutorContext context, BatchStep batchStep) {

    }

    public void after(BatchJobExecutorContext context, BatchStep batchStep,Long totalTime) {

    }

    public void exceptionProcss(BatchJobExecutorContext context, BatchStep batchStep, Throwable throwable) {

    }
}
