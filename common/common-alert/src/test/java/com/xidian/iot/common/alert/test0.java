package com.xidian.iot.common.alert;

import com.xidian.iot.common.alert.alertsender.AlertFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/application-alert.xml"})
public class test0 {
    @Resource
    AlertFactory alertFactory;
    @Test
    public void test1(){
//        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/application-alert.xml");
//        alertTest at = (alertTest) ac.getBean("alertTest");

        alertFactory.getAlert((byte)2,"654353294@qq.com","alert");
    }
}
