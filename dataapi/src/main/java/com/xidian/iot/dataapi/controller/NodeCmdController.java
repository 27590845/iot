package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.param.NodeAttrParam;
import com.xidian.iot.database.param.NodeCmdParam;
import com.xidian.iot.database.param.ValidList;
import com.xidian.iot.database.valid.ValidGroup;
import com.xidian.iot.databiz.service.NodeAttrService;
import com.xidian.iot.databiz.service.NodeCmdService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 节点命令相关接口
 * @author: Hansey
 * @date: 2020-09-22 20:11
 */
@RestController
@RequestMapping("/nodeCmd")
public class NodeCmdController {
    @Autowired
    private NodeCmdService nodeCmdService;

    @ApiOperation(value = "批量添加节点命令")
    @PostMapping("/{sceneSn}/{nodeSn}")
    public HttpResult addNodeAttrs(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                   @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable("nodeSn") String nodeSn,
                                   @ApiParam(name = "nodeAttrs", value = "节点属性列表") @Validated(ValidGroup.INSERT.class) @RequestBody() ValidList<NodeCmdParam> nodeCmdParams) {
        nodeCmdService.addNodeCmds(sceneSn, nodeSn, nodeCmdParams);
        return HttpResult.responseOK("批量添加节点命令成功");
    }
}
