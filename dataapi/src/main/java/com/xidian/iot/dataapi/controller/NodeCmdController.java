package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.param.NodeCmdParam;
import com.xidian.iot.database.param.ValidList;
import com.xidian.iot.database.valid.ValidGroup;
import com.xidian.iot.databiz.service.NodeActCmdService;
import com.xidian.iot.databiz.service.NodeCmdService;
import com.xidian.iot.databiz.service.NodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 节点命令相关接口
 *
 * @author: Hansey
 * @date: 2020-09-22 20:11
 */
@Api(tags = "节点命令", description = "提供节点命令操作的相关接口")
@RestController
@RequestMapping("/nodeCmd")
public class NodeCmdController {
    @Autowired
    private NodeCmdService nodeCmdService;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private NodeActCmdService nodeActCmdService;

    @ApiOperation(value = "批量添加节点命令、保证添加的命令描述字段、命令内容不重复")
    @PostMapping("/{sceneSn}/{nodeSn}")
    public HttpResult addNodeCmds(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                  @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable("nodeSn") String nodeSn,
                                  @ApiParam(name = "nodeCmdParams", value = "节点属命令列表") @Validated(ValidGroup.INSERT.class) @RequestBody() ValidList<NodeCmdParam> nodeCmdParams) {
        //验证是否存在重复的节点命令名称/内容
        nodeCmdService.checkReptCmds(nodeCmdParams);
        //验证该节点是否存在
        Node node = nodeService.getNodeBySn(sceneSn, nodeSn);
        //验证该节点是否已经存在该节点命令名称/内容
        nodeCmdService.checkExistCmds(node.getNodeId(), nodeCmdParams);
        return HttpResult.oK().message("批量添加节点命令成功").data(nodeCmdService.addNodeCmds(sceneSn, nodeSn, node.getNodeId(), nodeCmdParams));
    }

    @ApiOperation(value = "删除一条节点命令、并且级联删除相关触发器命令表")
    @DeleteMapping("/{ncId}")
    public HttpResult delNodeCmd(@ApiParam(name = "ncId", value = "节点命令Id") @PathVariable("ncId") Long ncId) {
        //检查是否存在此节点命令
        nodeCmdService.getNodeCmdById(ncId);
        nodeCmdService.deleteByNcId(ncId);
        return HttpResult.responseOK("节点命令删除成功");
    }

    @ApiOperation(value = "根据节点Id获取节点下所有的节点命令")
    @GetMapping("/nodeId/{nodeId}")
    public HttpResult getNodeCmds(@ApiParam(name = "nodeId", value = "节点命令Id") @PathVariable("nodeId") Long nodeId) {
        return HttpResult.responseOK(nodeCmdService.getNodeCmdsByNodeId(nodeId));
    }

    @ApiOperation(value = "根据节点Id删除节点下所有的节点命令")
    @DeleteMapping("/nodeId/{nodeId}")
    public HttpResult delNodeCmds(@ApiParam(name = "nodeId", value = "节点命令Id") @PathVariable("nodeId") Long nodeId) {
        //检查是否存在此节点
        nodeService.getNodeByNodeId(nodeId);
        //检查此节点下是否存在节点命令
        List<Long> ncIds = nodeCmdService.getNcIdsByNodeId(nodeId);
        if (Objects.isNull(ncIds) || ncIds.size() == 0) {
            return HttpResult.generateErrorResult(-1,"该节点下无节点命令");
        }
        nodeCmdService.deleteByNodeId(nodeId);
        //删除触发器触发命令
        nodeActCmdService.delNodeActCmdByNcIds(ncIds);
        return HttpResult.responseOK("该节点下的命令已删除成功");
    }

    @ApiOperation(value = "修改节点命令")
    @PutMapping("/{ncId}")
    public HttpResult updateNodeCmd(@ApiParam(name = "ncId", value = "节点命令Id") @PathVariable("ncId") Long ncId,
                                    @ApiParam(name = "nodeCmdParam", value = "节点命令") @Validated @RequestBody() NodeCmdParam nodeCmdParam) {
        if (StringUtils.isBlank(nodeCmdParam.getNcName()) && StringUtils.isBlank(nodeCmdParam.getNcContent())) {
            return HttpResult.generateErrorResult(-1, "更新信息不能同时为空");
        }
        return HttpResult.oK().message("修改节点命令成功").data(nodeCmdService.updateByNcId(ncId, nodeCmdParam));
    }

}
