package com.xidian.iot.databiz.service.impl;

import com.baidu.fsg.uid.UidGenerator;
import com.github.pagehelper.PageHelper;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.SceneExample;
import com.xidian.iot.database.mapper.SceneMapper;
import com.xidian.iot.database.param.SceneAddParam;
import com.xidian.iot.databiz.constants.EncodeType;
import com.xidian.iot.databiz.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mrl
 * @Title: SceneService
 * @Package com.xidian.iot.databiz.service
 * @Description: scene service
 * @date 2020/9/1 5:31 下午
 */
@Service
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private UidGenerator uidGenerator;

    @Override
    public List<Scene> getScene(String sceneSn, int page, int limit){
        SceneExample sceneExample = new SceneExample();
        sceneExample.createCriteria().andSceneSnEqualTo(sceneSn);
        if(page>=0 && limit>0){
            PageHelper.startPage(page, limit);
        }
        return sceneMapper.selectByExample(sceneExample);
    }

    @Cacheable(value="scene")
    @Override
    public Scene getSceneById(Long sceneId) {
        return sceneMapper.selectByPrimaryKey(sceneId);
    }

    @Override
    public void testId() {
        long uid = uidGenerator.getUID();
        System.out.println(uidGenerator.parseUID(uid));
    }

    @Override
    public Scene addScene(SceneAddParam param) {
        Scene scene = param.build();
        scene.setSceneId(uidGenerator.getUID());
        //补零操作、如果是6位也就是最多支持一百台。之后要修改。
        String sequence = String.format("%06d", countScene());
        //物联网唯一标示体系
        scene.setSceneSn(String.valueOf(EncodeType.EncodeGateway.getCode())+"866101022"+param.getUsageCode()+param.getCommCode()+sequence);
        sceneMapper.insertSelective(scene);
        return scene;
    }

    @Override
    public int countScene() {
        return (int)sceneMapper.countByExample(new SceneExample());
    }
}
