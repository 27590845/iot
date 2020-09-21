package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.param.NodeAddParam;
import com.xidian.iot.database.param.NodeUpdateParam;
import com.xidian.iot.database.vo.NodeVo;

/**
 * @author: Hansey
 * @date: 2020-09-10 21:45
 */
public interface NodeService {

    /** 
     * 添加节点
     * @param param
     * @return com.xidian.iot.database.entity.Node
     * */ 
    Node addNode(NodeAddParam param);

    /**
     * 根据sceneSn和nodeSn删除node
     * @param sceneSn
     * @param nodeSn
     * @return void
     * */
    void delNode(String sceneSn, String nodeSn);

    /**
     * 根据sceneSn和nodeSn更改node信息
     * @param sceneSn
     * @param nodeSn
     * @param param
     * @return void
     * */
    Node updateNode(String sceneSn, String nodeSn, NodeUpdateParam param);

    /**
     * 根据sceneSn和nodeSn获取node
     * @param sceneSn
     * @param nodeSn
     * @return
     */
    Node getNodeBySn(String sceneSn, String nodeSn);

    /**
     *
     * @param sceneSn
     * @param nodeSn
     * @return com.xidian.iot.database.vo.NodeVo
     * */
    NodeVo getNodeVoBySn(String sceneSn, String nodeSn);
}
