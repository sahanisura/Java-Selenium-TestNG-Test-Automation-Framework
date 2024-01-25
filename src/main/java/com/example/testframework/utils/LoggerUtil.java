package com.example.testframework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class LoggerUtil {
    private static final ConcurrentHashMap<Long, Logger> infoLoggerMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Long, Logger> debugLoggerMap = new ConcurrentHashMap<>();

    private LoggerUtil() {
    }

    public static Logger getInfoLogger() {
        long threadId = Thread.currentThread().threadId();
        if (infoLoggerMap.get(threadId) == null) {
            Logger logger = LoggerFactory.getLogger("InfoData");
            infoLoggerMap.put(threadId, logger);
        }
        return infoLoggerMap.get(threadId);
    }

    public static Logger getDebugLogger() {
        long threadId = Thread.currentThread().threadId();
        if (debugLoggerMap.get(threadId) == null) {
            Logger logger = LoggerFactory.getLogger("DebugData");
            debugLoggerMap.put(threadId, logger);
        }
        return debugLoggerMap.get(threadId);
    }
}
