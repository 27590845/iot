package com.xidian.iot.database.mapper.custom;

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
     * @return int
     * */
    String maxSceneSn(@Param(value = "sceneSnPre") String sceneSnPre);
}
