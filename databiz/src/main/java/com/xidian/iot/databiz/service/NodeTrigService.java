package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.database.entity.custom.NodeTrigExt;
import com.xidian.iot.database.param.NodeTrigParam;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeTrigService
 * @Package
 * @Description:
 * @date 2020/9/10 9:52 下午
 */
public interface NodeTrigService {

    /**
     * 通过{@link com.xidian.iot.database.entity.NodeActCmd}.ncId列表获取NodeTrig.ntId列表
     * @param ncIds
     * @return
     */
    List<Long> getNtIdsByNcIds(List<Long> ncIds);

    /**
     * 根据NodeTrig.ntId获取NodeTrig的拓展类
     * @param ntId
     * @return
     */
    NodeTrigExt getNodeTrigExtById(Long ntId);

    /**
     * 通过Id更新NodeTrigExt，更新至数据库、缓存
     * @param nodeTrigExt
     * @return
     */
    NodeTrigExt updateNodeTrigExtById(NodeTrigExt nodeTrigExt);

    /**
     * 删除一条触发器
     * @param ntId
     * @return
     */
    int delNodeTrigByNtId(Long ntId);

    /**
     * 添加一条触发器数据
     * @param nodeTrig
     * @return
     */
    NodeTrig addNodeTrig(NodeTrig nodeTrig);
}
