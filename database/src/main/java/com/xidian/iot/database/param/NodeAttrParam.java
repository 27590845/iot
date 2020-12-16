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

    @ApiModelProperty(value = "属性值格式化公式")
    private String naForm;

    @ApiModelProperty(value = "属性映射的传感器偏移地址")
    private String naMap;

    public NodeAttr build(Long naId, String sceneSn, String nodeSn, Long nodeId) {
        NodeAttr nodeAttr = new NodeAttr();
        nodeAttr.setNodeId(nodeId);
        nodeAttr.setNaId(naId);
        nodeAttr.setNaKey(naKey);
        nodeAttr.setNaName(naName);
        nodeAttr.setNaSym(naSym);
        nodeAttr.setNaUnit(naUnit);
        nodeAttr.setSceneSn(sceneSn);
        nodeAttr.setNodeSn(nodeSn);
        nodeAttr.setNaForm(StringUtils.isNotBlank(naForm)?naForm:null);
        nodeAttr.setNaMap(StringUtils.isNotBlank(naMap)?naMap:null);
        return nodeAttr;
    }

    /**
     * 由 NodeAttrUpdateParam转化为NodeAttr，同时过滤空字符
     *
     * @param naId
     * @return com.xidian.iot.database.entity.NodeAttr
     */
    public NodeAttr build(Long naId) {
        NodeAttr nodeAttr = new NodeAttr();
        nodeAttr.setNaId(naId);
        nodeAttr.setNaKey(StringUtils.isNotBlank(naKey) ? naKey : null);
        nodeAttr.setNaName(StringUtils.isNotBlank(naName) ? naName : null);
        nodeAttr.setNaSym(StringUtils.isNotBlank(naName) ? naName : null);
        nodeAttr.setNaUnit(naUnit);
        nodeAttr.setNaForm(StringUtils.isNotBlank(naForm)?naForm:null);
        nodeAttr.setNaMap(StringUtils.isNotBlank(naMap)?naMap:null);
        return nodeAttr;
    }
}
