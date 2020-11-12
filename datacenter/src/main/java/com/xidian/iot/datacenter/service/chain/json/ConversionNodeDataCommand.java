package com.xidian.iot.datacenter.service.chain.json;

import com.xidian.iot.common.util.JsonUtil;
import com.xidian.iot.database.entity.mongo.NodeData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 负责转换上数的DataNode数据，设置到上数上下文中。
 *
 * 解析格式如下：
 * [
 *      {sn:"123123",at:"9876313123",key1:"value1",key2:"value2",keyN:"valueN"},
 *      {sn:"123123",at:"9876313123",key1:"value1",key2:"value2",keyN:"valueN"},
 *      ....
 * ]
 * 以上格式中，key:value对不限，但sn、at是必须的字段。参见{@link UpDataConst#NODE_SN_KEY}{@link UpDataConst#NODE_AT_KEY}。
 */

/**
 * @author mrl
 * @Title: ConversionNodeDataCommand
 * @Package
 * @Description: 负责转换上传的DataNode数据    copy from langyan    modify: 去掉node.sn_value，其功能由node_sn替代
 * @date 2020/9/10 2:55 下午
 */
@Slf4j
public class ConversionNodeDataCommand implements Command {

    /**
     * =====入口======
     * @param context
     * @return
     * @throws Exception
     */
    @Override
    public boolean execute(Context context) throws Exception {
        log.debug("================================Start conversion node data.================================");
        // 将json数据转换为NodeData
        conversionNodeData((JsonDataContext) context);
        log.debug("================================Complete conversion node data.================================");
        return false;
    }

    /**
     * 转换为NodeData数据
     * @param context 上下文
     * @return
     * @throws Exception
     */
    private void conversionNodeData(JsonDataContext context) throws Exception {
        // 获得合法数据
        List<Map> legalDatastreamsList = context.getLegalDatastreamsList();
        // 验证上数数据,抛弃无效数据
        List<NodeData> nodeDataList = new ArrayList<NodeData>();
        NodeData nodeData = null;
        for (Map<String, Object> origData : legalDatastreamsList) {
            Map<String, Object> data = JsonUtil.toObject(JsonUtil.toJson(origData), Map.class);
            // 删除着两个属性，因为在NodeData中已经记录了这两个属性
            data.remove(JsonDataContext.NODE_SN_KEY);
            data.remove(JsonDataContext.NODE_AT_KEY);
            // 创建一个节点数据
            nodeData = new NodeData();
            nodeData.setSceneSn(context.getSceneSn()); // 场景SN
            nodeData.setNodeSn((String) origData.get("sn"));// 节点SN
            nodeData.setAt(noAtGetAt(origData)); // 获取上数时间
            nodeData.setData(data); // 设置上数数据
            // 添加到列表中
            nodeDataList.add(nodeData);
        }
        log.debug("Converion the map list to NodeData list complete.");
        log.debug("\t====>>the NodeData list {}", nodeDataList);
        // 将节点数据设置到上数上下文中
        context.setNodeDataList(nodeDataList);
    }

    /**
     * 从上数数据中获得时间，若无时间，则采用系统时间。
     * @param data 上数数据。
     * @return 上数时间点或系统时间
     */
    private Long noAtGetAt(Map<String, Object> data) {
        Object origAt = data.get(JsonDataContext.NODE_AT_KEY);
        Long at = null;
        if (origAt instanceof String && NumberUtils.isDigits((String) origAt)) {
            at = Long.parseLong((String) origAt);
        } else if (origAt instanceof Integer) {
            at = ((Integer) origAt).longValue();
        } else {
            at = new Long(System.currentTimeMillis() / 1000);
        }
        return at;
    }
}