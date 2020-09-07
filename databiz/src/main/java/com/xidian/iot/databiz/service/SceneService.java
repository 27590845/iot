package com.xidian.iot.databiz.service;

import com.xidian.iot.database.pojo.Scene;

import java.util.List;

public interface SceneService {

    /**
    * 根据page和limit得到场景列表
    * */
    public List<Scene> getScene(String sceneSn, int page, int limit);

    /**
    * 根据场景ID获取场景
    * */
    Scene getSceneById(Integer sceneId);
}
