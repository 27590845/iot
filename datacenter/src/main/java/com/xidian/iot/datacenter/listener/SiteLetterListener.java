package com.xidian.iot.datacenter.listener;

import com.xidian.iot.common.mq.activemq.listener.JmsConsumerListener;
import com.xidian.iot.common.util.JsonUtil;
import com.xidian.iot.database.entity.SiteLetter;
import com.xidian.iot.databiz.service.SiteLetterService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wmr
 * @Title: SiteLetterListener
 * @Package
 * @Description: 监听mq存储站内信
 * @date 2020/9/21 7:24 下午
 */
public class SiteLetterListener extends JmsConsumerListener {
    @Autowired
    private SiteLetterService siteLetterService;

    @Override
    public void onMessage(Object topicName,Object message){
        System.out.printf("topicName = %s, message = %s\n", topicName, message);
        String content = (String) message;
        SiteLetter siteLetter = new SiteLetter();
        siteLetter = JsonUtil.toObject(content,siteLetter.getClass());
        siteLetterService.addSiteLetter(siteLetter);
    }
}
