package com.jaspercloud.mybaits.mapper.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RouteDataSource extends AbstractRoutingDataSource {

    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void setKey(String key) {
        threadLocal.set(key);
    }

    public static void remove() {
        threadLocal.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return threadLocal.get();
    }
}
