package com.xidian.iot.datacenter.listener;

import com.xidian.iot.common.mq.kafka.listener.KafkaConsumerListener;
import com.xidian.iot.common.util.JsonUtil;
import com.xidian.iot.common.util.StringUtil;
import com.xidian.iot.datacenter.constant.ErrorCode;
import com.xidian.iot.datacenter.exception.AbortChainException;
import com.xidian.iot.datacenter.service.chain.json.JsonDataContext;

/**
 * @author mrl
 * @Title: UpdataListener
 * @Package
 * @Description: 监听mq上传数据
 * @date 2020/9/10 11:42 上午
 */
public class UpdataListener extends KafkaConsumerListener {
    @Override
    public void onMessage(Object topicName, Object message) {
        System.out.printf("topicName = %s, message = %s\n", topicName, message);
        String jsonData = (String) message;
        try{
            // 当jsonData为空字符串（）
            if (StringUtil.isEmpty(jsonData)) {
                throw new AbortChainException(ErrorCode.ERR100030);
            }
            // 当json数据不合法
            if (!JsonUtil.validate(jsonData)) {
                throw new AbortChainException(ErrorCode.ERR100031);
            }
            // 设置到上下文中
            JsonDataContext context = new JsonDataContext();
            context.setJsonData(jsonData);
        } catch (AbortChainException e) {
            e.printStackTrace();
        }
    }
}
