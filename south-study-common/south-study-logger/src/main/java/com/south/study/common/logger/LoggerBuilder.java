package com.south.study.common.logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.rolling.RollingFileAppender;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志构建者
 *
 * @author YueJiaJun
 * @version 0.0.1
 * @date 2022年03月09日 19:52 星期三
 * @since JDK_1.8.0.271
 */
public class LoggerBuilder {

    private static final Map<String, Logger> CONTAINER = new HashMap<String, Logger>(4);

    /**
     * 实例化
     */
    public static LoggerBuilder builder() {
        return new LoggerBuilder();
    }

    public Logger getLogger(String name) {
        Logger logger = CONTAINER.get(name);

        if (logger != null) {
            return logger;
        }

        synchronized (LoggerBuilder.class) {
            logger = CONTAINER.get(name);

            if (logger != null) {
                return logger;
            }

            logger = build(name);
            CONTAINER.put(name, logger);
        }
        return logger;
    }

    public Logger getLogger(Class clazz) {
        Logger logger = CONTAINER.get(clazz.getName());

        if (logger != null) {
            return logger;
        }

        synchronized (LoggerBuilder.class) {
            logger = CONTAINER.get(clazz.getName());

            if (logger != null) {
                return logger;
            }

            logger = build(clazz.getName());
            CONTAINER.put(clazz.getName(), logger);
        }
        return logger;
    }

    /**
     * 构建层级与记录方式
     *
     * @param name 名称
     * @return Logger
     */
    @SuppressWarnings({
            "rawtypes",
            "unchecked"
    })
    private static Logger build(String name) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = context.getLogger(LoggerProperties.LOGGER_PRE + name);

        RollingFileAppender errorAppender = new LoggerAppender().getAppender(name, Level.ERROR);
        logger.addAppender(errorAppender);

        RollingFileAppender infoAppender = new LoggerAppender().getAppender(name, Level.INFO);
        logger.addAppender(infoAppender);

        RollingFileAppender warnAppender = new LoggerAppender().getAppender(name, Level.WARN);
        logger.addAppender(warnAppender);

        RollingFileAppender debugAppender = new LoggerAppender().getAppender(name, Level.DEBUG);
        logger.addAppender(debugAppender);

        return logger;
    }
}
