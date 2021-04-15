package com.xidian.iot.dataapi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ltp
 * @create 2021-04-13 14:32
 */
public class SetPanelAtrribute {

    private static int panelId = 1;     //每个dashboard的panel初始Id值

    /**
     * 设置panel
     * @param dataInfo 基本数据信息，三个字段：sceneSn， nodeSn， attrKey
     * @return  对应节点属性需要展示的panel列表
     */
    public static List<GrafanaFormatUtil.Panel> setPanle(Map<String, String> dataInfo){
        String[] panelTypes = dataInfo.get("panelType").split("_");
        List<GrafanaFormatUtil.Panel> panels = new ArrayList<>();
        for(int i = 0; i < panelTypes.length; i++) {    //遍历当前属性的panelTypes，即需要展示的图表类型，设置对应的panel属性值
            GrafanaFormatUtil.Panel panel = new GrafanaFormatUtil.Panel(null, 0
                    , "", "", "", "", "", ""
                    , 0, 0, 0, 0);
            //只需先设置好以下三个属性
            panel.setMeasurement(dataInfo.get("sceneSn"));
            panel.setTag(dataInfo.get("nodeSn"));
            panel.setColumn(dataInfo.get("attrKey"));
            panel.setTitle(panel.getTag() + "." + panel.getColumn());
            panel.setAlias(panel.getTag() + "." + panel.getColumn());
            panel.setDescription("");
            //设置panel高度和宽度
            panel.setHeight(8);
            panel.setWidth(12);     //dashboard的最大宽度为24
            //设置panel的类型
            switch (panelTypes[i]) {
                case "heat":
                    panel.setType(GrafanaFormatUtil.PanelTemp.HEATMAP);
                    break;
                case "line":
                    panel.setType(GrafanaFormatUtil.PanelTemp.LINE);
                    break;
                case "stat":
                    panel.setType(GrafanaFormatUtil.PanelTemp.STAT);
                    break;
                case "max":
                    panel.setType(GrafanaFormatUtil.PanelTemp.MAX);
                    break;
                case "min":
                    panel.setType(GrafanaFormatUtil.PanelTemp.MIN);
                    break;
            }
            //设置panel的id
            panel.setId(panelId++);
            //设置panel在dashboard中的x和y的值，即panel展现的位置(一行展示两个)
            panel.setXAxis(panel.getWidth() * ((panel.getId() - 1) % 2));
            panel.setYAxis(panel.getHeight() * ((panel.getId() - 1) / 2));
            panels.add(panel);
        }
        return panels;
    }

}
