package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeCmd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author: Hansey
 * @date: 2020-09-11 09:31
 */
@Data
@ApiModel(description = "节点添加信息")
public class NodeAddParam {

    @ApiModelProperty(value = "网关SN")
    @Size(max = 18,min = 18,message = "非法网关")
    private String sceneSn;

    @ApiModelProperty(value = "节点名称")
    @NotBlank(message = "节点名称不能为空")
    private String nodeName;

    @ApiModelProperty(value = "描述")
    private String nodeDesc;

    @Valid
    @ApiModelProperty(value = "节点属性")
    private List<NodeAttrParam> nodeAttrParams;

    @Valid
    @ApiModelProperty(value = "节点命令")
    private List<NodeCmdParam> nodeCmdParams;

    @ApiModelProperty(value = "节点SN")
    private String nodeSn;

    @ApiModelProperty(value = "JSON格式存放节点和属性各值上载名称")
    private String nodeAttrname;
    /**
     * 由 NodeAddParam转化为Node 同时添加从数据库取出的sceneId
     * @param sceneId
     * @return com.xidian.iot.database.entity.Node
     * */
    public Node buildNode(Long sceneId){
        Node node = new Node();
        node.setSceneId(sceneId);
        node.setSceneSn(sceneSn);
        node.setNodeName(nodeName);
        node.setNodeSn(nodeSn);
        node.setNodeDesc(StringUtils.isNotBlank(nodeDesc)?nodeDesc:null);
        node.setCreateTime(new Date());
        node.setNodeAttrname(StringUtils.isNotBlank(nodeAttrname)?nodeAttrname:null);
        return node;
    }
}
