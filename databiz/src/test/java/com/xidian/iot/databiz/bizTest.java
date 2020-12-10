package com.xidian.iot.databiz;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.database.entity.NodeAttrStd;
import com.xidian.iot.database.entity.NodeAttrStdExample;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.SceneExample;
import com.xidian.iot.database.mapper.NodeAttrStdMapper;
import com.xidian.iot.database.mapper.SceneMapper;
import com.xidian.iot.databiz.service.NodeAttrStdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

/**
 * @author: Hansey
 * @date: 2020-09-13 15:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/application-mybatis-dynamic.xml"})
public class bizTest {

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    private void assertTrue(boolean b) {
    }

    @Resource
    SceneMapper sceneMapper;

    @Test
    public void getScene(){
        List<Scene> scenes1 = sceneMapper.selectByExample(new SceneExample());
        System.out.println(scenes1.size());

        List<Scene> scenes2 = sceneMapper.selectByExample(new SceneExample());
        System.out.println(scenes2.size());
    }

    @Autowired
    private NodeAttrStdMapper nodeAttrStdMapper;
    @Test
    public void addNas(){
        NodeAttrStd nodeAttrStd = new NodeAttrStd();
        nodeAttrStd.setNasId(100000000l);
        nodeAttrStd.setNasKey("test");
        nodeAttrStd.setNasSym("o");
        nodeAttrStd.setNasUnit("o");
        nodeAttrStd.setNasDesc("测试");
        nodeAttrStdMapper.insertSelective(nodeAttrStd);
    }

    @Test
    public void getNas() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            List<NodeAttrStd> nodeAttrStds = null;
            List<Scene> scenes = null;
            try {
                nodeAttrStds = nodeAttrStdMapper.selectByExample(new NodeAttrStdExample());
                scenes = sceneMapper.selectByExample(new SceneExample());
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.print(nodeAttrStds==null?"null":nodeAttrStds.size()+"  ");
            System.out.println(scenes==null?"null":scenes.size());
            Thread.sleep(10000);
        }
    }
}
