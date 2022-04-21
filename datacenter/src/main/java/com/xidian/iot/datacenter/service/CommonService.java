package com.xidian.iot.datacenter.service;

import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.database.entity.custom.NodeTrigExt;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.databiz.service.NodeActAlertService;
import com.xidian.iot.databiz.service.NodeAttrService;
import com.xidian.iot.databiz.service.NodeCondService;
import com.xidian.iot.databiz.service.NodeTrigService;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mrl
 * @Title: CommonService
 * @Package
 * @Description: 定义了一些公共service操作，均为单表查询
 * @date 2020/9/20 1:04 下午
 */
@Service
public class CommonService {

    @Resource
    private NodeAttrService nodeAttrService;
    @Resource
    private NodeCondService nodeCondService;
    @Resource
    private NodeTrigService nodeTrigService;
    @Resource
    private NodeActAlertService nodeActAlertService;

    /**
     * 根据sceneSn、nodeSn，获取条件所关联的触发器未失效、且不在时间间隔内的条件
     * @param sceneSn
     * @param nodeSn
     * @return
     */
    public List<NodeCondExt> getNodeCondExts(String sceneSn, String nodeSn){
        //先获取ncId
        List<Long> ncIds = nodeCondService.getNcIdsBySn(sceneSn, nodeSn);
        //根据ncId获取NodeExt列表为啥不一步到位
        List<NodeCondExt> nodeCondExts = ncIds.stream().map(ncId -> nodeCondService.getNodeCondExtById(ncId)).collect(Collectors.toList());
        //只获取条件所关联的触发器未失效、且不在时间间隔内的条件
        nodeCondExts = nodeCondExts.stream().filter(nodeCondExt -> {
            NodeTrigExt nodeTrigExt = nodeTrigService.getNodeTrigExtById(nodeCondExt.getNtId());
            return !(nodeTrigExt.getNtExec()==1 || (nodeTrigExt.getNtExpr()!=null && nodeTrigExt.getNtExpr().before(new Date())))
                    && (nodeTrigExt.getLastRunTime()==null || nodeTrigExt.getLastRunTime().getTime()+(nodeTrigExt.getNtInvl()*1000)<=System.currentTimeMillis());
        }).collect(Collectors.toList());
        return nodeCondExts;
    }

    /**
     * 在getNodeCondExts(String sceneSn, String nodeSn)的基础上，去掉没有关联到naKey的条件
     * @param sceneSn
     * @param nodeSn
     * @param naKeys
     * @return
     */
    public List<NodeCondExt> getNodeCondExts(String sceneSn, String nodeSn, Set<String> naKeys){
        List<NodeCondExt> nodeCondExts = getNodeCondExts(sceneSn, nodeSn);
        Map<String, String> naMap = nodeAttrService.getNaMapBySn(sceneSn, nodeSn);
        nodeCondExts = nodeCondExts.stream()
                .filter(nodeCondExt -> naKeys.contains(naMap.get(String.valueOf(nodeCondExt.getNaId())))).collect(Collectors.toList());
        nodeCondExts.forEach(nodeCondExt -> nodeCondExt.setNaKey(naMap.get(String.valueOf(nodeCondExt.getNaId()))));
        return nodeCondExts;
    }

    public List<NodeCondExt> getNodeCondExts(Long ntId){
        return nodeCondService.getNcIdsByNtId(ntId)
                .stream().map(ncId -> nodeCondService.getNodeCondExtById(ncId)).collect(Collectors.toList());
    }
    
    public NodeTrigExt getNodeTrigExt(Long ntId){
        return nodeTrigService.getNodeTrigExtById(ntId);
    }

    public void changeNodeCondExt(NodeCondExt nodeCondExt) {
        nodeCondService.changeNodeCondExt(nodeCondExt);
    }

    public void updateNodeTrigExtById(NodeTrigExt nodeTrigExt) {
        nodeTrigService.updateNodeTrigExtById(nodeTrigExt);
    }

    public List<NodeActAlert> getNodeActAlerts(Long ntId){
        return nodeActAlertService.getNodeActAlertsByntId(ntId);
    }
}
