package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.param.SceneAddParam;
import com.xidian.iot.database.param.SceneUpdateParam;
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
@Api(tags = "/nodeAttrStd", description = "提供节点属性操作的相关接口")
@RestControllerAdvice
public class NodeAttrStdController {
    @Autowired
    private NodeAttrStdService nodeAttrStdService;

    @ApiOperation(value = "分页获取所有的节点属性模版")
    @GetMapping("/list")
    public HttpResult getNodeAttrStds(@ApiParam(name = "page",value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @ApiParam(name = "limit",value = "页数") @RequestParam(value = "limit", required = false, defaultValue = "5") int limit){
        return HttpResult.responseOK(nodeAttrStdService.getNodeAttrStds(page,limit));
    }

    @ApiOperation(value = "根据nasId获取指定节点属性模版")
    @GetMapping("/{nasId}")
    public HttpResult getNodeAttrStd(@ApiParam(name = "nasId",value = "节点属性模版ID") @PathVariable("nasId") Long nasId){
        return HttpResult.responseOK(nodeAttrStdService.getNodeAttrStd(nasId));
    }
//
//    @ApiOperation(value = "添加节点属性模版")
//    @PostMapping
//    public HttpResult addNodeAttrStd(@ApiParam(name = "SceneAddParam",value = "场景信息") @Valid @RequestBody SceneAddParam param){
//        return HttpResult.responseOK(nodeAttrStdService.addNodeAttrStd(param));
//    }
//
//    @ApiOperation(value = "删除场景")
//    @DeleteMapping("/{nasId}")
//    public HttpResult delNodeAttrStd(@ApiParam(name = "nasId",value = "节点属性模版ID") @PathVariable("nasId") Long nasId){
//        nodeAttrStdService.delNodeAttrStd(nasId);
//        return HttpResult.oK().message("删除场景成功");
//    }
//
//    @ApiOperation(value = "更新场景")
//    @PutMapping("/{sceneSn}")
//    public HttpResult updateScene(@ApiParam(name = "sceneSn",value = "场景sn") @PathVariable("sceneSn") String sceneSn,
//                                  @ApiParam(name = "SceneUpdateParam",value = "场景更新信息") @Valid @RequestBody SceneUpdateParam param){
//        sceneService.updateScene(sceneSn, param);
//        return HttpResult.oK().message("更新场景成功");
//    }
//
//    @ApiOperation(value = "查看一个节点的数据")
//    @GetMapping("/{sceneSn}/node/{nodeSn}")
//    public HttpResult getScene(@ApiParam(name = "sceneSn",value = "场景sn") @PathVariable("sceneSn") String sceneSn
//            ,@ApiParam(name = "nodeSn",value = "节点sn") @PathVariable("nodeSn") String nodeSn){
//        return HttpResult.responseOK(sceneService.getSceneBySn(sceneSn));
//    }
}
