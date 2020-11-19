package com.xidian.iot.dataapi.controller;

import com.xidian.iot.common.util.uid.UidPrefixFactory;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.databiz.service.SceneService;
import com.xidian.iot.databiz.service.UidGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * master 节点特有的接口
 * @author: Hansey
 * @date: 2020-09-05 21:36
 */

@Api(tags = "master 节点特有的接口")
@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private UidPrefixFactory uidPrefixFactory;

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

    @ApiOperation(value = "idPrefix", notes = "用于获取master的id前缀")
    @GetMapping("/idPrefix")
    public HttpResult getIdPrefix(){
        return HttpResult.oK()
                .code(0)
                .message("获取id前缀成功")
                .data(uidPrefixFactory.getPrefix())
                .success(true);
    }

    @Autowired
    private UidGenerator uidGenerator;

    @ApiOperation(value = "idGen", notes = "用于获取master的id前缀")
    @GetMapping("/idGen")
    public HttpResult getId(){
        return HttpResult.oK()
                .code(0)
                .message("获取id前缀成功")
                .data(uidGenerator.getUID())
                .success(true);
    }
}
