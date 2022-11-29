package com.south.study.common.core.domain;

import com.south.study.common.core.api.request.GlobalRequest;
import com.south.study.common.core.api.response.GlobalResponseV1;

import java.util.List;

/**
 * 通用 CURD 接口
 *
 * get: 获取单个
 * list: 获取列表
 * count: 计数
 * remove: 移除 | 删除
 * insert: 新增
 * save: 保存
 * update: 更新
 *
 * @author YueJiaJun
 * @version 0.0.1
 * @date 2022年03月12日 22:15 星期六
 * @since JDK_1.8.0.271
 */
public interface AbstractCURD<T> {

    /**
     * 新增 或 保存
     */
    int save(T record);

    /**
     * 删除操作
     */
    int remove(T record);

    /**
     * 批量删除操作
     */
    int remove(List<T> records);

    /**
     * 根据ID查询
     */
    T getById(Long id);

    /**
     * 分页查询
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     *
     * @param pageRequest 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
    @SuppressWarnings("all")
    GlobalResponseV1 findPage(GlobalRequest pageRequest);
}
