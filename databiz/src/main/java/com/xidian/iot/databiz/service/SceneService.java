package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.param.SceneAddParam;
import com.xidian.iot.database.param.SceneUpdateParam;

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
     * 根据场景SN获取场景及其下属的NODE和NODE_ATTR
     * */
    Scene getSceneBySn(String sceneSn);

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

    /**
    * 根据sceneSn删除场景
    * */
    void delScene(String sceneSn);

    /**
     * 更新scene
     * */
    void updateScene(String sceneSn, SceneUpdateParam param);
}
