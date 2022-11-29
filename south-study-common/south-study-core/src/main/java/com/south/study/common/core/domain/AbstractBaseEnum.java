package com.south.study.common.core.domain;

/**
 * 通用枚举
 *
 * @author YueJiaJun
 * @version 0.0.1
 * @date 2022年03月09日 17:20 星期三
 * @since JDK_1.8.0.271
 */
public interface AbstractBaseEnum<K, V> {

    /**
     * 获取代码
     *
     * @return String
     */
    K key();

    /**
     * 获取内容
     *
     * @return String
     */
    V value();

}
