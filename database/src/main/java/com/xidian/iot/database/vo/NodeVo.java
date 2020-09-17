package com.xidian.iot.database.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeAttr;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 返回给前端展示的node及其属性
 * @author: Hansey
 * @date: 2020-09-16 20:28
 */
@Data
@JsonIgnoreProperties(value = {"handler"})
public class NodeVo extends Node implements Serializable {

    public List<NodeAttr> nodeAttrList;

}
