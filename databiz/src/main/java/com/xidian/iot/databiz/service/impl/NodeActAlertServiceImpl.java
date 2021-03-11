package com.xidian.iot.databiz.service.impl;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.common.util.Assert;
import com.xidian.iot.common.util.constants.ExceptionEnum;
import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.entity.NodeActAlertExample;
import com.xidian.iot.database.mapper.NodeActAlertMapper;
import com.xidian.iot.database.mapper.custom.NodeActAlertCustomMapper;
import com.xidian.iot.database.param.NodeActAlertParam;
import com.xidian.iot.databiz.service.NodeActAlertService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 触发报警信息实现类
 * @author: Hansey
 * @date: 2020-12-09 15:41
 */
@Service
public class NodeActAlertServiceImpl implements NodeActAlertService {

    @Resource
    private UidGenerator uidGenerator;
    @Resource
    private NodeActAlertMapper nodeActAlertMapper;
    @Resource
    private NodeActAlertCustomMapper nodeActAlertCustomMapper;

    @Override
    public int addNodeActAlert(NodeActAlert nodeActAlert) {
        nodeActAlert.setNaaId(uidGenerator.getUID());
        return nodeActAlertMapper.insert(nodeActAlert);
    }

    @Override
    public int delNodeActAlertByNtId(Long ntId) {
        NodeActAlertExample nodeActAlertExample = new NodeActAlertExample();
        nodeActAlertExample.createCriteria().andNtIdEqualTo(ntId);
        return nodeActAlertMapper.deleteByExample(nodeActAlertExample);
    }

    @Override
    public int updateNodeActAlert(NodeActAlert nodeActAlert) {
       return nodeActAlertMapper.updateByPrimaryKeySelective(nodeActAlert);
    }

    @Override
    public NodeActAlert getNodeActAlertById(Long naaId) {
        NodeActAlertExample nodeActAlertExample = new NodeActAlertExample();
        nodeActAlertExample.createCriteria().andNaaIdEqualTo(naaId);
        List<NodeActAlert> nodeActAlerts = nodeActAlertMapper.selectByExample(nodeActAlertExample);
        Assert.isTrue(nodeActAlerts.size()>0, ExceptionEnum.NODE_ACT_ALERT_NOT_EXIST);
        return nodeActAlerts.get(0);
    }

    @Override
    public NodeActAlert addNodeActAlert(NodeActAlertParam nodeActAlertParam) {
        NodeActAlert nodeActAlert = nodeActAlertParam.build(uidGenerator.getUID());
        nodeActAlertMapper.insertSelective(nodeActAlert);
        return nodeActAlert;
    }

    @Override
    public void deleteByNodeActAlertId(Long naaId) {
        NodeActAlertExample nodeActAlertExample = new NodeActAlertExample();
        nodeActAlertExample.createCriteria().andNaaIdEqualTo(naaId);
        nodeActAlertMapper.deleteByExample(nodeActAlertExample);
    }

    @Override
    public void updateNodeActAlert(Long naaId, NodeActAlertParam param) {
        NodeActAlert nodeActAlert = param.build(naaId);
        nodeActAlertMapper.updateByPrimaryKeySelective(nodeActAlert);
    }

    @Override
    public void checkExistNodeActAlert(NodeActAlertParam param) {
        NodeActAlertExample nodeActAlertExample = new NodeActAlertExample();
        //通过检查同一触发器下是否存在同一接受者的其他警报来判断是否重复
        nodeActAlertExample.createCriteria().andNtIdEqualTo(param.getNtId()).andNaaTypeEqualTo(param.getNaaType()).andNaaValEqualTo(param.getNaaVal());
        List<NodeActAlert> nodeActAlerts = nodeActAlertMapper.selectByExample(nodeActAlertExample);
        if(nodeActAlerts.size()>0){
            throw new BusinessException(-1,"传入的警报已存在");
        }
    }

    @Override
    public List<NodeActAlert> getNodeActAlertsByntId(Long ntId,int page,int limit) {
        if (page >= 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        NodeActAlertExample nodeActAlertExample = new NodeActAlertExample();
        nodeActAlertExample.createCriteria().andNtIdEqualTo(ntId);
        return nodeActAlertMapper.selectByExample(nodeActAlertExample);
    }

    @Override
    public List<NodeActAlert> getNodeActAlertsByntId(Long ntId) {
        NodeActAlertExample nodeActAlertExample = new NodeActAlertExample();
        nodeActAlertExample.createCriteria().andNtIdEqualTo(ntId);
        return nodeActAlertMapper.selectByExample(nodeActAlertExample);
    }

    @Override
    public int addNodeActAlerts(List<NodeActAlert> nodeActAlerts) {
       nodeActAlerts.forEach(naa -> naa.setNaaId(uidGenerator.getUID()));
       return nodeActAlertCustomMapper.addBatch(nodeActAlerts);
    }

    @Override
    public int updateNodeActAlerts(List<NodeActAlert> nodeActAlerts) {
        return nodeActAlertCustomMapper.updateBatch(nodeActAlerts);
    }
}
