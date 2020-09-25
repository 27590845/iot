package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.NodeCmd;
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

    /**
     * 从节点命令传输封装类转化为节点命令
     * @param ncId
     * @param nodeId
     * @param sceneSn
     * @param nodeSn
     * @return com.xidian.iot.database.entity.NodeCmd
     * */
    public NodeCmd buildNodeCmd(Long ncId, Long nodeId, String sceneSn, String nodeSn) {
        NodeCmd nodeCmd = new NodeCmd();
        nodeCmd.setNcId(ncId);
        nodeCmd.setNodeId(nodeId);
        nodeCmd.setNodeSn(sceneSn);
        nodeCmd.setSceneSn(nodeSn);
        nodeCmd.setNcContent(ncContent);
        nodeCmd.setNcName(ncName);
        return nodeCmd;
    }

}
