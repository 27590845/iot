package com.xidian;

import com.xidian.iot.dataapi.Application;
//import com.xidian.iot.dataapi.config.BizConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Springboot测试类
 * @author: Hansey
 * @date: 2021-01-20 16:33
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes ={BizConfig.class})
public class ApplicationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test1() {
        Object myPerson = applicationContext.getBean("NodeService");
        System.out.println(myPerson); // Person{name='fsx', age=18}
    }
}
