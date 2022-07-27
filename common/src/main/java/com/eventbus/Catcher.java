package com.eventbus;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 20:20
 */
public interface Catcher {
    static String wrapErrorMessage(Throwable t, Executable executable) {
        StringBuilder sb = ThreadLocalBuilder.builder();
        sb.append(t);
        sb.append(executable);
        return sb.toString();
    }

    ExecuteAction catchException(BusinessContext ctx, ExecuteAction failedAction);
}
