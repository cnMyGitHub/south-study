package com.south.study.common.core.api.request;

import com.south.study.common.core.api.request.model.Param;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局请求体
 *
 * @author Juner
 * @version 0.0.1
 * @description
 * @date 2022年11月27日 9:38 星期日
 * @since JDK_1.8.0.271
 */
@Setter
@Getter
public class GlobalRequest implements Serializable {

    @ApiModelProperty(value="当前页码",name="pageNum",example="1")
    private int pageNum = 1;


    @ApiModelProperty(value="每页数量",name="pageSize",example="10")
    private int pageSize = 10;

    @ApiModelProperty(value="查询参数",name="params",example="")
    private List<Param> params = new ArrayList<Param>();

    /**
     * 查询参数对象
     *
     * @param name 参数名称
     */
    public Param getParam(String name) {
        for (Param param : this.params) {
            if (name != null && name.equals(param.getName())) {
                return param;
            }
        }
        return null;
    }

    /**
     * 查询参数值
     *
     * @param name 参数名称
     */
    public String getParamValue(String name) {
        Param param = getParam(name);
        if (param != null) {
            return param.getValue();
        }
        return null;
    }

}
