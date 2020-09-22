package com.xidian.iot.databiz.service.impl;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.common.util.Assert;
import com.xidian.iot.common.util.constants.ExceptionEnum;
import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.database.entity.NodeAttrStd;
import com.xidian.iot.database.entity.NodeAttrStdExample;
import com.xidian.iot.database.mapper.NodeAttrStdMapper;
import com.xidian.iot.database.param.NodeAttrStdParam;
import com.xidian.iot.databiz.service.NodeAttrStdService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 节点属性模版业务逻辑类
 * @author: Hansey
 * @date: 2020-09-13 11:19
 */
@Service
public class NodeAttrStdServiceImpl implements NodeAttrStdService {
    @Autowired
    private NodeAttrStdMapper nodeAttrStdMapper;
    @Autowired
    private UidGenerator uidGenerator;

    @Override
    public List<NodeAttrStd> getNodeAttrStds(int page, int limit) {
        if (page >= 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        return nodeAttrStdMapper.selectByExample(new NodeAttrStdExample());
    }

    @Override
    public NodeAttrStd getNodeAttrStd(Long nasId) {
        NodeAttrStdExample nodeAttrStdExample = new NodeAttrStdExample();
        nodeAttrStdExample.createCriteria().andNasIdEqualTo(nasId);
        List<NodeAttrStd> nodeAttrStds = nodeAttrStdMapper.selectByExample(nodeAttrStdExample);
        Assert.isTrue(nodeAttrStds.size() > 0, ExceptionEnum.NODE_ATTR_STD_NOT_EXIST);
        return nodeAttrStds.get(0);
    }

    @Override
    public NodeAttrStd addNodeAttrStd(NodeAttrStdParam param) {
        NodeAttrStd nodeAttrStd = param.buildNodeAttrStd(uidGenerator.getUID());
        nodeAttrStdMapper.insertSelective(nodeAttrStd);
        return nodeAttrStd;
    }

    @Override
    public void delNodeAttrStd(Long nasId) {
        NodeAttrStdExample nodeAttrStdExample = new NodeAttrStdExample();
        nodeAttrStdExample.createCriteria().andNasIdEqualTo(nasId);
        Assert.isTrue(nodeAttrStdMapper.deleteByExample(nodeAttrStdExample) > 0, ExceptionEnum.NODE_ATTR_STD_NOT_EXIST);
    }

    @Override
    public void updateNodeAttrStd(NodeAttrStd nodeAttrStd) {
        //判断该nasId是否存在
        getNodeAttrStd(nodeAttrStd.getNasId());
        //如果更改了nasKey就判断该nasKey是否已经存在
        if (!Objects.isNull(nodeAttrStd.getNasKey())) {
            NodeAttrStdExample nodeAttrStdExample = new NodeAttrStdExample();
            nodeAttrStdExample.createCriteria().andNasIdNotEqualTo(nodeAttrStd.getNasId()).andNasKeyEqualTo(nodeAttrStd.getNasKey());
            if(nodeAttrStdMapper.countByExample(nodeAttrStdExample)>0){
                throw new BusinessException(-1, "更改节点属性模版失败，该节点属性模版key在系统中已经存在");
            }
        }
        nodeAttrStdMapper.updateByPrimaryKeySelective(nodeAttrStd);
    }

    @Override
    public int getCount() {
        return (int) nodeAttrStdMapper.countByExample(new NodeAttrStdExample());
    }
}
