package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.util.Assert;
import com.xidian.iot.common.util.constants.ExceptionEnum;
import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.entity.NodeAttrExample;
import com.xidian.iot.database.mapper.NodeAttrMapper;
import com.xidian.iot.database.mapper.custom.NodeAttrCustomMapper;
import com.xidian.iot.database.param.NodeAttrParam;
import com.xidian.iot.database.param.NodeAttrUpdateParam;
import com.xidian.iot.databiz.service.NodeAttrService;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 节点属性增删改查业务逻辑
 *
 * @author: Hansey
 * @date: 2020-09-14 10:35
 */
@Service
public class NodeAttrServiceImpl implements NodeAttrService {
    @Autowired
    private NodeAttrMapper nodeAttrMapper;
    @Autowired
    private NodeAttrCustomMapper nodeAttrCustomMapper;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private UidGenerator uidGenerator;

    @Override
    public NodeAttr getNodeAttrById(Long naId) {
        NodeAttrExample nodeAttrExample = new NodeAttrExample();
        nodeAttrExample.createCriteria().andNaIdEqualTo(naId);
        List<NodeAttr> nodeAttrs = nodeAttrMapper.selectByExample(nodeAttrExample);
        Assert.isTrue(nodeAttrs.size() > 0, ExceptionEnum.NODE_ATTR_NOT_EXIST);
        return nodeAttrs.get(0);
    }

    @Override
    public List<NodeAttr> addNodeAttr(String sceneSn, String nodeSn, List<NodeAttrParam> nodeAttrs) {
        Node node = nodeService.getNodeBySn(sceneSn, nodeSn);
        //先检查批量插入的是否有重复、先检查添加的属性是否存在
        List<String> repeNodeAttrs = nodeAttrs.stream()
                .map(e -> e.getNaKey()).collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet().stream() // 所有 entry 对应的 Stream
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList()); // 转化为 List
        if (repeNodeAttrs.size() > 0) {
            throw new BusinessException(-1, "传入的" + repeNodeAttrs.toString() + "，这些传感器属性重复、请确认后重新添加");
        } else {
            //批量插入属性没有重复，检查添加的属性是否存在。
            List<String> nodeAttrKeys = nodeAttrs.stream()
                    .map(e -> e.getNaKey()).collect(Collectors.toList());
            NodeAttrExample attrExample = new NodeAttrExample();
            attrExample.createCriteria().andNodeIdEqualTo(node.getNodeId()).andNaKeyIn(nodeAttrKeys);
            List<NodeAttr> alreadyExisted = nodeAttrMapper.selectByExample(attrExample);
            if (alreadyExisted.size() > 0) {
                throw new BusinessException(-1, alreadyExisted.stream()
                        .map(e -> e.getNaKey())
                        .collect(Collectors.toList()).toString() + "传感器属性在该节点已存在、请确认后重新添加");
            } else {
                List<NodeAttr> list = nodeAttrs.stream().map(nodeAttrParam -> {
                    Long naId = uidGenerator.getUID();
                    return nodeAttrParam.build(naId, node.getNodeId());
                }).collect(Collectors.toList());
                nodeAttrCustomMapper.insertBatch(list);
                return list;
            }
        }
    }

    @Override
    public void delNodeAttrs(String sceneSn, String nodeSn, List<String> naKeyLists) {
        Node node = nodeService.getNodeBySn(sceneSn, nodeSn);
        NodeAttrExample attrExample = new NodeAttrExample();
        NodeAttrExample.Criteria criteria = attrExample.createCriteria().andNodeIdEqualTo(node.getNodeId());
        //如果传入的naKeyLists为空则删除该节点下所有属性
        if (Objects.isNull(naKeyLists)) {
            nodeAttrMapper.deleteByExample(attrExample);
        } else {
            //先查看是否存在该属性
            criteria.andNaKeyIn(naKeyLists);
            //alreadyExisted为传入keys对应数据库中的数据
            List<NodeAttr> alreadyExisted = nodeAttrMapper.selectByExample(attrExample);
            //将alreadyExisted中的naKeys转化为string类型的List
            List<String> existedNaKeys = alreadyExisted.stream().map(e -> e.getNaKey()).collect(Collectors.toList());
            //过滤传入naKeys在该节点中实际未存在的属性
            List<String> notExistedKeys = naKeyLists.stream().filter(item -> !existedNaKeys.contains(item)).collect(Collectors.toList());
            if (notExistedKeys.size() > 0) {
                //如果有实际未存在的属性就删除失败，返回错误信息
                throw new BusinessException(-1, "删除失败，传入的" + notExistedKeys.toString() + "，这些传感器属性不存在，请确认后重新删除");
            } else {
                nodeAttrMapper.deleteByExample(attrExample);
            }
        }
    }

    @Override
    public NodeAttr getNodeAttr(String sceneSn, String nodeSn, String naKey) {
        Node node = nodeService.getNodeBySn(sceneSn, nodeSn);
        NodeAttrExample attrExample = new NodeAttrExample();
        attrExample.createCriteria().andNodeIdEqualTo(node.getNodeId()).andNaKeyEqualTo(naKey);
        List<NodeAttr> nodeAttrs = nodeAttrMapper.selectByExample(attrExample);
        Assert.isTrue(nodeAttrs.size() > 0, ExceptionEnum.NODE_ATTR_NOT_EXIST);
        return nodeAttrs.get(0);
    }

    @Override
    public void updateNodeAttr(Long naId, NodeAttrUpdateParam param) {
        //查看是否存在此节点属性naId
        NodeAttr nodeAttr = getNodeAttrById(naId);
        //如果更新的是naKey查看该节点下是否已经存在此节点属性naKey
        NodeAttr nodeAttr1 = param.build(naId);
        if (!Objects.isNull(nodeAttr1.getNaKey())) {
            NodeAttrExample attrExample = new NodeAttrExample();
            attrExample.createCriteria().andNaIdNotEqualTo(naId)
                    .andNodeIdEqualTo(nodeAttr.getNodeId())
                    .andNaKeyEqualTo(nodeAttr1.getNaKey());
            if (nodeAttrMapper.countByExample(attrExample) > 0) {
                //该节点已经存在此节点属性
                throw new BusinessException(-1, "更改节点属性失败，该节点属性keys在节点中已经存在");
            }
        }
        nodeAttrMapper.updateByPrimaryKeySelective(nodeAttr1);
    }
}
