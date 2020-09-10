package com.xidian.iot.database.mapper;

import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.SceneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SceneMapper {
    long countByExample(SceneExample example);

    int deleteByExample(SceneExample example);

    int deleteByPrimaryKey(Long sceneId);

    int insert(Scene record);

    int insertSelective(Scene record);

    List<Scene> selectByExample(SceneExample example);

    Scene selectByPrimaryKey(Long sceneId);

    int updateByExampleSelective(@Param("record") Scene record, @Param("example") SceneExample example);

    int updateByExample(@Param("record") Scene record, @Param("example") SceneExample example);

    int updateByPrimaryKeySelective(Scene record);

    int updateByPrimaryKey(Scene record);
}