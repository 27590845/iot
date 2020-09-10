package com.xidian.iot.datacenter.service.chain;

import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.mongo.NodeData;
import lombok.Data;
import org.apache.commons.chain.impl.ContextBase;

import java.util.List;

/**
 * 上传数据上下文
 * 此上下文贯穿了整个上数的业务逻辑处理
 * 从控制层到责任链再到触发器，都需要用到此上下文可以说所有层获取的数据的上下传递，都靠这个对象来完成
 */

/**
 * @author mrl
 * @Title: BaseContext
 * @Package
 * @Description: copy from langyan
 * @date 2020/9/9 10:02 下午
 */
@Data
public abstract class BaseContext extends ContextBase {

    //上数数据的API KEY
    protected String apiKey;
    //网关的出厂序列号
    protected String sceneSn;
    //网关对应的场景
    protected Scene scene;
    //本次需要存储的节点数据
    protected List<NodeData> nodeDataList;
    //新添加的节点列表
    private List<Node> addedNodeList;
}
