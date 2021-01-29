package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.databiz.service.OauthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录认证部分、这里的oauth并不是真正的oauth、因为并没有client的概念、
 * 只有单个用户（admin、用户名和密码写到配置文件中），不提供第三方授权使用，并不需要oauth这种授权第三方应用授权机制。
 * 只是名字这样起比较高大上，只使用其中的token令牌机制。
 * 其中的token是jwt、集群token共享使用redis
 *
 * @author: Hansey
 * @date: 2020-10-19 10:09
 */
@Api(tags = "授权", description = "提供权限访问机制")
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Value("${nm}")
    private String nm1;

    @Value("${pw}")
    private String pwd1;

    @Resource
    private OauthService oauthService;

    @ApiOperation(value = "获取授权令牌token",notes="根据用户名和密码获取授权令牌token")
    @GetMapping("/getAccessToken")
    public HttpResult getNode(@ApiParam(name = "nm", value = "登录用户名") @RequestParam(value = "nm", required = true) String nm,
                              @ApiParam(name = "pwd", value = "密码") @RequestParam(value = "pwd", required = true) String pwd) {
        if (!nm.equals(nm1)) {
            return HttpResult.generateErrorResult(-1, "用户名错误");
        }
        if (!pwd.equals(pwd1)) {
            return HttpResult.generateErrorResult(-1, "密码错误");
        }
        return HttpResult.responseOK(oauthService.getToken());
    }
}
