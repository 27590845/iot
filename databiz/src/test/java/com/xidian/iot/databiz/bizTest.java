package com.xidian.iot.databiz;

import com.github.pagehelper.PageHelper;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.SceneExample;
import com.xidian.iot.database.mapper.SceneMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Hansey
 * @date: 2020-09-13 15:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/application-mybatis-def.xml"})
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
        PageHelper.startPage(0,0);
        List<Scene> scenes = sceneMapper.selectByExample(new SceneExample());
        System.out.println(scenes.size());
    }

}
