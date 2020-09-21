package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.param.NodeAttrParam;
import com.xidian.iot.database.param.NodeAttrUpdateParam;

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
     * @param naId
     * @return
     */
    NodeAttr getNodeAttrById(Long naId);

    /**
     * 根据 sceneSn、nodeSn添加节点属性
     * @param sceneSn 网关sn
     * @param nodeSn 节点sn
     * @param nodeAttrs 新增的节点属性列表
     * @return com.xidian.iot.database.entity.NodeAttr
     * */
    List<NodeAttr> addNodeAttr(String sceneSn, String nodeSn, List<NodeAttrParam> nodeAttrs);

    /**
     *  根据 sceneSn、nodeSn、naKeys删除节点属性
     * @param sceneSn 网关sn
     * @param nodeSn 节点sn
     * @param naKeyLists 多个节点属性标示
     * @return void
     * */
    void delNodeAttrs(String sceneSn, String nodeSn, List<String> naKeyLists);

    /**
     * 根据 sceneSn、nodeSn、naKey获取节点属性
     * @param sceneSn 网关sn
     * @param nodeSn 节点sn
     * @param naKey 节点属性标示
     * @return com.xidian.iot.database.entity.NodeAttr
     * */
    NodeAttr getNodeAttr(String sceneSn, String nodeSn, String naKey);

    /**
     *
     * @param naId 节点属性Id
     * @param param 更新的属性
     * @return void
     * */
    void updateNodeAttr(Long naId, NodeAttrUpdateParam param);

    /**
     * 根据sceneSn和nodeSn获取NodeAttr的简要信息，key代表naId，value代表naKey
     * @param sceneSn
     * @param nodeSn
     * @return
     */
    Map<String, String> getNaMapBySn(String sceneSn, String nodeSn);
}
