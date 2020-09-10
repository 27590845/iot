package com.xidian.iot.dataapi.controller;

import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.databiz.service.SceneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 测试
 * @author: Hansey
 * @date: 2020-09-05 21:36
 */

@Api(tags = "测试相关接口", description = "提供测试操作的相关接口")
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private SceneService sceneService;

    @ApiOperation(value = "欢迎界面", notes = "用于测试欢迎的信息")
    @GetMapping("/welcome")
    public HttpResult welcome(){
        sceneService.testId();
        return new HttpResult();
    }

    @ApiOperation(value = "欢迎界面1", notes = "测试带参数的欢迎信息")
    @GetMapping("/{param}")
    public HttpResult welcome1(@ApiParam(name = "param", value = "测试参数", required = false) @PathVariable String param){
        return new HttpResult(param);
    }

    @ApiOperation(value = "欢迎2", notes = "测试参数为json的欢迎信息")
    @PostMapping("/welcome2")
    public HttpResult welcome2(@ApiParam(name = "param", value = "测试参数对象", required = false) @RequestBody Scene scene){
            return HttpResult.oK()
                    .code(1)
                    .message("dddd")
                    .data("sss")
                    .success(false);
    }

}
