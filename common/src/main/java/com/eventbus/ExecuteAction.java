package com.eventbus;

import lombok.Data;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 18:35
 */
@Data
public class ExecuteAction {
    public static final ExecuteAction CONTINUE = new ExecuteAction(null, null, null);
    public static final ExecuteAction FINISHED = new ExecuteAction(null, null, null);
    public static final ExecuteAction EXIT_TRANSACTION = new ExecuteAction(null, null, null);
    public static final ExecuteAction BREAK_EVENT = new ExecuteAction(null, null, null);

    private final Event event;
    private final Activity activity;
    private final Exception exception;

    public ExecuteAction(Event event, Activity activity, Exception exception) {
        this.event = event;
        this.activity = activity;
        this.exception = exception;
    }

    public static ExecuteAction failed(Event event, Activity activity, Exception e) {
        return new ExecuteAction(event, activity, e);
    }

    public boolean failed() {
        return exception != null;
    }
}
