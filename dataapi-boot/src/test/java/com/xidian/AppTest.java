package com.xidian;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONObject;
import com.xidian.iot.dataapi.util.GrafanaApiUtil;
import com.xidian.iot.dataapi.util.GrafanaFormatUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    //在dashboard后面追加两个折线图
    @Test
    public void updateDashboardTest() {
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
        JSONObject result = GrafanaApiUtil.updateDashboard(dashboard);
        System.out.println(result);
    }

    @Test
    public void initDashboardTest(){
        List<Map<String, String>> baseInfos = new ArrayList<>();

        Map<String, String> baseHum = new HashMap<>();
        baseHum.put("sceneSn", "186610102211100356");
        baseHum.put("nodeSn", "RD001");
        baseHum.put("attrKey", "hum");
        baseInfos.add(baseHum);

        Map<String, String> baseTem3 = new HashMap<>();
        baseTem3.put("sceneSn", "186610102211100356");
        baseTem3.put("nodeSn", "RD001");
        baseTem3.put("attrKey", "tem3");
        baseInfos.add(baseTem3);

        Map<String, String> baseTem4 = new HashMap<>();
        baseTem4.put("sceneSn", "186610102211100356");
        baseTem4.put("nodeSn", "RD001");
        baseTem4.put("attrKey", "tem4");
        baseInfos.add(baseTem4);

        Map<String, String> baseTem1 = new HashMap<>();
        baseTem1.put("sceneSn", "186610102211100356");
        baseTem1.put("nodeSn", "RD001");
        baseTem1.put("attrKey", "tem1");
        baseInfos.add(baseTem1);

//        Map<String, String> baseTem2 = new HashMap<>();
//        baseTem2.put("sceneSn", "186610102211100356");
//        baseTem2.put("nodeSn", "RD001");
//        baseTem2.put("attrKey", "tem2");
//        baseInfos.add(baseTem2);

        JSONObject result = GrafanaApiUtil.initDashboardFromBaseInfo(baseInfos, "2021040401", true);
        System.out.println(result.toJSONString());
    }

}
