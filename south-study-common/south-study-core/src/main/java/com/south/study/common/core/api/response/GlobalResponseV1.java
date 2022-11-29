package com.south.study.common.core.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.south.study.common.core.constant.BasicResponseCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 全局响应体（原样）
 *
 * @author Juner
 * @version 0.0.1
 * @description
 * @date 2022年11月26日 23:54 星期六
 * @since JDK_1.8.0.271
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalResponseV1<T> {

    /**
     * 业务请求码（前端判断请求成功的状态码）
     */
    @ApiModelProperty(value = "请求状态码", example = "200")
    private Integer code;

    /**
     * 信息描述（用于异常提示）
     */
    @ApiModelProperty(value = "信息描述", example = "请求成功")
    private String message;

    /**
     * 总数（符合筛选条件的数据总数）
     */
    @ApiModelProperty(value = "总数", example = "88")
    private Long totalCount;

    /**
     * 总页数（符合筛选条件的数据总页数）
     */
    @ApiModelProperty(value = "总页数", example = "9")
    private Long totalPages;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", example = "1")
    private Long pageNumber;

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数", example = "10")
    private Long pageCount;

    /**
     * 请求数据
     */
    @ApiModelProperty(value = "数据", example = "{ \"name\": \"zhangsan\", \"info\": [ 1, 2] }")
    private T data;

    /**
     * 请求数据键
     */
    @ApiModelProperty(value = "数据", example = "{ \"keyList\": [ 1, 2] }")
    private List<String> keyList;

    /**
     * 当前时间
     */
    @ApiModelProperty(value = "本次查询时间", example = "2020-01-30 08:01:59.001")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date now = new Date();

    /**
     * 使用时间
     */
    @ApiModelProperty(value = "本次查询所用时间", example = "8ms")
    private String useTime;

    private static <T> GlobalResponseV1<T> builder() {
        return new GlobalResponseV1<>();
    }

    // ------------------------------- [ 请求成功（返回数据） ] -------------------------------

    public static <T> GlobalResponseV1 of(T data) {
        return new GlobalResponseV1<>(data);
    }

    public static <T> GlobalResponseV1 of(Pair pair) {
        return new GlobalResponseV1(pair.getData(), (Long) pair.getCount(), (Long) pair.getPages());
    }

    // ------------------------------- [ 更多类型请求 ] -------------------------------
    public static GlobalResponseV1 of(BasicResponseCode request) {
        return new GlobalResponseV1<>(request.key(), request.value());
    }

    public static GlobalResponseV1 of(BasicResponseCode request, String message) {
        return new GlobalResponseV1<>(request.key(), message);
    }


    // ------------------------------- [ 请求成功 ] -------------------------------

    public static GlobalResponseV1<Object> success() {
        return new GlobalResponseV1<>(200, "请求成功");
    }

    // ------------------------------- [ 系统错误 ] -------------------------------

    public static GlobalResponseV1<String> error() {
        return error("系统异常，请联系管理员");
    }
    public static GlobalResponseV1<String> error(String message) {
        return error(500, message);
    }

    public static GlobalResponseV1<String> error(Integer code, String message) {
        return new GlobalResponseV1<>(code, message);
    }


    // ------------------------------- [ 功能暂未开放 ] -------------------------------

    public static GlobalResponseV1<Object> disable() {
        return new GlobalResponseV1<>(401, "功能暂未开放");
    }

    // ------------------------------- [ 以下内容为各类构造方法 ] -------------------------------

    public GlobalResponseV1() {
    }

    /**
     * 状态、消息
     *
     * @param code    状态
     * @param message 消息
     */
    private GlobalResponseV1(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 数据
     *
     * @param data 数据
     */
    private GlobalResponseV1(T data) {
        this.ok();
        this.data = data;
    }

    /**
     * 数据、总数
     *
     * @param data  数据
     * @param count 总数据量
     * @param pages 总页数
     */
    private GlobalResponseV1(T data, Long count, Long pages) {
        this.ok();
        this.data = data;
        this.totalCount = count;
        this.totalPages = pages;
    }

    /**
     * 状态、消息、数据、总数、
     *
     * @param code    状态
     * @param message 消息
     * @param data    数据
     * @param count   总数据量
     * @param pages   总页数
     */
    private GlobalResponseV1(Integer code, String message, T data, Long count, Long pages) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.totalCount = count;
        this.totalPages = pages;
    }

    /**
     * 状态、消息、数据、数据量、耗时
     *
     * @param code    状态
     * @param message 消息
     * @param data    数据
     * @param count   总数据量
     * @param pages   总页数
     * @param time    耗时
     */
    private GlobalResponseV1(Integer code, String message, T data, Long count, Long pages, String time) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.totalCount = count;
        this.totalPages = pages;
        this.useTime = time;
    }

    private void ok() {
        this.code = 200;
        this.message = "请求成功";
    }

}
