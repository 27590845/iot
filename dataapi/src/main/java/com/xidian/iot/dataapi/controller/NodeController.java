package com.xidian.iot.dataapi.controller;

import com.xidian.iot.common.constants.ExceptionEnum;
import com.xidian.iot.common.util.StringUtil;
import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.param.NodeAddParam;
import com.xidian.iot.database.param.NodeUpdateParam;
import com.xidian.iot.databiz.service.NodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: Hansey
 * @date: 2020-09-10 21:42
 */
@Api(tags = "/node", description = "提供节点操作的相关接口")
@RestController
@RequestMapping("/node")
public class NodeController {
    @Autowired
    private NodeService nodeService;

    @ApiOperation(value = "添加节点")
    @PostMapping
    public HttpResult addNode(@ApiParam(name = "NodeAdd", value = "场景信息") @Valid @RequestBody NodeAddParam param) {
        return HttpResult.responseOK(nodeService.addNode(param));
    }

    @ApiOperation(value = "删除节点")
    @DeleteMapping("/{sceneSn}/{nodeSn}")
    public HttpResult delNode(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                               @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable(value = "nodeSn") String nodeSn) {
        nodeService.delNode(sceneSn, nodeSn);
        return HttpResult.oK().message("删除场景成功");
    }

    @ApiOperation(value = "更新节点名称和描述")
    @PutMapping("/{sceneSn}/{nodeSn}")
    public HttpResult updateScene(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                  @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable(value = "nodeSn") String nodeSn,
                                  @ApiParam(name = "NodeUpdateParam", value = "节点更新信息") @Valid @RequestBody NodeUpdateParam param) {
        if (StringUtils.isBlank(param.getNodeName())&&StringUtil.isBlank(param.getNodeDesc())){
            return HttpResult.generateErrorResult(ExceptionEnum.PARAMETER_VERIFICATION_ERROR);
        }
        nodeService.updateNode(sceneSn, nodeSn, param);
        return HttpResult.oK().message("更新成功");
    }

    @ApiOperation(value = "根据Sn获取指定场景接口（查询结果包含其节点的属性）")
    @GetMapping("/{sceneSn}/{nodeSn}")
    public HttpResult getNode(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                              @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable("nodeSn") String nodeSn) {
        return HttpResult.responseOK(nodeService.getNodeVoBySn(sceneSn, nodeSn));
    }
}
