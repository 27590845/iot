package com.xidian.iot.databiz;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.SceneExample;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.database.mapper.SceneMapper;
import com.xidian.iot.database.mapper.custom.NodeAttrCustomMapper;
import com.xidian.iot.databiz.service.NodeAttrService;
import com.xidian.iot.databiz.service.NodeCondService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * @author mrl
 * @Title: AppTest
 * @Package
 * @Description:
 * @date 2020/9/13 3:31 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/application-databiz.xml"})
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Resource
    SceneMapper sceneMapper;

    @Test
    public void getScene(){
        PageHelper.startPage(0,0);
        List<Scene> scenes = sceneMapper.selectByExample(new SceneExample());
        System.out.println(scenes.size());
    }

    @Resource
    NodeCondService nodeCondService;

    @Test
    public void getNodeCond() throws InterruptedException {
        for(int i=0;i<100;i++){
            NodeCondExt nodeCondExt = nodeCondService.getNodeCondExtById((long) 234567);
            nodeCondExt.setCurrentFitCount(nodeCondExt.getCurrentFitCount()+1);
            nodeCondService.changeNodeCondExt(nodeCondExt);
            Thread.sleep(1000);
        }
    }

    @Resource
    NodeAttrCustomMapper nodeAttrCustomMapper;

    @Test
    public void getNaMap(){
        List<Map<String, Object>> naSimples = nodeAttrCustomMapper.getNaSimplesBySn("A18600", "K99990");
        Map<Long, String> naMap = naSimples.stream().collect(Collectors.toMap(m -> (Long)m.get("na_id"), m -> (String) m.get("na_key")));
        System.out.println(naMap);
    }
}
