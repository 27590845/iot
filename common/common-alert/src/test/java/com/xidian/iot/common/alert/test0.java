package com.xidian.iot.common.alert;

import com.xidian.iot.database.entity.NodeActAlert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test0 {
    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/application.xml");
        alertTest at = (alertTest) ac.getBean("alertTest");

        try{
            at.alert1();
        }
        catch (Exception e){
            System.out.println("dddd");
        }
    }
}
