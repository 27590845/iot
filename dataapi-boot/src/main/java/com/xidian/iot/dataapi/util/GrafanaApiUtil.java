package com.xidian.iot.dataapi.util;

import com.alibaba.fastjson.JSONObject;
import com.xidian.iot.common.util.GetProps;
import com.xidian.iot.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: mrl
 * @date: 2021/4/2 下午9:34
 */
@Slf4j
public class GrafanaApiUtil {

    /**
     * 使用 grafana api 需要在请求头添加 API KEY：
     * curl -H "Authorization: Bearer apiKey" \
     * https://iot.xidian.edu.cn:50080/api/dashboards/home
     */

    public static final GetProps getProps = new GetProps("/conf/application.properties");
    public static final String GRAFANA_SERVER = getProps.getPropValue("grafana.server");

    public static final String GRAFANA_API_KEY = getProps.getPropValue("grafana.api.key");
    public static final String GRAFANA_API_WRITE = getProps.getPropValue("grafana.api.write");
    public static final String GRAFANA_API_GET_BYSLUG = getProps.getPropValue("grafana.api.get.byslug");

    public static final String GRAFANA_PARAM_SLUG = getProps.getPropValue("grafana.param.slug");

    /**
     * 若dashboard存在，返回true，否则返回false
     * @param slug
     * @return
     */
    public static boolean detectDashboardBySlug(String slug){
        int result = HttpUtil.sendHttpRequest(GRAFANA_SERVER+ GRAFANA_API_GET_BYSLUG.replace(GRAFANA_PARAM_SLUG, slug), defaultHeader());
        return result==200;
    }

    public static JSONObject deleteDashboardsBySlug(String slug){
        String result = HttpUtil.sendHttpRequestForContent(GRAFANA_SERVER+ GRAFANA_API_GET_BYSLUG.replace(GRAFANA_PARAM_SLUG, slug)
                , defaultHeader(), HttpUtil.Method.DELETE);
        JSONObject resultJson = JSONObject.parseObject(result);
        return resultJson;
    }

    /**
     * 根据slug查询dashboard，若存在，返回json格式的dashboard字符串，否则返回空字符串
     * @param slug
     * @return
     */
    public static JSONObject getDashboardsBySlug(String slug){
        String result = HttpUtil.sendHttpRequestForContent(GRAFANA_SERVER+ GRAFANA_API_GET_BYSLUG.replace(GRAFANA_PARAM_SLUG, slug)
                , defaultHeader(), HttpUtil.Method.GET);
        JSONObject resultJson = JSONObject.parseObject(result);
        JSONObject dashboard = null;
        if(result!=null && resultJson.containsKey("dashboard")){
            dashboard = resultJson.getJSONObject("dashboard");
        }
        return dashboard;
    }

    /**
     * 根据slug查询dashboard，若存在，返回dashboard对应的JSONObject，否则先创建再返回
     * @param slug
     * @return
     */
    public static JSONObject getOrCreateDashboard(String slug){
        if(!detectDashboardBySlug(slug)){
            String initDashboardResStr = HttpUtil.sendPost(GRAFANA_SERVER+GRAFANA_API_WRITE
                    , GrafanaFormatUtil.DASHBOARD_INIT.replace(GrafanaFormatUtil.placeholder_dash_title, slug)
                    , defaultHeader());
            log.info("created dashboard info: {}", initDashboardResStr);
        }
        return getDashboardsBySlug(slug);
    }

    /**
     * 修改dashboard，dashboard必须包含uid字段
     * @param dashboard
     * @return
     */
    public static JSONObject updateDashboard(JSONObject dashboard){
        if(dashboard == null || !dashboard.containsKey("uid")) return null;
        JSONObject reqBody = new JSONObject();
        reqBody.put("dashboard", dashboard);
        reqBody.put("message", "grafana api测试");
        reqBody.put("overwrite", false);
        Map<String,  String> header = defaultHeader();
        header.put("Content-Length", String.valueOf(reqBody.toJSONString().length()));
        String result = HttpUtil.sendPost(GRAFANA_SERVER+GRAFANA_API_WRITE, reqBody.toJSONString(), header);
        return JSONObject.parseObject(result);
    }

    /**
     * 根据基本数据信息生成dashboard
     * @param dataInfos 基本数据信息，三个字段：sceneSn， nodeSn， attrKey
     * @param usePrettyStyle 是否使用好看些的样式
     */
    public static JSONObject initDashboardFromBaseInfo(List<Map<String, String>> dataInfos, String dashboardName, boolean usePrettyStyle){
        if(CollectionUtils.isEmpty(dataInfos)) return null;
        List<GrafanaFormatUtil.Panel> panels = new ArrayList<>();
        // 如果没有指定dashboardName，就设置name为sceneSn
        if(StringUtil.isBlank(dashboardName)) dashboardName = dataInfos.get(0).get("sceneSn");
        for (Map<String, String> dataInfo : dataInfos){
            GrafanaFormatUtil.Panel panel = new GrafanaFormatUtil.Panel(null, 0
                    , "", "", "", "", "", ""
                    ,0,0,0,0);
            //只需先设置好以下三个属性
            panel.setMeasurement(dataInfo.get("sceneSn"));
            panel.setTag(dataInfo.get("nodeSn"));
            panel.setColumn(dataInfo.get("attrKey"));
            panels.add(panel);
        }
        if(usePrettyStyle){
            panels = GrafanaFormatUtil.prettyDashboardPanels(panels);
        }else {
            panels = GrafanaFormatUtil.defaultDashboardPanels(panels, 0, 1);
        }
        GrafanaApiUtil.deleteDashboardsBySlug(dashboardName);
        JSONObject dashboard = GrafanaApiUtil.getOrCreateDashboard(dashboardName);
        GrafanaFormatUtil.addPanel(dashboard, panels.toArray(new GrafanaFormatUtil.Panel[]{}));
        return GrafanaApiUtil.updateDashboard(dashboard);
    }

    private static Map<String, String> defaultHeader(){
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("connection", "Keep-Alive");
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        headers.put("Authorization", "Bearer " + GRAFANA_API_KEY);
        return headers;
    }

    /**
     * 根据基本数据信息生成dashboard
     * @param dataInfos 基本数据信息，三个字段：sceneSn， nodeSn， attrKey
     * @param dashboardName 面板名称，默认为sceneSn
     * @return  发送更新dashborad请求后的操作结果（类型为JSONObject）
     */
    public static JSONObject initDashboardFromBaseInfo2(List<Map<String, String>> dataInfos, String dashboardName) {
        if (CollectionUtils.isEmpty(dataInfos)) return null;
        List<GrafanaFormatUtil.Panel> panels = new ArrayList<>();
        if (StringUtil.isBlank(dashboardName)) dashboardName = dataInfos.get(0).get("sceneSn");
        for (Map<String, String> dataInfo : dataInfos) {
            List<GrafanaFormatUtil.Panel> panelList = SetPanelAtrribute.setPanle(dataInfo);
            panels.addAll(panelList);
        }
        GrafanaApiUtil.deleteDashboardsBySlug(dashboardName);
        JSONObject dashboard = GrafanaApiUtil.getOrCreateDashboard(dashboardName);
        GrafanaFormatUtil.addPanel(dashboard, panels.toArray(new GrafanaFormatUtil.Panel[]{}));
        return GrafanaApiUtil.updateDashboard(dashboard);
    }

}
