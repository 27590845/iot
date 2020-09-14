package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.NodeActCmd;
import com.xidian.iot.databiz.service.NodeActCmdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeActCmdServiceImpl
 * @Package
 * @Description:
 * @date 2020/9/14 7:20 下午
 */
@Service
public class NodeActCmdServiceImpl implements NodeActCmdService {
    @Override
    public List<NodeActCmd> getNodeActCmdByNtId(Long ntId) {
        return null;
    }
}
