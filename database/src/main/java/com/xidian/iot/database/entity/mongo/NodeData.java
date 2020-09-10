package com.xidian.iot.database.entity.mongo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mrl
 * @Title: NodeData
 * @Package
 * @Description: 对应mongo数据库传感器数据的数据结构
 * @date 2020/9/9 10:17 下午
 */
@Data
public class NodeData implements Serializable {

    /**
     * 网关序列号
     */
    private String sceneSn;
    /**
     * 节点序列号
     */
    private String nodeSn;
    /**
     * 上传时间点
     */
    private Long at;
    /**
     * 采集数据
     */
    private Map<String, Object> data;

    /**
     * 增加数据
     *
     * @param key 键
     * @param value 值
     */
    public void put(String key, Object value) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        data.put(key, value);
    }

    /**
     * 获取数据
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return data.get(key);
    }
}
