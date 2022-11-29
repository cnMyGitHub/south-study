package com.south.study.common.logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import static ch.qos.logback.core.spi.FilterReply.ACCEPT;
import static ch.qos.logback.core.spi.FilterReply.DENY;

/**
 * 日誌追加者
 *
 * @author YueJiaJun
 * @version 0.0.1
 * @date 2022年03月09日 19:56 星期三
 * @since JDK_1.8.0.271
 */
public class LoggerAppender {

    public String getFilePath(String name, Level level) {
        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
        return LoggerProperties.LOG_PATH + "/" + name + "/" + format.format(new Date()) + "/" + level.levelStr;
    }

    /**
     * 滚动追加
     * <p>
     * 通过传入日志记录和日志等级，动态设置 appender
     * </p>
     * <pre>
     *     这里是可以用来设置appender的，在xml配置文件里面，是这种形式：
     *      &lt;appender name="error"
     *               class="ch.qos.logback.core.rolling.RollingFileAppender"&gt;
     * </pre>
     *
     * @param name  名字
     * @param level 等级
     * @return RollingFileAppender
     */
    @SuppressWarnings({
            "rawtypes",
            "unchecked"
    })
    public RollingFileAppender getAppender(String name, Level level) {
        String filePath = getFilePath(name, level);

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        RollingFileAppender appender = new RollingFileAppender();

        LevelFilter levelFilter = getLevelFilter(level);
        levelFilter.start();
        appender.addFilter(levelFilter);

        appender.setContext(context);
        appender.setName(LoggerProperties.APPENDER_PRE + name);
        appender.setFile(OptionHelper.substVars(filePath + ".log", context));
        appender.setAppend(true);
        appender.setPrudent(false);

        appender.setRollingPolicy(loggerRollingPolicy(filePath, context, appender));
        appender.setEncoder(patternLayoutBuilder(context));
        appender.start();
        return appender;
    }

    /**
     * 记录器滚动政策
     */
    @SuppressWarnings({
            "rawtypes"
    })
    public SizeAndTimeBasedRollingPolicy loggerRollingPolicy(String filePath, LoggerContext context, RollingFileAppender appender) {
        SizeAndTimeBasedRollingPolicy policy = new SizeAndTimeBasedRollingPolicy();

        String fileName = OptionHelper.substVars(filePath + "/" + LoggerProperties.LOG_FILE_NAME, context);
        policy.setMaxFileSize(FileSize.valueOf(LoggerProperties.FILE_SIZE));
        policy.setFileNamePattern(fileName);
        policy.setMaxHistory(LoggerProperties.MAXIMUM_HISTORY);
        policy.setTotalSizeCap(FileSize.valueOf(LoggerProperties.FILE_TOTAL_SIZE));

        policy.setParent(appender);
        policy.setContext(context);
        policy.start();
        return policy;
    }

    /**
     * 布局构造
     * <p> 设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。 </p>
     * <p> 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。 </p>
     *
     * @param context 上下文
     * @return PatternLayoutEncoder
     */
    public PatternLayoutEncoder patternLayoutBuilder(LoggerContext context) {
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern(LoggerProperties.PATTERN);
        encoder.setCharset(LoggerProperties.UTF8);
        encoder.start();
        return encoder;
    }

    /**
     * <strong>获取级别过滤器</strong>
     *
     * @param level 等级
     * @return LevelFilter
     */
    private LevelFilter getLevelFilter(Level level) {
        LevelFilter levelFilter = new LevelFilter();
        levelFilter.setLevel(level);
        levelFilter.setOnMatch(ACCEPT);
        levelFilter.setOnMismatch(DENY);
        return levelFilter;
    }

}
