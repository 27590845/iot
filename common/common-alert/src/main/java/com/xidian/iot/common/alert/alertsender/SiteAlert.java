package com.xidian.iot.common.alert.alertsender;

import com.xidian.iot.common.alert.site.SiteLetterClient;
import com.xidian.iot.common.alert.site.SiteLetterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 站内信报警对象
 * @author wmr
 *
 */
@Slf4j
@Component
public class SiteAlert implements Alert {
    /**
     * 站内信客户端
     */
    @Resource
    private SiteLetterClient siteLetterClient;
    /**
     * 设置报警站内信
     *
     * @param destination 报警的接收人
     * @param content 报警文本
     * @return 报警信息对象
     */
    @Override
    public SiteLetterVo set(String destination,String content){
        SiteLetterVo siteLetterVo = new SiteLetterVo();
        siteLetterVo.setCreateTime(new Date());// 创建时间
        siteLetterVo.setIsRead((short)0);// 未读
        siteLetterVo.setCurrentValue(content);// 触发时的当前值&&报警内容
        return siteLetterVo;
    }

    /**
     * 发送报警站内信
     *
     * @param destination 报警的接收人
     * @param content 报警文本
     */
    @Override
    public void send(String destination,String content){
        SiteLetterVo siteLetterVo = set(destination,content);
        siteLetterClient.send(siteLetterVo);
        log.info("send siteletter:"+destination+" "+content);
    }
}