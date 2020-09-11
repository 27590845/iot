package com.xidian.iot.datacenter.service.chain.json;

import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.datacenter.service.chain.BaseContext;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author mrl
 * @Title: JsonDataContext
 * @Package
 * @Description: copy from langyan
 * @date 2020/9/9 10:28 下午
 */
@Data
public class JsonDataContext extends BaseContext {

    //上传数据中，主数据的 filed name
    public static final String MAIN_FILED = "datastreams";
    //上数数据中，节点数据的，sn的key
    public static final String NODE_SN_KEY = "sn";
    //上数数据中，节点数据的，at的key
    public static final String NODE_AT_KEY = "at";
    //JSON数据
    private String jsonData;
    //datastreams数据
    private String datastreams;
    //将datastreams字符串转换后的List
    private List<Map> datastreamsList;
    //违法的datastreams数据
    private List<Map> illegalDatastreamsList;
    //合法的datastreams数据
    private List<Map> legalDatastreamsList;
    //转换过的节点数据
    //private List<NodeData> conversionNodeDataList;
    //抛弃的节点数据                                                                                                                                   -
    private List<NodeData> discardNodeDataList;
    //新添加的节点属性列表
    private List<NodeAttr> addedNodeAttributeList;
}
