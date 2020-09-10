package com.xidian.iot.datacenter.service;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.common.cache.RedisUtil;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.SceneExample;
import com.xidian.iot.database.mapper.SceneMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mrl
 * @Title: SceneService
 * @Package com.xidian.iot.datacenter.service
 * @Description: scene service
 * @date 2020/9/1 11:02 上午
 */
//@Transactional(rollbackFor=Exception.class)
@Service
public class SceneService {

    //用于手动添加缓存
    @Resource
    private RedisUtil redisUtil = null;

    @Resource
    private SceneMapper sceneMapper;

    @Cacheable(value="scenes")
    public List<Scene> getScene(int page, int limit){
        SceneExample sceneExample = new SceneExample();
        if(page>=0 && limit>0){
            PageHelper.startPage(page, limit);
        }
        return sceneMapper.selectByExample(sceneExample);
    }

    //redis命令行客户端使用hset的语法是:
    //  hset key field value
    //@Cacheable的value参数实际上就是hset命令的key字段, 而@Cacheable的key参数是hset的field字段,最后@Cacheable注解所在的函数返回值是hset命令的value字段.
    @Cacheable(value="scene",key="'sceneSn'+#sceneSn")
    public Scene getScene(String sceneSn){
        SceneExample sceneExample = new SceneExample();
        sceneExample.createCriteria().andSceneSnEqualTo(sceneSn);
        List<Scene> scenes = sceneMapper.selectByExample(sceneExample);
        if(scenes!=null && scenes.size()>0){
            return scenes.get(0);
        }else {
            return null;
        }
    }
}
