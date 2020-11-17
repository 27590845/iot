package com.xidian.iot.databiz.service.impl;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.common.util.constants.ExceptionEnum;
import com.xidian.iot.common.util.Assert;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.SceneExample;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.database.mapper.SceneMapper;
import com.xidian.iot.database.mapper.custom.NodeCustomMapper;
import com.xidian.iot.database.mapper.custom.SceneCustomMapper;
import com.xidian.iot.database.param.SceneAddParam;
import com.xidian.iot.database.param.SceneUpdateParam;
import com.xidian.iot.database.vo.NodeVo;
import com.xidian.iot.database.vo.SceneVo;
import com.xidian.iot.databiz.constants.EncodeType;
import com.xidian.iot.databiz.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author mrl
 * @Title: SceneService
 * @Package com.xidian.iot.databiz.service
 * @Description: scene service
 * @date 2020/9/1 5:31 下午
 */
@Service
@Slf4j
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private SceneCustomMapper sceneCustomMapper;
    @Autowired
    private NodeCustomMapper nodeCustomMapper;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private NodeAttrService nodeAttrService;
    @Autowired
    private NodeCmdService nodeCmdService;
    @Autowired
    private NodeCondService nodeCondService;
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

    @Override
    public List<Scene> getScenes(int page, int limit) {
        if (page >= 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        return sceneMapper.selectByExample(new SceneExample());
    }

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
        //物联网唯一标示体系
        if(StringUtils.isBlank(scene.getSceneSn())) {
            String sceneSnPre = EncodeType.EncodeGateway.getCode() + "866101022";
            //补零操作、如果是6位也就是最多支持一百台。同一个区域的第几台。
            String sequence = String.format("%06d", Integer.valueOf(sceneCustomMapper.maxSceneSn(sceneSnPre)) + 1);
            scene.setSceneSn(sceneSnPre + param.getUsageCode() + param.getCommCode() + sequence);
        }
        Assert.isFalse(isSceneExistBySn(param.getSceneSn()), ExceptionEnum.SCENE_ALREADY_EXIST);
        scene.setSceneId(uidGenerator.getUID());
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
        //级联删除node
        nodeService.delNodesBySceneSn(sceneSn);
        //级联删除node_attr 节点属性
        nodeAttrService.delBySceneSn(sceneSn);
        //级联删除node_cmd、同时删除相关的节点触发相关条件node_atc_cmd
        nodeCmdService.delBySceneSn(sceneSn);
        //级联删除node_cond
        nodeCondService.delNodeCondBySceneSn(sceneSn);
    }

    @Override
    public void updateScene(String sceneSn, SceneUpdateParam param) {
        Scene scene = param.build(getSceneBySn(sceneSn).getSceneId());
        sceneMapper.updateByPrimaryKeySelective(scene);
    }

    @Override
    public SceneVo getSceneVoBySn(String sceneSn) {
//        log.info(String.valueOf(System.currentTimeMillis()));
        //考虑到之后分表分库所有的查询不使用联合查询
        SceneVo sceneVo1 = sceneCustomMapper.getSceneVoBySn(sceneSn);
//        log.info(String.valueOf(System.currentTimeMillis()));
//        SceneVo sceneVo2 = sceneCustomMapper.getSceneVoBySnJoin(sceneSn);
//        log.info(String.valueOf(System.currentTimeMillis()));
        Assert.isTrue(sceneVo1==null,ExceptionEnum.SCENE_NOT_EXIST );
        return sceneVo1;
    }

    @Override
    public List<NodeData> getLatestNodesData(String sceneSn) {
        //确定是否存在该节点
        Scene scene = getSceneBySn(sceneSn);
        List<NodeVo> nodeVos = nodeCustomMapper.getNodeVosBySceneId(scene.getSceneId());
        List<NodeData> nodeDataList = new ArrayList<>();
        nodeVos.forEach(nodeVo -> {
            NodeData nodeData = nodeService.getMongoCD(nodeVo.getSceneSn(), nodeVo.getNodeSn());
            if (!Objects.isNull(nodeData)) {
                nodeDataList.add(nodeData);
            }
        });
        return nodeDataList;
    }

    private boolean isSceneExistBySn(String sceneSn) {
        SceneExample sceneExample = new SceneExample();
        sceneExample.createCriteria().andSceneSnEqualTo(sceneSn);
        List<Scene> scenes = sceneMapper.selectByExample(sceneExample);
        return scenes!=null&&scenes.size()>0;
    }
}
