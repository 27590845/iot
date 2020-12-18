package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.param.NodeActAlertParam;

import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.param.NodeActAlertParam;

import java.util.List;


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
    /**
     * 根据naaId获取NodeActAlert
     *
     * @param naaId
     * @return com.xidian.iot.database.entity.NodeActAlert
     */
    NodeActAlert getNodeActAlertById(Long naaId);

    /**
     * 根据ntId获取NodeActAlerts
     *
     * @param ntId
     * @return com.xidian.iot.database.entity.NodeActAlert
     */
    List<NodeActAlert> getNodeActAlertsByntId(Long ntId,int page,int limit);

    /**
     * 根据ntId获取NodeActAlerts
     *
     * @param ntId
     * @return com.xidian.iot.database.entity.NodeActAlert
     */
    List<NodeActAlert> getNodeActAlertsByntId(Long ntId);

    /**
     * 添加关联报警
     *
     * @param nodeActAlertParam 新增的关联报警
     * @return com.xidian.iot.database.entity.NodeActAlert
     */
    NodeActAlert addNodeActAlert(NodeActAlertParam nodeActAlertParam);

    /**
     * 根据警报Id删除关联报警
     * @param naaId 警报Id
     * @return void
     * */
    void deleteByNodeActAlertId(Long naaId);

    /**
     * @param naaId  警报Id
     * @param param 更新的警报
     * @return void
     */
    void updateNodeActAlert(Long naaId, NodeActAlertParam param);

    /**
     * 查找是否已存在该警报
     * @param param 警报
     * @return void
     */
    void checkExistNodeActAlert(NodeActAlertParam param);
}
