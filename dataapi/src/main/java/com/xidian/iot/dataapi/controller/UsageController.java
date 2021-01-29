package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.databiz.constants.UsageEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 使用场景
 * @author: Hansey
 * @date: 2021-01-17 20:30
 */
@Api(tags = "使用场景", description = "提供使用场景的相关接口")
@RestController
@RequestMapping("/usage")
public class UsageController {

    @ApiOperation(value = "获取所有的使用场景")
    @GetMapping()
    public HttpResult getAllUsage() {
        HashMap<String,Object> map = new HashMap<>();

        for (UsageEnum e : UsageEnum.values()) {
            map.put(e.getUsageCode(),e.getUsageType());
        }
        return HttpResult.responseOK(map);
    }
}
