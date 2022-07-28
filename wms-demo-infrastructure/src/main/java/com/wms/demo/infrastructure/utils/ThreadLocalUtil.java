package com.wms.demo.infrastructure.utils;

public class ThreadLocalUtil {

    private static ThreadLocal<String> threadLocalTraceId = new ThreadLocal<>();

    public static String getTraceId() {
        return threadLocalTraceId.get();
    }

    public static void setTraceId(String traceId) {
        threadLocalTraceId.set(traceId);
    }
}
