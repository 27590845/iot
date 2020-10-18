package com.xidian.iot.common.alert.alertsender;

/**
 * 警报工厂
 * @author wmr
 */
public class AlertFactory{
    public static void getAlert(byte alertType,String destination,String content){
        Alert alert = null;
        switch(alertType){
            case 1:{
                alert = new SMSAlert();
                break;
            }
            case 2:{
                alert = new EmailAlert();
                break;
            }
            case 3:{
                alert = new SiteAlert();
                break;
            }
        }
        alert.send(destination,content);
    }
}