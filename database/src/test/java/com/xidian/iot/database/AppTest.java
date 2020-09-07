package com.xidian.iot.database;

import static org.junit.Assert.assertTrue;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.database.mapper.SceneMapper;
import com.xidian.iot.database.pojo.Scene;
import com.xidian.iot.database.pojo.SceneExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/application-mybatis-def.xml"})
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
}
