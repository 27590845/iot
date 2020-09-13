package com.xidian.iot.database.param;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 节点属性模版
 * @author: Hansey
 * @date: 2020-09-13 12:54
 */
public class NodeAttrStdParam {

    @ApiModelProperty(value = "节点属性的归一化表示、例如tem1、tem2、等")
    @NotBlank(message = "节点属性模版名称不能为为空")
    private String nasKey;

    /**
     * 对主属性的描述
     */
    @ApiModelProperty(value = "主属性的归一化表示、例如tem1、tem2、等")
    @NotBlank(message = "节点属性的模版描述不能为为空")
    private String nasDesc;

    /**
     * 主属性单位
     */
    @ApiModelProperty(value = "节点属性的模版单位")
    @NotBlank(message = "节点属性的模版单位")
    private String nasUnit;

    /**
     * 主属性符号
     */
    @ApiModelProperty(value = "节点属性的模版单位")
    @NotBlank(message = "节点属性模版符号")
    private String nasSym;
}
