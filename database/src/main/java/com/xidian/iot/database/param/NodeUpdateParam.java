package com.xidian.iot.database.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 节点更新参数
 * @author: Hansey
 * @date: 2020-09-13 09:40
 */
@Data
public class NodeUpdateParam {

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 描述
     */
    private String nodeDesc;

    /**
     * 节点映射实际地址
     */
    private String nodeMap;

}
