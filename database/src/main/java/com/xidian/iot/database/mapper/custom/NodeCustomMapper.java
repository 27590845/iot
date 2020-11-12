package com.xidian.iot.database.mapper.custom;

import com.xidian.iot.database.vo.NodeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义节点mapper
 * @author: Hansey
 * @date: 2020-09-11 15:44
 */
public interface NodeCustomMapper {
    /**
     * 根据nodeId获取NodeVo
     * @param nodeId 节点Id
     * @return com.xidian.iot.database.vo.NodeVo
     * */
    NodeVo getNodeVoByNodeId(@Param("nodeId") Long nodeId);

    /**
     * 根据sceneId获取场景下所有节点及其属性
     * @param sceneId
     * @return java.util.List<com.xidian.iot.database.vo.NodeVo>
     * */
    List<NodeVo> getNodeVosBySceneId(@Param("sceneId") Long sceneId);

    /**
     * 得到该网关下最后添加的nodeSn
     * @param sceneId
     * @return java.lang.String
     * */
    String getSceneLastNodeSn(Long sceneId);
}
