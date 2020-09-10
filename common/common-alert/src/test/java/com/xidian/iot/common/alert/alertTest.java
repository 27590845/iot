package com.xidian.iot.common.alert;

import com.xidian.iot.common.alert.aspect.AlertType;
import com.xidian.iot.common.alert.aspect.SendAlert;
import com.xidian.iot.database.entity.NodeActAlert;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class alertTest {

    @SendAlert(alertType = AlertType.EMAIL, destination = "123456@qq.com", content = "报警信息")
    public void alert1(){

        System.out.println(1 / 0);
    }
}
