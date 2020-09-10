package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.param.SceneAddParam;
import com.xidian.iot.databiz.service.SceneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: Hansey
 * @date: 2020-09-06 21:29
 */
@Api(tags = "场景相关接口", description = "提供场景操作的相关接口")
@RestController
@RequestMapping("/scene")
public class SceneController {
    @Autowired
    private SceneService sceneService;

    @ApiOperation(value = "获取指定场景接口")
    @GetMapping("/{sceneId}")
    public HttpResult getScene(@ApiParam(name = "sceneId",value = "场景id") @PathVariable("sceneId") Long sceneId){
        return HttpResult.responseOK(sceneService.getSceneById(sceneId));
    }

    @ApiOperation(value = "添加场景")
    @PostMapping
    public HttpResult addScene(@ApiParam(name = "SceneAddParam",value = "场景信息") @Valid @RequestBody SceneAddParam param){
        return HttpResult.responseOK(sceneService.addScene(param));
    }

}
