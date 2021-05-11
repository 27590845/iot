package com.xidian.iot.dataapi.controller;

import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.dataapi.controller.res.Page;
import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.database.param.NodeCondParam;
import com.xidian.iot.database.param.NodeTrigParam;
import com.xidian.iot.database.valid.ValidGroup;
import com.xidian.iot.databiz.service.RuleEngineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author mrl
 * @Title: RuleEngineController
 * @Package
 * @Description: 规则引擎相关接口
 * @date 2020/9/17 9:15 下午
 */
@Api(tags = "规则引擎")
@RestController
@RequestMapping("/rule")
public class RuleEngineController {

    @Resource
    private RuleEngineService ruleEngineService;

    @ApiOperation("添加一条规则")
    @PostMapping
    public HttpResult add(
            @ApiParam("要添加的规则，规则应该包含基本规则信息，规则条件列表，规则触发动作列表，规则触发报警列表")
            @RequestBody @Validated(ValidGroup.INSERT.class) NodeTrigParam nodeTrigParam) {
        return HttpResult.responseOK(ruleEngineService.addRuleEngine(nodeTrigParam));
    }

    @ApiOperation("添加单个节点的触发条件")
    @PostMapping("/{ntId}")
    public HttpResult addNodeCond(
            @ApiParam(name = "ntId", value = "") @PathVariable("ntId") Long ntId,
            @ApiParam("需要添加的规则条件")
            @RequestBody @Validated(ValidGroup.INSERT.class) NodeCondParam nodeCondParam) {
        return HttpResult.responseOK(ruleEngineService.addNodeCond(ntId, nodeCondParam));
    }

    @ApiOperation("删除一条规则，会级联删除相关触发器条件，触发器动作，触发器报警")
    @DeleteMapping("/{ntId}")
    public HttpResult del(@ApiParam(name = "ntId", value = "") @PathVariable("ntId") Long ntId) {
        return HttpResult.responseOK(ruleEngineService.delRuleEngine(ntId));
    }

    @ApiOperation("删除单个节点触发条件")
    @DeleteMapping("/nc/{ncId}")
    public HttpResult delNodeCond(@ApiParam(name = "ncId", value = "") @PathVariable("ncId") Long ncId) {
        return HttpResult.responseOK(ruleEngineService.delNodeCondByNcId(ncId));
    }

    @ApiOperation("删除多个节点触发条件")
    @DeleteMapping("/ncs/{ncIds}")
    public HttpResult delNodeConds(@ApiParam(name = "ncIds", value = "") @PathVariable("ncIds") String ncIds) {
        String[] ids = ncIds.split(",");
        List<Long> ncIdss = new ArrayList<>();
        for (String id : ids) {
            ncIdss.add(Long.valueOf(id));
        }
        if (Objects.isNull(ncIdss) || ncIdss.size() < 1) {
            throw new BusinessException(-1, "至少有一个触发条件");
        }
        return HttpResult.responseOK(ruleEngineService.delNodeCondByNcIds(ncIdss));
    }

    @ApiOperation(value = "更新触发器")
    @PutMapping("/{ntId}")
    public HttpResult updateRuleEngine(@ApiParam(name = "ntId", value = "") @PathVariable("ntId") Long ntId,
                                  @ApiParam(name = "NodeTrigParam", value = "触发器更新信息") @Validated(ValidGroup.UPDATE.class) @RequestBody NodeTrigParam nodeTrigParam) {
        ruleEngineService.updateRuleEngine(ntId, nodeTrigParam);
        return HttpResult.oK().message("更新成功");
    }

    @ApiOperation(value = "更新触发器,支持新增")
    @PutMapping("/update/{ntId}")
    public HttpResult updateRuleEngineAll(@ApiParam(name = "ntId", value = "") @PathVariable("ntId") Long ntId,
                                          @ApiParam(name = "NodeTrigParam", value = "触发器更新信息") @Validated(ValidGroup.UPDATE.class) @RequestBody NodeTrigParam nodeTrigParam) {
        ruleEngineService.updateRuleEngine1(ntId, nodeTrigParam);
        return HttpResult.oK().message("更新成功");
    }

    @ApiOperation(value = "只更新节点触发器")
    @PatchMapping("/{ntId}")
    public HttpResult updateNodeTrigger(@ApiParam(name = "ntId", value = "") @PathVariable("ntId") Long ntId,
                                        @ApiParam(name = "NodeTrig", value = "触发器更新信息") @RequestBody NodeTrig nodeTrig) {
        ruleEngineService.updateNodeTrig(ntId, nodeTrig);
        return HttpResult.oK().message("更新成功");
    }

    @ApiOperation("获取规则、包含规则内容")
    @GetMapping("/{ntId}")
    public HttpResult get(@ApiParam(name = "ntId", value = "") @PathVariable("ntId") Long ntId) {
        return HttpResult.responseOK(ruleEngineService.getRuleEngine(ntId));
    }

    @ApiOperation(value = "获取规则列表")
    @GetMapping("/getRuleList")
    public HttpResult getRuleList(@ApiParam(name = "page", value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @ApiParam(name = "limit", value = "页数") @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        //分页一方面获取总条数、一方面获取数据、如果可以把page和limit也可以带着
        int total = ruleEngineService.countRuleEngine();
        Page<NodeTrigParam> ruleEnginePage = new Page<>(total, page, limit);
        ruleEnginePage.setData(ruleEngineService.getNodeTrigParam(page,limit));
        return HttpResult.responseOK(ruleEnginePage);
    }
}
