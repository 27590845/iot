package com.xidian.iot.dataviewer.service;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.database.mapper.SceneMapper;
import com.xidian.iot.database.pojo.Scene;
import com.xidian.iot.database.pojo.SceneExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mrl
 * @Title: SceneService
 * @Package com.xidian.iot.dataviewer.service
 * @Description: scene service
 * @date 2020/9/1 5:31 下午
 */
@Service
public class SceneService {

    @Autowired
    private SceneMapper sceneMapper;

    public List<Scene> getScene(String sceneSn, int page, int limit){
        SceneExample sceneExample = new SceneExample();
        sceneExample.createCriteria().andSceneSnEqualTo(sceneSn);
        if(page>=0 && limit>0){
            PageHelper.startPage(page, limit);
        }
        return sceneMapper.selectByExample(sceneExample);
    }
}
