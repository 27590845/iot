package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.database.param.NodeAddParam;
import com.xidian.iot.database.param.NodeUpdateParam;
import com.xidian.iot.database.vo.NodeVo;

import java.util.List;

/**
 * @author: Hansey
 * @date: 2020-09-10 21:45
 */
public interface NodeService {

    /**
     * 添加节点
     *
     * @param param
     * @return com.xidian.iot.database.entity.Node
     */
    Node addNode(NodeAddParam param);

    /**
     * 根据sceneSn和nodeSn删除node
     *
     * @param sceneSn
     * @param nodeSn
     * @return void
     */
    void delNode(String sceneSn, String nodeSn);

    /**
     * 根据sceneSn和nodeSn更改node信息
     *
     * @param sceneSn
     * @param nodeSn
     * @param param
     * @return void
     */
    Node updateNode(String sceneSn, String nodeSn, NodeUpdateParam param);

    /**
     * 根据sceneSn和nodeSn获取node
     *
     * @param sceneSn
     * @param nodeSn
     * @return
     */
    Node getNodeBySn(String sceneSn, String nodeSn);

    /**
     * 根据网关sn和节点sn得到NodeVo
     *
     * @param sceneSn 网关sn
     * @param nodeSn  节点sn
     * @return com.xidian.iot.database.vo.NodeVo
     */
    NodeVo getNodeVoBySn(String sceneSn, String nodeSn);

    /**
     * getMongoCurrentData 得到当前的数据 也就是15分钟之内的数据
     * 从mongo数据库中得到该节点/网关 15分钟内最新的数据
     * 如果节点为空则是获取该网关下的15分钟内最新数据
     *
     * @param sceneSn 网关sn
     * @param nodeSn  节点sn
     * @return com.xidian.iot.database.entity.custom.NodeData
     */
    NodeData getMongoCD(String sceneSn, String nodeSn);

    /**
     * getMongoLatestData 得到该节点最后一次上传的数据
     * 从mongo数据库中得到该节点/网关
     *
     * @param sceneSn 网关sn
     * @param nodeSn  节点sn
     * @return com.xidian.iot.database.entity.mongo.NodeData
     */
    NodeData getMongoLD(String sceneSn, String nodeSn);

    /**
     * 获取一个节点的历史数据
     * @param sceneSn 网关sn
     * @param nodeSn 节点sn
     * @param st 起始时间
     * @param et 结束时间
     * @return java.util.List<com.xidian.iot.database.entity.mongo.NodeData>
     * */
    List<NodeData> getMongoData(String sceneSn, String nodeSn, Long st, Long et);
}
