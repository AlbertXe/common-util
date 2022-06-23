package com.util.springfactory;

import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiehongwei
 * @date 2022/6/23 1:51 下午
 */
public class FactoriesLoader {
    private static final Map<Class<?>, Map<String, Object>> cache = new ConcurrentHashMap<>();

    public static <T> T getDefaultFactory(Class<T> factoryClass) {
        SPI spi = factoryClass.getAnnotation(SPI.class);
        if (spi == null) {
            return null;
        }
        String defaultId = spi.defaultId();
        Map<String,T> instance = getFactories(factoryClass);
        return instance.get(defaultId);
    }

    public static <T> T getNewestFactory(Class<T> factoryClass) {
        Map<String, T> factories = getFactories(factoryClass);
        return factories.isEmpty() ? null : (T) factories.values().toArray()[factories.size() - 1];
    }

    private static <T> Map<String,T> getFactories(Class<T> factoryClass) {
        Map<String, T> instance = (Map<String, T>) cache.get(factoryClass);
        if (instance != null) {
            return instance;
        }
        loadFacories(factoryClass);
        return (Map<String, T>) cache.get(factoryClass);
    }

    private static void loadFacories(Class<?> factoryClass) {
        //通过order排序
        List<?> factories = SpringFactoriesLoader.loadFactories(factoryClass, null);
        if (CollectionUtils.isEmpty(factories)) {
            cache.put(factoryClass, Collections.EMPTY_MAP);
        }
        Map<String, Object> instances = new LinkedHashMap<>();
        for (Object factory : factories) {
            String spiName = getSpiName(factory.getClass());
            Object old = instances.get(spiName);
            if (old != null) {
                // 新的覆盖旧的
            }
            instances.put(spiName, factory);
        }
        //无则放入
        cache.putIfAbsent(factoryClass, instances);
    }

    private static String getSpiName(Class<?> clz) {
        SPIMeta spiMeta = clz.getAnnotation(SPIMeta.class);
        if (spiMeta == null) {
            return clz.getSimpleName();
        }
        return spiMeta.id();
    }




}
