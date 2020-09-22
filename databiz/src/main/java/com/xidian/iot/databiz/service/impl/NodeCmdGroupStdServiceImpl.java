package com.xidian.iot.databiz.service.impl;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.database.entity.NodeCmdGroupStd;
import com.xidian.iot.database.entity.NodeCmdGroupStdExample;
import com.xidian.iot.database.mapper.NodeCmdGroupStdMapper;
import com.xidian.iot.databiz.service.NodeCmdGroupService;
import com.xidian.iot.databiz.service.NodeCmdGroupStdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节点命令组名称模版相关服务-增产改查
 * @author: Hansey
 * @date: 2020-09-21 14:52
 */
@Service
public class NodeCmdGroupStdServiceImpl implements NodeCmdGroupStdService {
    @Autowired
    private NodeCmdGroupStdMapper nodeCmdGroupStdMapper;

    @Override
    public int getCount() {
        return (int)nodeCmdGroupStdMapper.countByExample(new NodeCmdGroupStdExample());
    }

    @Override
    public List<NodeCmdGroupStd> getNodeCmdGroupStds(int page, int limit) {
        if (page >= 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        return nodeCmdGroupStdMapper.selectByExample(new NodeCmdGroupStdExample());
    }
}
