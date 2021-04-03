package com.xidian.iot.dataapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.xidian.iot.dataapi.util.GrafanaApiUtil;
import com.xidian.iot.dataapi.util.GrafanaFormatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

    @ApiOperation(value = "/sceneSn", notes = "根据sceneSn获取dashboard")
    @GetMapping("/{sceneSn}")
    public void grafanaRedirect(@ApiParam(name = "sceneSn", value = "网关号") @PathVariable String sceneSn){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        try {
            request.getRequestDispatcher("/dashboard/db/"+sceneSn+"?orgId=1").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据基本数据信息生成dashboard
     * @param dataInfos 基本数据信息，三个字段：sceneSn， nodeSn， attrKey
     * @param usePrettyStyle 是否使用好看些的样式
     */
    public void initDashboard(List<Map<String, String>> dataInfos, boolean usePrettyStyle){
        if(CollectionUtils.isEmpty(dataInfos) || !dataInfos.get(0).containsKey("sceneSn")) return;
        List<GrafanaFormatUtil.Panel> panels = new ArrayList<>();
        String sceneSn = dataInfos.get(0).get("sceneSn");
        for (Map<String, String> dataInfo : dataInfos){
            GrafanaFormatUtil.Panel panel = new GrafanaFormatUtil.Panel(null, 0
                    , "", "", "", "", "", ""
                    ,0,0,0,0);
            //只需先设置好以下三个属性
            panel.setMeasurement(sceneSn);
            panel.setTag(dataInfo.get("nodeSn"));
            panel.setColumn(dataInfo.get("attrKey"));
            panels.add(panel);
        }
        if(usePrettyStyle){
            panels = GrafanaFormatUtil.prettyDashboardPanels(panels);
        }else {
            panels = GrafanaFormatUtil.defaultDashboardPanels(panels, 0, 1);
        }
        GrafanaApiUtil.deleteDashboardsBySlug(sceneSn);
        JSONObject dashboard = GrafanaApiUtil.getOrCreateDashboard(sceneSn);
        GrafanaFormatUtil.addPanel(dashboard, panels.toArray(new GrafanaFormatUtil.Panel[]{}));
        JSONObject result = GrafanaApiUtil.updateDashboard(dashboard);
        System.out.println(result);
    }

}
