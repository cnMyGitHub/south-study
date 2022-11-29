package com.south.study.common.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * 1. 序列化
 * 2. 创建时间、更新时间、及其格式
 * 3. 创建人、更新人
 * 4. 数据主键 ID
 * 5. 必须有实体继承，禁止单独使用
 *
 * @author YueJiaJun
 * @version 0.0.1
 * @date 2022年03月11日 18:11 星期五
 * @since JDK_1.8.0.271
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity<ID> implements Serializable {

    @ApiModelProperty(value="主键 ID")
    private ID id;

    @ApiModelProperty(value="创建者")
    private String createBy;

    @JsonFormat(
            pattern = UnifiedTime._DEFAULT,
            timezone = UnifiedTime.DEFAULT_TIMEZONE
    )
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="更新者")
    private String updateBy;

    @ApiModelProperty(value="更新时间")
    @JsonFormat(
            pattern = UnifiedTime._DEFAULT,
            timezone = UnifiedTime.DEFAULT_TIMEZONE
    )
    private Date updateTime;

    @ApiModelProperty(value="是否已删除；0正常，1删除")
    private Byte isDelete;

}
