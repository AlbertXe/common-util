package com.eventbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 19:00
 */
public abstract class AbstractExecutable extends
    AbstractIdentifier implements Executable,Timed {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int MAX_EVENT_VISITS = 3;

    protected final DataMap input = new DataMap();
    protected final DataMap output = new DataMap();
    protected List<Executable> scheduledExecutables;
    private Consumer<BusinessContext> beforeConsumer;
    private Consumer<BusinessContext> afterConsumer;
    private long startTime;
    private long endTime;

    private Throwable t;

    @Override
    public ExecuteAction execute(BusinessContext ctx) {
        OffsetDateTime now = OffsetDateTime.now();
        startTime = now.toInstant().toEpochMilli();
        String busiSeq = null;

        int visit = 0;
        ExecuteAction action = null;
        try {
            if (this instanceof Event) {
                Event event = (Event) this;
                visit = ctx.getEventCounter()
                        .getOrDefault(event.eventNo(), 0);
                if (visit < MAX_EVENT_VISITS) {
                    ctx.getEventCounter()
                            .put(event.eventNo(), ++visit);
                    if (visit > 1) {
                        //说明在整个流程中该事件被调用了两次
                        logger.warn("duplicate event:{}", event);
                    }
                }else {
                    if (visit > 0) {
                        ctx.getEventCounter().remove(event.eventNo());
                        visit = 0;
                    }
                    throw new RuntimeException();
                }

            }
            if (beforeConsumer!=null) beforeConsumer.accept(ctx);
            beforeExecute(ctx);
            action = doExecute(ctx);
            afterExecute(ctx);
            if (afterConsumer !=null) afterConsumer.accept(ctx);
        } catch (RuntimeException e) {
            this.t = e;
            String errMsg = Catcher.wrapErrorMessage(e, this);
            logger.error(errMsg);
            Event event = this instanceof Event ? (Event) this : null;
            Activity activity = this instanceof Activity ? (Activity) this :null;
            return ExecuteAction.failed(event, activity, e);

        } finally {
            if (visit > 0) {
                Event event = (Event) this;
                if (visit == 1) {
                    ctx.getEventCounter().remove(event.eventNo());
                }else {
                    ctx.getEventCounter().put(event.eventNo(), --visit);
                }
            }
            endTime = Clock.SYSTEM.milliseconds();
            if (logger.isInfoEnabled()) {
                String type,id;
                if (this instanceof Event) {
                    type = "Event";
                    id = ((Event) this).eventNo();
                }else {
                    type = "Activity";
                    id = ((Activity) this).activityNo();
                }
                long deltaTime = endTime = startTime;
                if (!CollectionUtils.isEmpty(scheduledExecutables)) {
                    long innerEnd = scheduledExecutables.get(scheduledExecutables.size() - 1).endTime();
                    long innerStart = scheduledExecutables.get(0).startTime();
                    deltaTime -= innerEnd - innerStart;
                }

            }
        }
        return action;

    }

    protected void afterExecute(BusinessContext ctx) {
    }

    protected void beforeExecute(BusinessContext ctx) {

    }

    protected abstract ExecuteAction doExecute(BusinessContext ctx);


    @Override
    public DataMap input(BusinessContext ctx) {
        return input;
    }

    @Override
    public DataMap output(BusinessContext ctx) {
        return output;
    }

    @Override
    public <T extends Executable> T beforeConsumer(Consumer<BusinessContext> beforeConsumer) {
        this.beforeConsumer = beforeConsumer;
        return (T) this;
    }


    @Override
    public List<Executable> scheduledExecutables() {
        if (scheduledExecutables == null) {
            scheduledExecutables = new ArrayList<>();
        }
        return scheduledExecutables;
    }

    protected void addScheduledExecutable(Executable executable) {
        scheduledExecutables.add(executable);
    }

    @Override
    public <T extends Executable> T afterConsumer(Consumer<BusinessContext> afterConsumer) {
        this.afterConsumer = afterConsumer;
        return (T) this;
    }

    @Override
    public Throwable exceptionCaught() {
        return t;
    }

    @Override
    public long startTime() {
        return startTime;
    }

    @Override
    public long endTime() {
        return endTime;
    }
}
