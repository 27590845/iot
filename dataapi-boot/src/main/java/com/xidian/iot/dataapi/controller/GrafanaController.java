package com.xidian.iot.dataapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.dataapi.util.GrafanaApiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description: grafana db 相关操作
 * @author: mrl
 * @date: 2021/4/2 上午11:00
 */
@Api(tags = "master 节点特有的接口")
@RestController
@RequestMapping("/grafana")
public class GrafanaController {

    @ApiOperation(value = "/{sceneSn}", notes = "根据sceneSn获取dashboard")
    @GetMapping("/{sceneSn}")
    public void grafanaRedirect(@ApiParam(name = "sceneSn", value = "网关号") @PathVariable String sceneSn){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        try {
            if(GrafanaApiUtil.detectDashboardBySlug(sceneSn)) {
                request.getRequestDispatcher("/dashboard/db/" + sceneSn + "?orgId=1").forward(request, response);
            }else {
                // 如果没有名为sceneSn的dashboard，则跳转至创建页面
                response.sendRedirect("/html/dashboard/dashboard_init.html" + "?sceneSn="+sceneSn);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "/dashboardName", notes = "根据基本信息生成可视化页面，基本信息是由map组成的list，map包含三个属性：sceneSn、nodeSn、attrKey")
    @PostMapping("/{dashboardName}")
    public HttpResult grafanaInit(@ApiParam(name = "dashboardName", value = "可视化页面的名称，务必设置为sceneSn") @PathVariable String dashboardName
            , @ApiParam(name = "baseInfos", value = "基本信息是由map组成的list，map包含三个属性：sceneSn、nodeSn、attrKey") @RequestBody List<Map<String, String>> baseInfos){
        JSONObject result = GrafanaApiUtil.initDashboardFromBaseInfo2(baseInfos, dashboardName);
        if(result!=null){
            return HttpResult.responseOK(result).message("操作完成");
        }else {
            return HttpResult.generateErrorResult(-1, "操作失败");
        }
    }

    @ApiOperation(value = "/dashboardName", notes = "根据基本信息生成可视化页面，基本信息是由map组成的list，map包含三个属性：sceneSn、nodeSn、attrKey")
    @DeleteMapping("/{dashboardName}")
    public HttpResult grafanaDelete(@ApiParam(name = "dashboardName", value = "可视化页面的名称") @PathVariable String dashboardName){
        JSONObject result = GrafanaApiUtil.deleteDashboardsBySlug(dashboardName);
        if(result!=null){
            return HttpResult.responseOK(result).message("操作完成");
        }else {
            return HttpResult.generateErrorResult(-1, "操作失败");
        }
    }

}
