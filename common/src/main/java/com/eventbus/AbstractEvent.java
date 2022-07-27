package com.eventbus;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 18:59
 */
public abstract class AbstractEvent extends AbstractExecutable implements Event{
    private final List<ActivityTbl> activityTbls = new ArrayList<>();
    private final List<Activity> activities = new ArrayList<>();
    protected boolean isSkipChangeFee;
    protected EventTbl eventTbl;
    protected String eventNo;

    @Override
    protected ExecuteAction doExecute(BusinessContext ctx) {
        extractActivities(ctx);
        ExecuteAction action = doExecuteEvent(ctx);
        return action;
    }

    protected ExecuteAction doExecuteEvent(BusinessContext ctx) {
        if (CollectionUtils.isEmpty(activities)) {
            return ExecuteAction.CONTINUE;
        }
        ExecuteAction action = ExecuteAction.CONTINUE;
        Map<String, Object> input = this.input;
        Map<String, Object> output = null;
        ctx.setCurrEvent(this);
        ctx.setCurrEventId(eventNo);

        Activity lastActivity = null;
        int i=0;
        for (Activity activity : activities) {
            try {
                i++;
                lastActivity = activity;
                addScheduledExecutable(activity);
                DataMap input1 = lastActivity.input(ctx);
                //每个activity input1是空对象
                input1.putAll(input);
                //input其实是上一个activity的output
                ctx.setCurrActivityId(lastActivity.activityNo());
                ctx.setCurrActivity(lastActivity);
                action = lastActivity.execute(ctx);
                if (!action.failed()) {
                    output = lastActivity.output(ctx);
                    input = output;
                    ctx.putAll(output);
                }
                if (action != ExecuteAction.CONTINUE) {
                    break;
                }
            } catch (Exception e) {
                action = ExecuteAction.failed(this, lastActivity, e);
                break;
            }
        }
        if (!action.failed()) {
            if (output != null) {
                this.output.putAll(output);
            }
            if (action == ExecuteAction.BREAK_EVENT) {
                return ExecuteAction.CONTINUE;
            }
            return action;
        }

        ExecuteAction failedAction = action;
        int last = i>=activities.size()?i-1:i;
        for (int j = last; j >=0 ; j--) {
            try {
                lastActivity = activities.get(j);
                if (lastActivity instanceof Catcher) {
                    ((Catcher) lastActivity).catchException(ctx, failedAction);
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return action;
    }

    private void extractActivities(BusinessContext ctx) {
        //按照事件号查询事件活动映射表
        List<EventActyMpngTbl> mapTbl = new ArrayList<>();
        mapTbl.sort(Comparator.comparingInt(EventActyMpngTbl::getActyExecSeqNo));
        mapTbl.stream()
                .map(e -> fromEventActivityMap(this, e))
                .forEach(activities::add);
    }

    private static Activity fromEventActivityMap(Event event, EventActyMpngTbl eventActyMpngTbl) {
        AbstractActivity activity = SpringUtils.getBean(eventActyMpngTbl.getAcTyId(), AbstractActivity.class);
        activity.setInitEvent(event);
        return activity;
    }

    public void initEvent(EventTbl eventTbl) {
        this.eventTbl = eventTbl;
        this.eventNo = eventTbl.getEventId();
        this.name = eventNo;
        loadFeeItem();

    }

    /**
     * 加载收费项目
     */
    protected void loadFeeItem(){

    }


    @Override
    public String eventNo() {
        return eventNo;
    }

    @Override
    public EventTbl event() {
        return eventTbl;
    }

    @Override
    public List<ActivityTbl> activities() {
        return activityTbls;
    }

    @Override
    public String desc() {
        return "Event"+name;
    }


}
