package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.dataapi.controller.res.Page;
import com.xidian.iot.database.entity.NodeAttrStd;
import com.xidian.iot.database.param.NodeAttrStdParam;
import com.xidian.iot.databiz.service.NodeAttrStdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 实现对节点属性模版的增删改查操作
 * @author: Hansey
 * @date: 2020-09-13 11:12
 */
@Api(tags = "节点属性模版", description = "提供节点属性操作的相关接口")
@RestControllerAdvice
@RequestMapping("/nodeAttrStd")
public class NodeAttrStdController {
    @Autowired
    private NodeAttrStdService nodeAttrStdService;

    @ApiOperation(value = "分页获取所有的节点属性模版")
    @GetMapping("/list")
    public HttpResult getNodeAttrStds(){
        int total = nodeAttrStdService.getCount();
        Page<NodeAttrStd> nodeAttrStdPage = new Page<NodeAttrStd>(total,1,10);
        nodeAttrStdPage.setData(nodeAttrStdService.getNodeAttrStds(-1,-1));
        return HttpResult.responseOK(nodeAttrStdPage);
    }

    @ApiOperation(value = "根据nasId获取指定节点属性模版")
    @GetMapping("/{nasId}")
    public HttpResult getNodeAttrStd(@ApiParam(name = "nasId",value = "节点属性模版ID") @PathVariable("nasId") Long nasId){
        return HttpResult.responseOK(nodeAttrStdService.getNodeAttrStd(nasId));
    }

    @ApiOperation(value = "添加节点属性模版")
    @PostMapping
    public HttpResult addNodeAttrStd(@ApiParam(name = "NodeAttrStdParam",value = "节点属性模版") @Valid @RequestBody NodeAttrStdParam param){
        return HttpResult.oK().message("添加成功").data(nodeAttrStdService.addNodeAttrStd(param));
    }

    @ApiOperation(value = "删除节点属性模版")
    @DeleteMapping("/{nasId}")
    public HttpResult delNodeAttrStd(@ApiParam(name = "nasId",value = "节点属性模版ID") @PathVariable("nasId") Long nasId){
        nodeAttrStdService.delNodeAttrStd(nasId);
        return HttpResult.oK().message("删除节点属性模版");
    }

    @ApiOperation(value = "更新节点属性模版")
    @PutMapping("/{nasId}")
    public HttpResult updateNodeAttrStd(@ApiParam(name = "nasId",value = "节点属性模版ID") @PathVariable("nasId") Long nasId,
                                  @ApiParam(name = "NodeAttrStdParam",value = "节点属性模版") @Valid @RequestBody NodeAttrStdParam param){
        nodeAttrStdService.updateNodeAttrStd(param.buildNodeAttrStd(nasId));
        return HttpResult.oK().message("更新节点属性模版成功");
    }

    @ApiOperation(value = "部分更新节点属性模版")
    @PatchMapping("/{nasId}")
    public HttpResult selectUpdateScene(@ApiParam(name = "nasId",value = "节点属性模版ID") @PathVariable("nasId") Long nasId,
                                  @ApiParam(name = "NodeAttrStdParam",value = "节点属性模版")  @RequestBody NodeAttrStdParam param){
        nodeAttrStdService.updateNodeAttrStd(param.filterNodeAttrStd(nasId));
        return HttpResult.oK().message("更新节点属性模版成功");
    }

}
