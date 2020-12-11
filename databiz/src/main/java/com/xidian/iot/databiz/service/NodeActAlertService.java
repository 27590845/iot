package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.param.NodeActAlertParam;

/**
 * @author mrl
 * @Title: NodeActAlertService
 * @Package
 * @Description: 节点触发器关联报警相关服务
 * @date 2020/9/11 9:28 下午
 */
public interface NodeActAlertService {

    /**
     * 添加节点触发器关联相关触发报警信息
     * @param nodeActAlert
     * @return int
     * */
    int addNodeActAlert(NodeActAlert nodeActAlert);

    int delNodeActAlertByNtId(Long ntId);

    /**
     * 更新节点触发器关联相关触发报警
     * @param nodeActAlert
     * @return int
     * */
    int updateNodeActAlert(NodeActAlert nodeActAlert);
}
