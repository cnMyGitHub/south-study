package com.south.study.common.core.domain;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 通用分页
 *
 * 通常用于返回时的统一响应体中
 *
 * @author YueJiaJun
 * @version 0.0.1
 * @date 2022年03月11日 18:27 星期五
 * @since JDK_1.8.0.271
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractPageEntity<ID>
        extends AbstractEntity<ID> {

    /**
     * 当前页码
     */
    @ApiModelProperty(value="当前页码")
    private Integer pageNum;

    /**
     * 每页数量
     */
    @ApiModelProperty(value="每页数量")
    private Integer pageSize;

    /**
     * @return 如果需要分页，page 则不为空，进行分页计算
     */
    public Integer getPage() {
        if (ObjectUtil.isNotNull(this.pageNum)) {
            return (this.pageNum - 1) * this.pageSize;
        }
        return null;
    }

    /**
     * @return Limit 为需要取出的数据量
     */
    public Integer getPageSize() {
        return this.pageSize;
    }

}
