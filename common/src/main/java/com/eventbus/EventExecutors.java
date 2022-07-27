package com.eventbus;

import java.util.Map;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 18:20
 */
public class EventExecutors {
    /**
     * 联机 额度 批量
     * @param map
     * @return
     */
    public static Map<String, Object> executeOnlineEvent(Map<String, Object> map) {
        DefaultExecutor executor = new DefaultExecutor();
        return executor.execute(map);
    }

    public static Map<String, Object> executeCutOverEvent(Map<String, Object> map) {
        CutOverEexcutor executor = new CutOverEexcutor();
        return executor.execute(map);
    }

    public static <Q extends AbstractRequest, P extends AbstractResponse> P executePostingEvent(Q request) {
        PostingExecutor executor = new PostingExecutor();
        return (P) executor.execute(request);
    }

    public static <Q extends AbstractRequest, P extends AbstractResponse> P executePostingEvent(BusinessContext ctx,Q request) {
        PostingExecutor executor = new PostingExecutor();
        executor.attachContext(ctx);
        return (P) executor.execute(request);
    }

    public static <Q extends AbstractRequest, P extends AbstractResponse> P executeInnerEvent(Q request) {
        GenericExecutor executor = new GenericExecutor();
        return (P) executor.execute(request);
    }

    public static <Q extends AbstractRequest, P extends AbstractResponse> P executeInnerEvent(BusinessContext ctx,Q request) {
        GenericExecutor executor = new GenericExecutor();
        executor.attachContext(ctx);
        return (P) executor.execute(request);
    }



}
