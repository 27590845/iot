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
import com.xidian.iot.databiz.service.NodeAttrService;
import com.xidian.iot.databiz.service.NodeCondService;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
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
    private NodeCondService nodeCondService;
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
    public void checkReptAttrKeys(List<NodeAttrParam> nodeAttrs) {
        //先检查批量插入的是否有重复、先检查添加的属性是否存在
        List<String> repeNodeAttrs = nodeAttrs.stream()
                .map(e -> e.getNaKey()).collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet().stream() // 所有 entry 对应的 Stream
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList()); // 转化为 List
        if (repeNodeAttrs.size() > 0) {
            throw new BusinessException(-1, "传入的" + repeNodeAttrs.toString() + "，这些传感器属性重复、请确认后重新添加");
        }
    }

    @Override
    public void checkExistAttrKeys(Long nodeId, List<NodeAttrParam> nodeAttrParams) {
        //批量插入属性没有重复，检查添加的属性是否存在。
        List<String> nodeAttrKeys = nodeAttrParams.stream()
                .map(e -> e.getNaKey()).collect(Collectors.toList());
        NodeAttrExample attrExample = new NodeAttrExample();
        attrExample.createCriteria().andNodeIdEqualTo(nodeId).andNaKeyIn(nodeAttrKeys);
        List<NodeAttr> alreadyExisted = nodeAttrMapper.selectByExample(attrExample);
        if (alreadyExisted.size() > 0) {
            throw new BusinessException(-1, alreadyExisted.stream()
                    .map(e -> e.getNaKey())
                    .collect(Collectors.toList()).toString() + "传感器属性在该节点已存在、请确认后重新添加");
        }
    }

    @Override
    public List<NodeAttr> addNodeAttr(String sceneSn, String nodeSn, Long nodeId, List<NodeAttrParam> nodeAttrParamss) {
        List<NodeAttr> nodeAttrs = nodeAttrParamss.stream()
                .map(nodeAttrParam -> nodeAttrParam.build(uidGenerator.getUID(), sceneSn, nodeSn, nodeId)
                ).collect(Collectors.toList());
        nodeAttrCustomMapper.insertBatch(nodeAttrs);
        return nodeAttrs;
    }

    @Override
    public void delNodeAttrs(String sceneSn, String nodeSn, List<String> naKeyLists) {
        //判断是否存在此节点
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
                //找出节点属性Id级联删除节点属性相关联的节点触发条件
                List<Long> naIds = alreadyExisted.stream().map(e -> e.getNaId()).collect(Collectors.toList());
                nodeCondService.delNodeCondByNaIds(naIds);
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
    public void updateNodeAttr(Long naId, NodeAttrParam param) {
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

    @Cacheable(value = "NaMap", key = "'getNaMapBySn:'+#sceneSn+':'+#nodeSn")
    @Override
    public Map<String, String> getNaMapBySn(String sceneSn, String nodeSn) {
        List<Map<String, Object>> naSimples = nodeAttrCustomMapper.getNaSimplesBySn(sceneSn, nodeSn);
        //reids存储Map时，key只能反序列化为String，暂时无更好的决方案
        Map<String, String> naMap = naSimples.stream().collect(Collectors.toMap(m -> String.valueOf(m.get("na_id")), m -> (String) m.get("na_key")));
        return naMap;
    }

    @Override
    public void deleteByNodeId(Long nodeId) {
        //获取节点下所有的节点属性Id
        List<Long> naIds = nodeAttrCustomMapper.getNAIdsByNodeId(nodeId);
        if (!Objects.isNull(naIds) && naIds.size() > 0) {
            NodeAttrExample nodeAttrExample = new NodeAttrExample();
            nodeAttrExample.createCriteria().andNodeIdEqualTo(nodeId);
            nodeAttrMapper.deleteByExample(nodeAttrExample);
            //根据节点属性id级联删除相关节点触发条件
            nodeCondService.delNodeCondByNaIds(naIds);
        }
    }

    @Override
    public int delBySceneSn(String sceneSn) {
        NodeAttrExample nodeAttrExample = new NodeAttrExample();
        nodeAttrExample.createCriteria().andSceneSnEqualTo(sceneSn);
        return nodeAttrMapper.deleteByExample(nodeAttrExample);
    }
}
