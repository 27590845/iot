package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.valid.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

/**
 * 节点属性
 *
 * @author: Hansey
 * @date: 2020-09-15 09:40
 */
@Data
public class NodeAttrParam {

    @ApiModelProperty(value = "属性标识")
    @NotBlank(groups = {ValidGroup.INSERT.class}, message = "属性标识不能为为空")
    private String naKey;

    @ApiModelProperty(value = "属性名称")
    @NotBlank(groups = {ValidGroup.INSERT.class}, message = "属性名称不能为为空")
    private String naName;

    @ApiModelProperty(value = "属性单位")
    @NotBlank(groups = {ValidGroup.INSERT.class}, message = "属性单位不能为为空")
    private String naUnit;

    @ApiModelProperty(value = "属性符号")
    @NotBlank(groups = {ValidGroup.INSERT.class}, message = "属性符号不能为为空")
    private String naSym;

    public NodeAttr build(String sceneSn, String nodeSn, Long naId, Long nodeId) {
        NodeAttr nodeAttr = new NodeAttr();
        nodeAttr.setNaId(naId);
        nodeAttr.setNodeId(nodeId);
        nodeAttr.setNaKey(naKey);
        nodeAttr.setNaName(naName);
        nodeAttr.setNaSym(naSym);
        nodeAttr.setNaUnit(naUnit);
        nodeAttr.setSceneSn(sceneSn);
        nodeAttr.setNodeSn(nodeSn);
        return nodeAttr;
    }

    /**
     * 由 NodeAttrUpdateParam转化为NodeAttr，同时过滤空字符
     * @param naId
     * @return com.xidian.iot.database.entity.NodeAttr
     * */
    public NodeAttr build(Long naId) {
        NodeAttr nodeAttr = new NodeAttr();
        nodeAttr.setNaId(naId);
        nodeAttr.setNaKey(StringUtils.isNotBlank(naKey)?naKey:null);
        nodeAttr.setNaName(StringUtils.isNotBlank(naName)?naName:null);
        nodeAttr.setNaSym(StringUtils.isNotBlank(naName)?naName:null);
        nodeAttr.setNaUnit(naUnit);
        return nodeAttr;
    }
}
