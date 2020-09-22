package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.dataapi.controller.res.Page;
import com.xidian.iot.database.entity.NodeCmdGroupStd;
import com.xidian.iot.databiz.service.NodeCmdGroupStdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 节点命令组名称模版
 * @author: Hansey
 * @date: 2020-09-21 14:48
 */
@Api(tags = "/nodeCmdGroupStd", description = "提供节点命令组名称操作的相关接口")
@RestController
@RequestMapping("/nodeCmdGroupStd")
public class NodeCmdGroupStdController {
    @Autowired
    private NodeCmdGroupStdService nodeCmdGroupStdService;

    @ApiOperation(value = "分页获取所有的节点命令组模版")
    @GetMapping("/list")
    public HttpResult getNodeCmdGroupStds(@ApiParam(name = "page",value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @ApiParam(name = "limit",value = "页数") @RequestParam(value = "limit", required = false, defaultValue = "5") int limit){
        int total = nodeCmdGroupStdService.getCount();
        Page<NodeCmdGroupStd> nodeCmdGroupStdPage = new Page<NodeCmdGroupStd>(total,page,limit);
        nodeCmdGroupStdPage.setData(nodeCmdGroupStdService.getNodeCmdGroupStds(page,limit));
        return HttpResult.responseOK(nodeCmdGroupStdPage);
    }

//    @ApiOperation(value = "添加节点属性模版")
//    @PostMapping
//    public HttpResult addNodeCmdGroupStd(@ApiParam(name = "NodeCmdGroupStdParam",value = "节点属性模版") @Valid @RequestBody NodeAttrStdParam param){
//        return HttpResult.oK().message("添加成功").data(nodeAttrStdService.addNodeAttrStd(param));
//    }
//
//    @ApiOperation(value = "删除节点属性模版")
//    @DeleteMapping("/{nasId}")
//    public HttpResult delNodeAttrStd(@ApiParam(name = "nasId",value = "节点属性模版ID") @PathVariable("nasId") Long nasId){
//        nodeAttrStdService.delNodeAttrStd(nasId);
//        return HttpResult.oK().message("删除节点属性模版");
//    }
//
//    @ApiOperation(value = "更新节点属性模版")
//    @PutMapping("/{nasId}")
//    public HttpResult updateNodeAttrStd(@ApiParam(name = "nasId",value = "节点属性模版ID") @PathVariable("nasId") Long nasId,
//                                        @ApiParam(name = "NodeAttrStdParam",value = "节点属性模版") @Valid @RequestBody NodeAttrStdParam param){
//        nodeAttrStdService.updateNodeAttrStd(param.buildNodeAttrStd(nasId));
//        return HttpResult.oK().message("更新节点属性模版成功");
//    }
//
//    @ApiOperation(value = "部分更新节点属性模版")
//    @PatchMapping("/{nasId}")
//    public HttpResult selectUpdateScene(@ApiParam(name = "nasId",value = "节点属性模版ID") @PathVariable("nasId") Long nasId,
//                                        @ApiParam(name = "NodeAttrStdParam",value = "节点属性模版")  @RequestBody NodeAttrStdParam param){
//        nodeAttrStdService.updateNodeAttrStd(param.filterNodeAttrStd(nasId));
//        return HttpResult.oK().message("更新节点属性模版成功");
//    }

}
