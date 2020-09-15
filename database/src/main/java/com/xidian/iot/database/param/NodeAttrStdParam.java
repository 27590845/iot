package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.NodeAttrStd;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 节点属性模版
 * @author: Hansey
 * @date: 2020-09-13 12:54
 */
@Data
public class NodeAttrStdParam {

    @ApiModelProperty(value = "节点属性的归一化表示、例如tem1、tem2、等")
    @NotBlank(message = "节点属性模版名称不能为为空")
    private String nasKey;

    @ApiModelProperty(value = "主属性的描述")
    @NotBlank(message = "节点属性的模版描述不能为为空")
    private String nasDesc;

    @ApiModelProperty(value = "节点属性的模版单位")
    @NotBlank(message = "节点属性的模版单位")
    private String nasUnit;

    @ApiModelProperty(value = "节点属性的模版单位")
    @NotBlank(message = "节点属性模版符号")
    private String nasSym;

    public NodeAttrStd buildNodeAttrStd(Long nasId){
        NodeAttrStd nodeAttrStd = new NodeAttrStd();
        nodeAttrStd.setNasId(nasId);
        nodeAttrStd.setNasDesc(nasDesc);
        nodeAttrStd.setNasKey(nasKey);
        nodeAttrStd.setNasUnit(nasUnit);
        nodeAttrStd.setNasSym(nasSym);
        return nodeAttrStd;
    }
}
