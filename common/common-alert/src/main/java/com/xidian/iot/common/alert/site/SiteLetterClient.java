package com.xidian.iot.common.alert.site;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.alert.AlertClient;
import com.xidian.iot.common.alert.AlertVo;
import com.xidian.iot.common.alert.util.JsonUtil;
import com.xidian.iot.common.mq.MqSender;

import javax.annotation.Resource;
import java.util.List;


/**
 * 站内信发送
 *
 * @author wmr
 */
public class SiteLetterClient implements AlertClient {

    /**
     * 消息队列访问接口
     */
    @Resource
    private MqSender mqSender;
    /**
     * 向消息队列发送站内信
     */
    public boolean send(AlertVo vo) {
        SiteLetterVo siteLetterVo = (SiteLetterVo) vo;
        // 添加站内信
        try{
            mqSender.sendSeriObjByte("SiteLetter", JsonUtil.toJson(siteLetterVo));
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void send(List<AlertVo> list) {
        if (null != list && list.size() > 0) {
            for (AlertVo alertVo : list) {
                send(alertVo);
            }
        }
    }

}
