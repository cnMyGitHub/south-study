package com.south.study.common.logger;

import java.nio.charset.Charset;

/**
 * 日志配置属性
 *
 * @author Juner
 * @version 0.0.1
 * @description
 * @date 2022年11月26日 18:05 星期六
 * @since JDK_1.8.0.271
 */
public interface LoggerProperties {

    /**
     * 日志构建-前缀
     */
    String LOGGER_PRE = "LoggerBuilder-";

    /**
     * 日志构建-前缀
     */
    String APPENDER_PRE = "Appender-";

    /**
     * 日志构建-编码方式
     */
    Charset UTF8 = Charset.forName("UTF-8");

    /**
     * 日志构建-输出格式
     */
    String PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n";

    /**
     * 日志构建-最大历史记录
     */
    Integer MAXIMUM_HISTORY = 2 << 3;

    /**
     * 日志构建-总记录大小
     */
    String FILE_TOTAL_SIZE = MAXIMUM_HISTORY + "GB";

    /**
     * 日志构建-日志文件大小
     */
    String FILE_SIZE = "100MB";

    /**
     * 日志构建-日志路径
     */
    String LOG_PATH = "./logs";

    String LOG_FILE_NAME = "%d{yyyy-MM-dd}.%i.log";

}
