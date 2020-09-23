package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.param.NodeTrigParam;
import com.xidian.iot.database.valid.ValidGroup;
import com.xidian.iot.databiz.service.RuleEngineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author mrl
 * @Title: RuleEngineController
 * @Package
 * @Description: 规则引擎相关接口
 * @date 2020/9/17 9:15 下午
 */
@Api(tags = "/rule")
@RestController
@RequestMapping("/rule")
public class RuleEngineController {

    @Resource
    private RuleEngineService ruleEngineService;

    @ApiOperation("添加一条规则")
    @PostMapping
    public HttpResult add(
            @ApiParam("要添加的规则，规则应该包含基本规则信息，规则条件列表，规则触发动作列表，规则触发报警列表")
            @RequestBody @Validated(ValidGroup.INSERT.class) NodeTrigParam nodeTrigParam
    ){
        return HttpResult.responseOK(ruleEngineService.addRuleEngine(nodeTrigParam));
    }

    @ApiOperation("删除一条规则，会级联删除相关触发器条件，触发器动作，触发器报警")
    @DeleteMapping("/{ntId}")
    public HttpResult del(@ApiParam(name = "ntId", value = "") @PathVariable("ntId") Long ntId){
        return HttpResult.responseOK(ruleEngineService.delRuleEngine(ntId));
    }
}
