package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.util.GrafanaFormatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
     * @param DataInfos 基本数据信息，三个字段：sceneSn， nodeSn， attrKey
     */
    public void initDashboard(List<Map<String, String>> DataInfos){
        List<GrafanaFormatUtil.Panel> panels = new ArrayList<>();
        for (Map<String, String> dataInfo : DataInfos){
            GrafanaFormatUtil.Panel panel = new GrafanaFormatUtil.Panel(GrafanaFormatUtil.PanelTemp.MAX, 0
                    , "", "RD001.tem1", "", "", "", ""
                    ,0,12,0,0);
            panel.setSceneSn(dataInfo.get("sceneSn"));
            panel.setNodeSn(dataInfo.get("nodeSn"));
            panel.setAttrKey(dataInfo.get("attrKey"));
            panels.add(panel);
        }
    }

    public void createDefaultDashboard(List<GrafanaFormatUtil.Panel> panels){
    }

}
