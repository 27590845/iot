package com.xidian.iot.dataapi.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xidian.iot.common.util.GetProps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
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

    private static Map<String, String> defaultHeader(){
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("connection", "Keep-Alive");
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        headers.put("Authorization", "Bearer " + GRAFANA_API_KEY);
        return headers;
    }

    //在dashboard后面追加两个折线图
    public static void main(String[] args) {
        JSONObject dashboard = GrafanaApiUtil.getOrCreateDashboard("20210403121001");
        int lastId = 0, lastY = 0, lastH = 0, panelH = 8;
        if(dashboard!=null && dashboard.containsKey("panels")){
            //下面是获取最后一个面板的id，和最下面一个面板的y坐标与高
            for(Object obj:dashboard.getJSONArray("panels")){
                JSONObject panel = (JSONObject) obj;
                if(panel.containsKey("id")) {
                    int id = panel.getIntValue("id");
                    lastId = id>lastId?id:lastId;
                }
                if(panel.containsKey("gridPos")) {
                    JSONObject gridPos = panel.getJSONObject("gridPos");
                    int y = gridPos.getIntValue("y");
                    if(y > lastY){
                        lastY = y;
                        lastH = gridPos.getIntValue("h");
                    }
                }
            }
        }
        GrafanaFormatUtil.addPanel(dashboard
                , new GrafanaFormatUtil.Panel(GrafanaFormatUtil.PanelTemp.LINE, ++lastId,
                        "RD001.tem1 折线图", "RD001.tem1", "测试，温度折线图", "186610102211100356", "RD001", "tem1",
                        panelH,12,0,lastY+lastH));
        GrafanaFormatUtil.addPanel(dashboard
                , new GrafanaFormatUtil.Panel(GrafanaFormatUtil.PanelTemp.LINE, ++lastId,
                        "RD001.tem2 折线图", "RD001.tem2", "测试，温度则线图", "186610102211100356", "RD001", "tem2",
                        panelH,12,0,lastY+lastH+panelH));
        JSONObject result = updateDashboard(dashboard);
        System.out.println(result);
    }
}
