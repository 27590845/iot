package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.database.param.NodeTrigParam;
import com.xidian.iot.database.valid.ValidGroup;
import com.xidian.iot.databiz.service.NodeTrigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author mrl
 * @Title: RuleEngineController
 * @Package
 * @Description: 规则引擎相关接口
 * @date 2020/9/17 9:15 下午
 */
@Api(tags = "/nodeTrig")
@RestController
@RequestMapping("/nodeTrig")
public class RuleEngineController {

    @Resource
    private NodeTrigService nodeTrigService;

    @ApiOperation("添加一条规则")
    @PostMapping
    public HttpResult add(
            @ApiParam("要添加的规则，规则应该包含基本规则信息，规则条件列表，规则触发动作列表，规则触发报警列表")
            @RequestBody @Validated(ValidGroup.INSERT.class) NodeTrigParam nodeTrigParam
    ){
        return HttpResult.responseOK(nodeTrigService.addRuleEngine(nodeTrigParam));
    }
}
