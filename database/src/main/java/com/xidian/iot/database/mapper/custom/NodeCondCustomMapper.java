package com.xidian.iot.database.mapper.custom;

import com.xidian.iot.database.entity.NodeCond;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeCondCustomMapper
 * @Package
 * @Description: 自定义NodeCond数据库查询
 * @date 2020/9/14 7:27 下午
 */
public interface NodeCondCustomMapper {

    List<Long> getNcIdsAvlBySn(@Param("sceneSn") String sceneSn, @Param("nodeSn") String nodeSn);

    List<Long> getNcIdsBySn(@Param("sceneSn") String sceneSn, @Param("nodeSn") String nodeSn);

    List<Long> getNcIdsByNtId(@Param("ntId") Long ntId);

    int addBatch(@Param("ncs") List<NodeCond> nodeConds);

    List<Long> getNcIdsByNaIds(@Param("naIds")List<Long> naIds);

    int delBatchsByNcIds(@Param("ncIds")List<Long> ncIds);

    /**
     * 获取网关下所有的节点命令触发器
     * @param sceneSn
     * @return java.util.List<java.lang.Long>
     * */
    List<Long> getNcIdsBySceneSn(@Param("sceneSn") String sceneSn);

    int updateBatch(@Param("ncs")List<NodeCond> nodeConds);
}