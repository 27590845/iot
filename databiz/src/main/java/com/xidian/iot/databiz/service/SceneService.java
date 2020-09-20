package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.database.param.SceneAddParam;
import com.xidian.iot.database.param.SceneUpdateParam;
import com.xidian.iot.database.vo.SceneVo;

import java.util.List;

public interface SceneService {

    /**
    * 根据page和limit得到场景列表
    * */
    public List<Scene> getScene(String sceneSn, int page, int limit);

    /**
     * 分页获取当前所有的场景号
     * @param page
     * @param limit
     * @return java.util.List<com.xidian.iot.database.entity.Scene>
     * */
    List<Scene> getScenes(int page, int limit);

    /**
     * 根据场景ID获取场景
     * */
    Scene getSceneById(Long sceneId);

    /**
     * 根据场景SN获取场景
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

    /**
     * 根据场景SN获取场景及其下属的NODE和NODE_ATTR
     * */
    SceneVo getSceneVoBySn(String sceneSn);

    /**
     * 得到一个网关下15分钟内上传的数据
     * @param sceneSn 网关sn
     * @return java.lang.Object
     * */
    List<NodeData> getLatestNodesData(String sceneSn);
}
