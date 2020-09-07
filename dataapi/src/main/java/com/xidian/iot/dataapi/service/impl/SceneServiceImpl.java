package com.xidian.iot.dataapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.database.mapper.SceneMapper;
import com.xidian.iot.database.pojo.Scene;
import com.xidian.iot.database.pojo.SceneExample;
import com.xidian.iot.dataapi.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mrl
 * @Title: SceneService
 * @Package com.xidian.iot.dataapi.service
 * @Description: scene service
 * @date 2020/9/1 5:31 下午
 */
@Service
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneMapper sceneMapper;

    @Override
    public List<Scene> getScene(String sceneSn, int page, int limit){
        SceneExample sceneExample = new SceneExample();
        sceneExample.createCriteria().andSceneSnEqualTo(sceneSn);
        if(page>=0 && limit>0){
            PageHelper.startPage(page, limit);
        }
        return sceneMapper.selectByExample(sceneExample);
    }

    @Override
    public Scene getSceneById(Integer sceneId) {
        return null;
    }
}
