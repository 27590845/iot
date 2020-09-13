package com.xidian.iot.databiz.service.impl;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.common.constants.ExceptionEnum;
import com.xidian.iot.common.util.Assert;
import com.xidian.iot.database.entity.NodeAttrStd;
import com.xidian.iot.database.entity.NodeAttrStdExample;
import com.xidian.iot.database.mapper.NodeAttrStdMapper;
import com.xidian.iot.databiz.service.NodeAttrStdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节点属性模版业务逻辑类
 * @author: Hansey
 * @date: 2020-09-13 11:19
 */
@Service
public class NodeAttrStdServiceImpl implements NodeAttrStdService {
    @Autowired
    private NodeAttrStdMapper nodeAttrStdMapper;

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
        Assert.isTrue(nodeAttrStds.size() > 0, ExceptionEnum.NODE_ATTR_STD_EXIST);
        return nodeAttrStds.get(0);
    }
}
