package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeCmd;
import com.xidian.iot.database.param.NodeAttrParam;
import com.xidian.iot.database.param.NodeCmdParam;
import com.xidian.iot.database.param.ValidList;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeCmdService
 * @Package
 * @Description: NodeCmd相关服务
 * @date 2020/9/11 8:30 下午
 */
public interface NodeCmdService {

    /**
     * 根据nodeCmd.ncId获取NodeCmd
     *
     * @param ncId
     * @return
     */
    NodeCmd getNodeCmdById(Long ncId);

    /**
     * 批量添加NodeCmd, 应保证所有字段非空
     *
     * @param sceneSn       网关sn
     * @param nodeSn        节点sn
     * @param nodeId        节点Id
     * @param nodeCmdParams 节点命令参数
     * @return int
     */
    int addNodeCmds(String sceneSn, String nodeSn, Long nodeId, List<NodeCmdParam> nodeCmdParams);


    /**
     * 验证添加的nodeCmdParams
     * 是否存在重复命令内容/重复命令名称
     *
     * @return void
     */
    void checkReptCmds(List<NodeCmdParam> nodeCmdParams);

    /**
     * 根据节点Id获取节点下的所有节点命令
     *
     * @param nodeId
     * @return java.util.List<com.xidian.iot.database.entity.NodeCmd>
     */
    public List<NodeCmd> getNodeCmdsByNodeId(Long nodeId);

    /**
     * 根据节点命令主键删除
     *
     * @param ncId 节点命令主键
     * @return void
     */
    void deleteByNcId(Long ncId);

    /**
     * 删除节点下的
     *
     * @param nodeId
     * @return void
     */
    void deleteByNodeId(Long nodeId);

    /**
     * 根据节点命令Id主键、更新节点命令
     *
     * @param ncId         节点命令Id
     * @param nodeCmdParam 节点命令
     * @return void
     */
    void updateByNcId(Long ncId, NodeCmdParam nodeCmdParam);

    /**
     * 验证该节点是否已经存在该节点命令名称/内容
     * @param nodeId
     * @param nodeCmdParams
     * @return void
     * */
    void checkExistCmds(Long nodeId, ValidList<NodeCmdParam> nodeCmdParams);
}
