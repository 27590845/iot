package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.param.NodeAddParam;
import com.xidian.iot.database.param.SceneAddParam;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.databiz.service.SceneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public HttpResult addScene(@ApiParam(name = "NodeAdd",value = "场景信息") @Valid @RequestBody NodeAddParam param){
        return HttpResult.responseOK(nodeService.addNode(param));
    }

    @ApiOperation(value = "删除节点")
    @DeleteMapping
    public HttpResult delScene(@ApiParam(name = "NodeAdd",value = "场景信息") @Valid @RequestBody NodeAddParam param){
        return HttpResult.responseOK(nodeService.addNode(param));
    }
}
