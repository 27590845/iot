package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.param.NodeActAlertParam;
import com.xidian.iot.database.valid.ValidGroup;
import com.xidian.iot.databiz.service.NodeActAlertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * 警报信息
 *
 * @author: wmr
 * @date: 2020-11-27 15:39:14
 */
@Api(tags = "警报设置", description = "提供警报设置操作的相关接口")
@RestControllerAdvice
@RequestMapping("/nodeActAlert")
public class NodeActAlertController {
    @Autowired
    NodeActAlertService nodeActAlertService;

    @ApiOperation(value = "添加警报")
    @PostMapping
    public HttpResult addNodeActAlert(@ApiParam(name = "NodeActAlertParam", value = "警报信息") @Validated(ValidGroup.INSERT.class) @RequestBody NodeActAlertParam nodeActAlertParam) {
        //单独判断触发器ID是否为空以及是否满足个数，这里与添加触发器部分INSERT冲突，添加触发器时不要求触发器ID存在。故这里单纯判断。
        if (Objects.isNull(nodeActAlertParam.getNtId())) {
            return HttpResult.generateErrorResult(-1, "添加触发器Id不可为空");
        }
//        if (nodeActAlertParam.getNtId().toString().length() != 18 && nodeActAlertParam.getNtId().toString().length() != 19) {
//            return HttpResult.generateErrorResult(-1, "添加触发器Id长度在18～19");
//        }
        //查看是否已存在相同的警报
        nodeActAlertService.checkExistNodeActAlert(nodeActAlertParam);
        return HttpResult.responseOK(nodeActAlertService.addNodeActAlert(nodeActAlertParam));
    }

    @ApiOperation(value = "删除警报")
    @DeleteMapping("/{naaId}")
    public HttpResult delNodeActAlert(@ApiParam(name = "naaId", value = "警报ID") @PathVariable("naaId") Long naaId) {
        nodeActAlertService.deleteByNodeActAlertId(naaId);
        return HttpResult.oK().message("删除警报成功");
    }

    @ApiOperation(value = "更新警报信息")
    @PutMapping("/{naaId}")
    public HttpResult updateNodeActAlert(@ApiParam(name = "naaId", value = "警报ID") @PathVariable("naaId") Long naaId,
                                         @ApiParam(name = "NodeActAlertParam", value = "警报信息") @Validated(ValidGroup.UPDATE.class) @RequestBody NodeActAlertParam nodeActAlertParam) {
        //查看是否存在此警报naaId
        NodeActAlert nodeActAlertById = nodeActAlertService.getNodeActAlertById(naaId);
        //查看是否已存在相同的警报
        nodeActAlertService.checkExistNodeActAlert(nodeActAlertParam);
        nodeActAlertService.updateNodeActAlert(naaId, nodeActAlertParam);
        return HttpResult.oK().message("更新警报成功");
    }

    @ApiOperation(value = "查看警报")
    @GetMapping("/{naaId}")
    public HttpResult getNodeActAlert(@ApiParam(name = "naaId", value = "警报ID") @PathVariable("naaId") Long naaId) {
        return HttpResult.responseOK(nodeActAlertService.getNodeActAlertById(naaId));
    }

    @ApiOperation(value = "查看某一触发器下绑定的警报")
    @GetMapping("/{ntId}/list")
    public HttpResult getNodeActAlertByntId(@ApiParam(name = "ntId", value = "触发器ID") @PathVariable("ntId") Long ntId,
                                            @ApiParam(name = "page", value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                            @ApiParam(name = "limit", value = "页数") @RequestParam(value = "limit", required = false, defaultValue = "5") int limit) {
        return HttpResult.responseOK(nodeActAlertService.getNodeActAlertsByntId(ntId, page, limit));
    }
}
