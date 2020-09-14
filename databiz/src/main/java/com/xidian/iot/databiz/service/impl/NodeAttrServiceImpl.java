package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.constants.ExceptionEnum;
import com.xidian.iot.common.util.Assert;
import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.entity.NodeAttrExample;
import com.xidian.iot.database.entity.NodeAttrStdExample;
import com.xidian.iot.database.mapper.NodeAttrMapper;
import com.xidian.iot.databiz.service.NodeAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节点属性增删改查业务逻辑
 * @author: Hansey
 * @date: 2020-09-14 10:35
 */
@Service
public class NodeAttrServiceImpl implements NodeAttrService {
    @Autowired
    private NodeAttrMapper nodeAttrMapper;

    @Override
    public NodeAttr getNodeAttrById(Long naId) {
        NodeAttrExample nodeAttrExample = new NodeAttrExample();
        nodeAttrExample.createCriteria().andNaIdEqualTo(naId);
        List<NodeAttr> nodeAttrs = nodeAttrMapper.selectByExample(nodeAttrExample);
        Assert.isTrue(nodeAttrs.size() > 0, ExceptionEnum.NODE_ATTR_NOT_EXIST);
        return nodeAttrs.get(0);
    }
}
