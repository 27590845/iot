package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.param.NodeAttrParam;

import java.util.List;
import java.util.Map;

/**
 * @author mrl
 * @Title: NodeAttrService
 * @Package
 * @Description: NodeAttr 相关服务
 * @date 2020/9/11 7:54 下午
 */
public interface NodeAttrService {

    /**
     * 根据naId获取NodeAttr
     *
     * @param naId
     * @return
     */
    NodeAttr getNodeAttrById(Long naId);

    /**
     * 添加节点属性
     *
     * @param sceneSn         网关sn
     * @param nodeSn          节点sn
     * @param nodeId          节点Id
     * @param nodeAttrParamss 新增的节点属性列表
     * @return java.util.List<com.xidian.iot.database.entity.NodeAttr>
     */
    List<NodeAttr> addNodeAttr(String sceneSn, String nodeSn, Long nodeId, List<NodeAttrParam> nodeAttrParamss);

    /**
     * 验证添加的nodeAttrs是否存在重复key
     *
     * @param nodeAttrs
     * @return void
     */
    void checkReptAttrKeys(List<NodeAttrParam> nodeAttrs);

    /**
     * 根据节点的Id验证、输入的nodeAttr的keys是否已经存在
     * 如果存在直接抛出错误
     * @param nodeId
     * @param nodeAttrParams
     * @return void
     * */
    void checkExistAttrKeys(Long nodeId, List<NodeAttrParam> nodeAttrParams);

    /**
     * 根据 sceneSn、nodeSn、naKeys删除节点属性
     *
     * @param sceneSn    网关sn
     * @param nodeSn     节点sn
     * @param naKeyLists 多个节点属性标示
     * @return void
     */
    void delNodeAttrs(String sceneSn, String nodeSn, List<String> naKeyLists);

    /**
     * 根据 sceneSn、nodeSn、naKey获取节点属性
     *
     * @param sceneSn 网关sn
     * @param nodeSn  节点sn
     * @param naKey   节点属性标示
     * @return com.xidian.iot.database.entity.NodeAttr
     */
    NodeAttr getNodeAttr(String sceneSn, String nodeSn, String naKey);

    /**
     * 根据naId查询并修改nodeAttr
     * @param naId  节点属性Id
     * @param param 更新的属性
     * @return void
     */
    void updateNodeAttr(Long naId, NodeAttrParam param);

    /**
     * 根据sn号+naKey查询并修改nodeAttr
     * @param sceneSn
     * @param nodeSn
     * @param param
     */
    void updateNodeAttr(String sceneSn, String nodeSn, String naKey, NodeAttrParam param);

    /**
     * 根据sceneSn和nodeSn获取NodeAttr的简要信息，key代表naId，value代表naKey
     *
     * @param sceneSn
     * @param nodeSn
     * @return
     */
    Map<String, String> getNaMapBySn(String sceneSn, String nodeSn);

    /**
     * 根据节点Id删除节点下所有的属性
     * @param nodeId 节点Id
     * @return void
     * */
    void deleteByNodeId(Long nodeId);
}
