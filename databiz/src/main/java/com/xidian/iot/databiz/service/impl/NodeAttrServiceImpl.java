package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.mapper.NodeAttrMapper;
import com.xidian.iot.databiz.service.NodeAttrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mrl
 * @Title: NodeAttrServiceImpl
 * @Package
 * @Description:
 * @date 2020/9/14 7:21 下午
 */
@Service
public class NodeAttrServiceImpl implements NodeAttrService {

    @Resource
    private NodeAttrMapper nodeAttrMapper;

    @Override
    public NodeAttr getNodeAttrById(Long naId) {
        return nodeAttrMapper.selectByPrimaryKey(naId);
    }
}
