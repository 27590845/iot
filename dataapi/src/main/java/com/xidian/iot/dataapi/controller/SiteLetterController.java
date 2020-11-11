package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.databiz.service.SiteLetterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wmr
 * @date: 2020-09-21 20:38
 */

@Api(tags = "/站内信",description = "提供站内信查看的接口")
@RestController
@RequestMapping("/siteletter")
public class SiteLetterController {
    @Autowired
    private SiteLetterService siteLetterService;

    @ApiOperation("分页获取当前用户下的所有站内信")
    @GetMapping("/list")
    public HttpResult getSiteLetters(@ApiParam(name = "page", value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                     @ApiParam(name = "limit", value = "页数") @RequestParam(value = "limit", required = false, defaultValue = "5") int limit){
        return HttpResult.responseOK(siteLetterService.getSiteLetters(page,limit));
    }

}
