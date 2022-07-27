package com.eventbus;

import java.util.HashMap;
import java.util.Map;

/**
 * 交易级缓存
 *
 * @description:
 * @author: AlbertXe
 * @create: 2022-07-04 12:03
 */
public class TransactionCache {

    private static ThreadLocal<Map<Object, Map<Object, Object>>> cache = new ThreadLocal<Map<Object, Map<Object, Object>>>();


    public static Map<Object, Object> getCache(Object cacheType) {
        if (cache.get() == null) {
            cache.set(new HashMap<>());
        }
        Map<Object, Object> map = cache.get().get(cacheType);
        if (map == null) {
            cache.get().put(cacheType, map = new HashMap<>());
        }
        return map;
    }


    public static void clear() {
        Map<Object, Map<Object, Object>> map = cache.get();
        if (map != null) {
            map.clear();
        }
    }
}
