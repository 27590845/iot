package com.xidian.iot.common.alert.alertsender;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 警报工厂
 * @author wmr
 */
@Component
public class AlertFactory{
    @Resource
    EmailAlert emailAlert;
    @Resource
    SiteAlert siteAlert;
    @Resource
    SMSAlert smsAlert;
    public void getAlert(byte alertType,String destination,String content){
        Alert alert = null;
        switch(alertType){
            case 1:{
                alert = smsAlert;
                break;
            }
            case 2:{
                alert = emailAlert;
                break;
            }
            case 3:{
                alert = siteAlert;
                break;
            }
        }
        alert.send(destination,content);
    }
}