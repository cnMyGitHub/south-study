package com.south.study.common.core.exception;

import com.south.study.common.core.api.response.GlobalResponseV1;
import com.south.study.common.core.constant.BasicResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 全局异常处理器
 *
 * @author Juner
 * @version 0.0.1
 * @description
 * @date 2022年11月26日 23:54 星期六
 * @since JDK_1.8.0.271
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    // TODO logger & StringUtils

    /**
     * 处理其他异常
     *
     * @param e 异常
     * @return 系统内部错误
     */
    @ExceptionHandler(Exception.class)
    public GlobalResponseV1<String> globalHandler(Exception e) {
        logger.error("01 Exception, exception:{}", e, e);
        return GlobalResponseV1.error();
    }

    /**
     * <strong>捕获运行异常</strong>
     * <p>使用统一返回样式返回</p>
     *
     * @param e 运行时异常
     * @return ResponseEntity
     */
    @ExceptionHandler(RuntimeException.class)
    public GlobalResponseV1<String> runtimeExceptionHandler(RuntimeException e) {
        logger.error("02 Exception, exception:{}", e, e);
        return GlobalResponseV1.error();
    }

    /**
     * 不支持的非法请求
     *
     * @param e 不支持请求异常
     * @return 非法请求返回
     */
    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public GlobalResponseV1<String> notSupportHandler(org.springframework.web.HttpRequestMethodNotSupportedException e) {
        logger.error("03 Exception, exception:{}", e, e);
        String message = "不支持 " + e.getMethod() + " 请求，支持 " + StringUtils.strip(Arrays.toString(Objects.requireNonNull(e.getSupportedMethods())), "[]") + " 请求。";
        @SuppressWarnings({"unchecked", "raw"})
        GlobalResponseV1<String> result = GlobalResponseV1.of(BasicResponseCode.REQUEST_METHOD_DISABLE, message);
        return result;
    }

    /**
     * 当上传文件超出 max-swallow-size 大小，则无法捕获该异常。
     * <p>
     * e.g:
     * <pre>
     *     <code>
     *
     *         # 配置文件最大上传限制
     *         spring.servlet.multipart.max-file-size = 50MB
     *
     *         # 配置最大请求大小
     *         spring.servlet.multipart.max-request-size = 50MB
     *
     *         # 最大吞吐量：100MB，超出后无法捕获该异常
     *         server.tomcat.max-swallow-size = 100MB
     *
     *     </code>
     * </pre>
     *
     * @param e MaxUploadSizeExceededException
     * @return 统一响应体
     */
    @ExceptionHandler(org.springframework.web.multipart.MaxUploadSizeExceededException.class)
    public GlobalResponseV1<String> maxUploadSizeExceededException(org.springframework.web.multipart.MaxUploadSizeExceededException e) {
        double max = 0D;
        if (!StringUtils.isBlank(e.getMessage())) {
            List<String> messageList = String_Utils.findAll("\\d+", e.getMessage());
            max = Double.parseDouble(messageList.get(messageList.size() - 1));
        }
        @SuppressWarnings({"unchecked", "raw"})
        GlobalResponseV1<String> result = GlobalResponseV1.of(BasicResponseCode.FILE_SIZE_ERROR, "资源上传不能超过 " + String_Utils.convertSize(max));
        return result;
    }

    // TODO 类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public GlobalResponseV1<String> classCastHandler(ClassCastException e) {
        logger.error("05 Exception, exception:{}", e, e);
        return GlobalResponseV1.error();
    }

    // TODO Bean 绑定 | 处理校验方法异常
    @ExceptionHandler(org.springframework.validation.BindException.class)
    public GlobalResponseV1<String> bindHandler(org.springframework.validation.BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null;
        @SuppressWarnings({"unchecked", "raw"})
        GlobalResponseV1<String> result = GlobalResponseV1.of(BasicResponseCode.SPRING_BIND_EXCEPTION, fieldError.getDefaultMessage());
        return result;
    }

    /**
     * 处理远程调用方法异常
     *
     * @param e MethodArgumentNotValidException
     * @return 处理远程调用方法异常
     */
    // TODO 处理远程调用方法异常
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public GlobalResponseV1<String> argumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null;
        @SuppressWarnings({"unchecked", "raw"})
        GlobalResponseV1<String> result = GlobalResponseV1.of(BasicResponseCode.REMOTE_CALL_EXCEPTION, fieldError.getDefaultMessage());
        return result;
    }

    /**
     * 媒体类型不支持异常
     *
     * @param e HttpMediaTypeNotSupportedException
     * @return 统一响应体
     */
    // TODO 媒体类型不支持异常
    @ExceptionHandler(org.springframework.web.HttpMediaTypeNotSupportedException.class)
    public GlobalResponseV1<String> argumentNotValidException(org.springframework.web.HttpMediaTypeNotSupportedException e) {
        logger.error("请求类型 {} 不受支持", String_Utils.findAll("['0-9a-zA-Z\\-\\/\\;\\=]+", e.getMessage()).get(2));
        @SuppressWarnings({"unchecked", "raw"})
        GlobalResponseV1<String> result = GlobalResponseV1.of(BasicResponseCode.HTTP_MEDIA_TYPE_NOT_SUPPORTED);
        return result;
    }

}
