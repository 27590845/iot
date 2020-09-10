package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.param.SceneAddParam;

import java.util.List;

public interface SceneService {

    /**
    * 根据page和limit得到场景列表
    * */
    public List<Scene> getScene(String sceneSn, int page, int limit);

    /**
    * 根据场景ID获取场景
    * */
    Scene getSceneById(Long sceneId);

    /**
    * 测试生成Id
    * */
    public void testId();

    /**
    * 添加场景
    * */
    Scene addScene(SceneAddParam param);

    /**
    * 查询场景数量
    * */
    int countScene();
}
