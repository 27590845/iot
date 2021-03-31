package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.dataapi.controller.res.Page;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.databiz.constants.CommunicateEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 通讯协议
 * @author: Hansey
 * @date: 2021-01-17 20:30
 */
@Api(tags = "通讯协议", description = "提供通讯协议操作的相关接口")
@RestController
@RequestMapping("/communicate")
public class CommunicateController {

    @ApiOperation(value = "获取所有的通讯协议及类型")
    @GetMapping()
    public HttpResult getAllCommun() {
        HashMap<String,Object> map = new HashMap<>();
        //循环输出值
        for (CommunicateEnum e : CommunicateEnum.values()) {
            map.put(e.getCommCode(),e.getCommType());
        }
        return HttpResult.responseOK(map);
    }
}
