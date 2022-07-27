package com.eventbus;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 21:23
 */
public abstract class AbstractActivity extends
    AbstractExecutable implements Activity{
    public static final String ACTIVITY_NAME_SUFFIX = "Activity";
    public static final int ACTIVITY_NAME_LEN = 6 + ACTIVITY_NAME_SUFFIX.length();
    private List<EventTbl> triggerEvents = new ArrayList<>();
    protected Event initEvent;
    protected ActivityTbl activityTbl;
    protected boolean skipTriggerEvent;

    private Map<EventTbl,DataMap> triggerEventParams;
    private Map<EventTbl,String> instanceCdEventMap;

    @Override
    protected ExecuteAction doExecute(BusinessContext ctx) {
        String busiSeq = null;
        ExecuteAction action = doExecuteActivity(ctx);
        if (action.failed()) {
            return action;
        }
        if (skipTriggerEvent) {
            return action;
        }
        ExecuteAction newAction = triggerEvents(ctx);
        if (action == ExecuteAction.CONTINUE) {
            return newAction;
        }
        return newAction.failed() ? newAction : action;
    }

    protected abstract ExecuteAction doExecuteActivity(BusinessContext ctx);

    @Override
    public ExecuteAction triggerEvents(BusinessContext ctx) {
        List<EventTbl> triggerEvents = populateTriggerEvents(ctx);
        if (CollectionUtils.isEmpty(triggerEvents)) {
            return ExecuteAction.CONTINUE;
        }
        ExecuteAction action = ExecuteAction.CONTINUE;
        for (EventTbl eventTbl : triggerEvents) {
            Event event = Event.createEvent(eventTbl.getEventId());
            //
            addScheduledExecutable(event);
            event.input(ctx).putAll(input);
            String idfyInstanceCd = instanceCdEventMap.get(eventTbl);
            onTriggerEventInput(ctx, event, idfyInstanceCd);
            if (triggerEventParams != null) {
                DataMap params = triggerEventParams.get(eventTbl);
                event.input(ctx).putAll(params);
            }
            action = event.execute(ctx);

            action = triggerExecuteActionFunction(ctx, event, action);

            if (action != ExecuteAction.CONTINUE) {
                break;
            }
            onTriggerEventOutput(ctx, event, idfyInstanceCd, action);
        }
        return action;
    }

    protected void onTriggerEventOutput(BusinessContext ctx, Event event, String idfyInstanceCd, ExecuteAction action) {
    }

    protected ExecuteAction triggerExecuteActionFunction(BusinessContext ctx, Event event, ExecuteAction action) {
        return action;
    }

    protected void onTriggerEventInput(BusinessContext ctx, Event event, String idfyInstanceCd) {

    }

    protected List<EventTbl> populateTriggerEvents(BusinessContext ctx) {
        if (!CollectionUtils.isEmpty(triggerEvents)) {
            return triggerEvents;
        }
        //按照 原事件号+活动号+触发标识 查找活动触发事件表
        ActyTrgrEvntTbl actyTrgrEvntTbl = new ActyTrgrEvntTbl();
        EventTbl eventTbl = new EventTbl();
        if (eventTbl != null) {
            triggerEvents.add(eventTbl);
            instanceCdEventMap.put(eventTbl, "--------");
        }
        return triggerEvents;
    }

    @Override
    public ActivityTbl activity() {
        if (activityTbl == null) {
            //按照事件号和活动好查询活动表
        }
        return activityTbl;
    }

    public void setInitEvent(Event event) {
        this.initEvent = event;
    }
}
