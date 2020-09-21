package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.NodeAttr;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

/**
 * 节点属性更新参数，虽然和NodeAttrParam参数相同但是不可以用Validated分组
 * 因为NodeAttrParam支持嵌套查询
 * @author: Hansey
 * @date: 2020-09-16 17:18
 */
@Data
public class NodeAttrUpdateParam {

    @ApiModelProperty(value = "属性标识")
    private String naKey;

    @ApiModelProperty(value = "属性名称")
    private String naName;

    @ApiModelProperty(value = "属性单位")
    private String naUnit;

    @ApiModelProperty(value = "属性符号")
    private String naSym;

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
