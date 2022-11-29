package com.south.study.common.core.readme;

import com.south.study.common.core.annotation.Chinese;

/**
 * 请求方式
 *
 * @author YueJiaJun
 * @version 0.0.1
 * @date 2022年03月10日 20:36 星期四
 * @since JDK_1.8.0.271
 */
public class ParameterConstant {

    @Chinese({
            "获取 URL 中的数据",
            "/request/{data}",
            "/request/123"
    })
    public static final String PARAM_METHOD_1 = "PathVariable";

    @Chinese({
            "获取请求参数的值",
            "/request",
            "/request?data=requestData"
    })
    public static final String PARAM_METHOD_2 = "RequestParam";

}
