package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.entity.NodeActAlertExample;
import com.xidian.iot.database.mapper.NodeActAlertMapper;
import com.xidian.iot.databiz.service.NodeActAlertService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
