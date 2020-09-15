package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.param.SceneAddParam;
import com.xidian.iot.database.param.SceneUpdateParam;
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
@Api(tags = "/scene", description = "提供场景操作的相关接口")
@RestController
@RequestMapping("/scene")
public class SceneController {
    @Autowired
    private SceneService sceneService;

    @ApiOperation(value = "分页获取当前用户下所有的网关号")
    @GetMapping("/all")
    public HttpResult getUserScenes(@ApiParam(name = "page",value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @ApiParam(name = "limit",value = "页数") @RequestParam(value = "limit", required = false, defaultValue = "5") int limit){
        return HttpResult.responseOK(sceneService.getAllScenes(page,limit));
    }

    @ApiOperation(value = "根据Sn获取指定场景接口")
    @GetMapping("/{sceneSn}")
    public HttpResult getScene(@ApiParam(name = "sceneSn",value = "场景sn") @PathVariable("sceneSn") String sceneSn){
        return HttpResult.responseOK(sceneService.getSceneBySn(sceneSn));
    }

    @ApiOperation(value = "添加场景")
    @PostMapping
    public HttpResult addScene(@ApiParam(name = "SceneAddParam",value = "场景信息") @Valid @RequestBody SceneAddParam param){
        return HttpResult.responseOK(sceneService.addScene(param));
    }

    @ApiOperation(value = "删除场景")
    @DeleteMapping("/{sceneSn}")
    public HttpResult delScene(@ApiParam(name = "sceneSn",value = "场景sn") @PathVariable("sceneSn") String sceneSn){
        sceneService.delScene(sceneSn);
        return HttpResult.oK().message("删除场景成功");
    }

    @ApiOperation(value = "更新场景")
    @PutMapping("/{sceneSn}")
    public HttpResult updateScene(@ApiParam(name = "sceneSn",value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                  @ApiParam(name = "SceneUpdateParam",value = "场景更新信息") @Valid @RequestBody SceneUpdateParam param){
        sceneService.updateScene(sceneSn, param);
        return HttpResult.oK().message("更新场景成功");
    }
}
