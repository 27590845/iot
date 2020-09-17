package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.NodeAttr;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 节点属性
 * @author: Hansey
 * @date: 2020-09-15 09:40
 */
@Data
public class NodeAttrParam {

    @ApiModelProperty(value = "属性标识")
    @NotBlank(message = "属性标识不能为为空")
    private String naKey;

    @ApiModelProperty(value = "属性名称")
    @NotBlank(message = "属性名称不能为为空")
    private String naName;

    @ApiModelProperty(value = "属性单位")
    @NotBlank(message = "属性单位不能为为空")
    private String naUnit;

    @ApiModelProperty(value = "属性符号")
    @NotBlank(message = "属性符号不能为为空")
    private String naSym;

    public NodeAttr build(Long naId, Long nodeId) {
        NodeAttr nodeAttr = new NodeAttr();
        nodeAttr.setNaId(naId);
        nodeAttr.setNodeId(nodeId);
        nodeAttr.setNaKey(naKey);
        nodeAttr.setNaName(naName);
        nodeAttr.setNaSym(naSym);
        nodeAttr.setNaUnit(naUnit);
        return nodeAttr;
    }
}
