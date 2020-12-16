package com.xidian.iot.database.mapper.custom;

import com.xidian.iot.database.vo.SceneVo;
import org.apache.ibatis.annotations.Param;

/**
 * 自定义scene mapper
 * @author: Hansey
 * @date: 2020-09-11 15:47
 */
public interface SceneCustomMapper {

    /**
     * 找出同一个域下最大的scene
     * @param sceneSnPre sceneSn的前10为标示域
     * @return String
     * */
    String maxSceneSn(@Param(value = "sceneSnPre") String sceneSnPre);

    /**
     * 根据sceneSn获取场景及其下属node和node_attr
     * 这里是分步及时查询
     * @param sceneSn 场景SN
     * @return com.xidian.iot.database.vo.SceneVo
     * */
    SceneVo getSceneVoBySn(@Param(value = "sceneSn")String sceneSn);

    /**
     * 同上个方法实现相同功能
     * 这里是联合查询、与上个方法对比效率、当数据大的时候效率又会如何呢？
     * 联合查询主要就是将压力分给了数据库、减轻了多次使用连接池请求的网络开销
     * @param sceneSn
     * @return com.xidian.iot.database.vo.SceneVo
     * */
    SceneVo getSceneVoBySnJoin(@Param(value = "sceneSn")String sceneSn);
}
