package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.dataapi.service.SceneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "获取指定资产信息接口")
    @GetMapping("/{sceneId}")
    public HttpResult getAsset(@ApiParam(name = "sceneId",value = "场景id") @PathVariable("assetId") Integer sceneId){
        return HttpResult.responseOK(sceneService.getSceneById(sceneId));
    }

}
