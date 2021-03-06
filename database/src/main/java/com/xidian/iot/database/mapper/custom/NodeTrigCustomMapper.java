package com.xidian.iot.database.mapper.custom;

import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.database.param.NodeCondParam;
import com.xidian.iot.database.param.NodeTrigParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeTrigCustomMapper
 * @Package
 * @Description: 自定义Nodetrig数据库操作接口
 * @date 2020/9/21 9:35 上午
 */
public interface NodeTrigCustomMapper {

    /**
     * 通过{@link com.xidian.iot.database.entity.NodeActCmd}.ncId列表获取NodeTrig.ntId列表
     * @param ncIds
     * @return
     */
    List<Long> getNtIdsByNcIds(@Param("ncIds") List<Long> ncIds);

    /**
     * 根据ntId获取该联动规则下所有的相关信息
     * @param ntId
     * @return com.xidian.iot.database.param.NodeTrigParam
     * */
    NodeTrigParam getNodeTrigParamByNtId(@Param("ntId")Long ntId);

    /**
     * 获取所有的规则引擎NodeTrigParam
     * @return List<NodeTrigParam>
     */
    List<NodeTrigParam> getNodeTrigParam();

    /**
     *  由ntId更新nodeTrig、除了截止时间以外、为空则不更新。
     * @param nodeTrig
     * @return int
     * */
    int updateByNTIdSelective(NodeTrig nodeTrig);
}
