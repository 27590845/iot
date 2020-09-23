package com.xidian.iot.database.param;

import com.xidian.iot.database.valid.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 节点命令传输封装类
 *
 * @author: Hansey
 * @date: 2020-09-22 21:24
 */
@Data
public class NodeCmdParam {

    @ApiModelProperty(value = "节点命令内容")
    @NotBlank(groups = {ValidGroup.INSERT.class}, message = "节点命令内容不能为空")
    private String ncContent;

    @ApiModelProperty(value = "命令标题")
    @NotBlank(groups = {ValidGroup.INSERT.class}, message = "命令标题不能为空")
    private String ncName;

}
