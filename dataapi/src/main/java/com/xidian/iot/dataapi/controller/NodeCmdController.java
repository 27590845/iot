package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.param.NodeCmdParam;
import com.xidian.iot.database.param.ValidList;
import com.xidian.iot.database.valid.ValidGroup;
import com.xidian.iot.databiz.service.NodeCmdService;
import com.xidian.iot.databiz.service.NodeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 节点命令相关接口
 *
 * @author: Hansey
 * @date: 2020-09-22 20:11
 */
@RestController
@RequestMapping("/nodeCmd")
public class NodeCmdController {
    @Autowired
    private NodeCmdService nodeCmdService;
    @Autowired
    private NodeService nodeService;

    @ApiOperation(value = "批量添加节点命令、保证添加的命令描述字段、命令内容不重复")
    @PostMapping("/{sceneSn}/{nodeSn}")
    public HttpResult addNodeCmds(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                  @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable("nodeSn") String nodeSn,
                                  @ApiParam(name = "nodeCmdParams", value = "节点属命令列表") @Validated(ValidGroup.INSERT.class) @RequestBody() ValidList<NodeCmdParam> nodeCmdParams) {
        //验证是否存在重复的节点命令名称/内容
        nodeCmdService.checkReptCmds(nodeCmdParams);
        //验证该节点是否存在
        Node node = nodeService.getNodeVoBySn(sceneSn, nodeSn);
        //验证该节点是否已经存在该节点命令名称/内容
        nodeCmdService.checkExistCmds(node.getNodeId(), nodeCmdParams);
        nodeCmdService.addNodeCmds(sceneSn, nodeSn, node.getNodeId(), nodeCmdParams);
        return HttpResult.responseOK("批量添加节点命令成功");
    }

    @ApiOperation(value = "删除一条节点命令、并且级联删除相关触发器命令表")
    @DeleteMapping("/{ncId}")
    public HttpResult addNodeCmds(@ApiParam(name = "ncId", value = "节点命令Id") @PathVariable("ncId") Long ncId) {
        nodeCmdService.deleteByNcId(ncId);
        return HttpResult.responseOK("节点命令删除成功");
    }

    @ApiOperation(value = "根据节点Id获取节点下所有的节点命令")
    @GetMapping("/nodeId/{nodeId}")
    public HttpResult getNodeCmds(@ApiParam(name = "nodeId", value = "节点命令Id") @PathVariable("nodeId") Long nodeId) {
        nodeCmdService.deleteByNodeId(nodeId);
        return HttpResult.responseOK("节点命令删除成功");
    }

    @ApiOperation(value = "修改节点命令")
    @PutMapping("/{ncId}")
    public HttpResult updateNodeCmd(@ApiParam(name = "ncId", value = "节点命令Id") @PathVariable("ncId") Long ncId,
                                    @ApiParam(name = "nodeCmdParam", value = "节点命令") @Validated @RequestBody() NodeCmdParam nodeCmdParam) {
        if (StringUtils.isBlank(nodeCmdParam.getNcName()) && StringUtils.isBlank(nodeCmdParam.getNcContent())) {
            return HttpResult.generateErrorResult(-1, "更新信息不能同时为空");
        }
        nodeCmdService.updateByNcId(ncId, nodeCmdParam);
        return HttpResult.responseOK("修改节点命令成功");
    }

}
