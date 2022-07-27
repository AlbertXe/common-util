package com.eventbus;

/**
 *
 * @param <I> input
 * @param <O> output
 * @param <Q> input转化为request
 * @param <P> output转化response
 */
public interface Executor<I,O,Q,P> extends Timed {
    void attachContext(BusinessContext ctx);

    BusinessContext context();

    I input();

    O output();

    Q request();

    P response();

    Event event();

    O execute(I input);
}
