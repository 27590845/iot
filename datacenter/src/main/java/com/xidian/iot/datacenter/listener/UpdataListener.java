package com.xidian.iot.datacenter.listener;

import com.xidian.iot.common.mq.kafka.listener.KafkaConsumerListener;
import com.xidian.iot.common.util.JsonUtil;
import com.xidian.iot.common.util.StringUtil;
import com.xidian.iot.datacenter.constant.ErrorCode;
import com.xidian.iot.datacenter.exception.AbortChainException;
import com.xidian.iot.datacenter.service.chain.BaseContext;
import com.xidian.iot.datacenter.service.chain.json.JsonDataContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Chain;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @author mrl
 * @Title: UpdataListener
 * @Package
 * @Description: 监听mq上传数据
 * @date 2020/9/10 11:42 上午
 */
@Slf4j
public class UpdataListener extends KafkaConsumerListener {

    //json处理链
    @Resource
    private Chain jsonChain;

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
            // 约定用 prefix.sceneSn 作为订阅主题？
            context.setSceneSn(getSceneSnFromTopicName((String) topicName));
            context.setJsonData(jsonData);
            executeChain(jsonChain, context);
        } catch (AbortChainException e) {
            e.printStackTrace();
        }
    }

    private static String getSceneSnFromTopicName(String topicName){
        String res = null;
        if(StringUtils.isNotEmpty(topicName)){
            res = topicName.substring(topicName.lastIndexOf('.')+1);
        }
        return res;
    }

    /**
     * 执行责任链，并捕获异常
     *
     * @param chain 责任链
     * @param context 上下文
     * @return 响应对象
     */
    private void executeChain(Chain chain, BaseContext context) {
        try {
            chain.execute(context);
        } catch (AbortChainException ex) {
            log.debug("[{}]-[{}],", ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            log.error("......system happend error.......", ex);
        }
    }
}
