package com.xidian.iot.databiz.service.impl;

import com.baidu.fsg.uid.UidGenerator;
import com.github.pagehelper.PageHelper;
import com.xidian.iot.common.constants.ExceptionEnum;
import com.xidian.iot.common.util.Assert;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.SceneExample;
import com.xidian.iot.database.mapper.SceneMapper;
//import com.xidian.iot.database.mapper.custom.SceneCustomMapper;
import com.xidian.iot.database.param.SceneAddParam;
import com.xidian.iot.database.param.SceneUpdateParam;
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
//    @Autowired
//    private SceneCustomMapper sceneCustomMapper;
    @Autowired
    private UidGenerator uidGenerator;

    @Override
    public List<Scene> getScene(String sceneSn, int page, int limit) {
        SceneExample example = new SceneExample();
        example.createCriteria().andSceneSnEqualTo(sceneSn);
        if (page >= 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        return sceneMapper.selectByExample(example);
    }

    @Cacheable(value = "scene")
    @Override
    public List<Scene> getAllScenes(int page, int limit) {
        if (page >= 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        return sceneMapper.selectByExample(new SceneExample());
    }

    @Cacheable(value = "scene")
    @Override
    public Scene getSceneById(Long sceneId) {
        return sceneMapper.selectByPrimaryKey(sceneId);
    }

    @Override
    public Scene getSceneBySn(String sceneSn) {
        SceneExample sceneExample = new SceneExample();
        sceneExample.createCriteria().andSceneSnEqualTo(sceneSn);
        List<Scene> scenes = sceneMapper.selectByExample(sceneExample);
        Assert.isTrue(scenes.size() > 0, ExceptionEnum.SCENE_NOT_EXIST);
        return scenes.get(0);
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
        String sceneSnPre = EncodeType.EncodeGateway.getCode() + "866101022";
        //补零操作、如果是6位也就是最多支持一百台。同一个区域的第几台。
//        String sequence = String.format("%06d", sceneCustomMapper.countDomains(sceneSnPre) + 1);
        //物联网唯一标示体系
//        scene.setSceneSn(sceneSnPre + param.getUsageCode() + param.getCommCode() + sequence);
        sceneMapper.insertSelective(scene);
        return scene;
    }

    @Override
    public int countScene() {
        return (int) sceneMapper.countByExample(new SceneExample());
    }

    @Override
    public void delScene(String sceneSn) {
        SceneExample example = new SceneExample();
        example.createCriteria().andSceneSnEqualTo(sceneSn);
        Assert.isTrue(sceneMapper.deleteByExample(example) > 0, ExceptionEnum.SCENE_NOT_EXIST);
    }

    @Override
    public void updateScene(String sceneSn, SceneUpdateParam param) {
        Scene scene = param.build(getSceneBySn(sceneSn).getSceneId());
        sceneMapper.updateByPrimaryKeySelective(scene);
    }

}
