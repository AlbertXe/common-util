package com.eventbus;


import java.util.List;
import java.util.function.Consumer;

public interface Executable extends Pipe,Identifier,Timed {
    <T extends Executable> T beforeConsumer(Consumer<BusinessContext> beforeConsumer);

    Throwable exceptionCaught();

    ExecuteAction execute(BusinessContext ctx);

    List<Executable> scheduledExecutables();

    <T extends Executable> T afterConsumer(Consumer<BusinessContext> afterConsumer);

}
