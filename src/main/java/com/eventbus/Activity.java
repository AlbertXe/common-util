package com.eventbus;

public interface Activity extends Executable {
    String activityNo();

    ActivityTbl activity();

    ExecuteAction triggerEvents(BusinessContext ctx);

}
