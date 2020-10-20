package com.xidian.iot.common.mq.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.mq.MqSender;
import com.xidian.iot.common.mq.kafka.constant.KafkaMsgConstant;
import com.xidian.iot.common.util.JsonUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * @author mrl
 * @Title: KafkaSender
 * @Package com.xidian.iot.common.mq
 * @Description: send message to kafka mq, {@link org.apache.kafka.clients.producer.ProducerRecord}
 *  从功能上看，kafka与activemq相比，多了分区的概念、增加了发送回调的功能
 * @date 2020/9/8 4:53 下午
 */
@Slf4j
public class KafkaSender implements MqSender {

    @Setter
    private KafkaTemplate strKafkaTemplate;

    @Setter
    private KafkaTemplate objKafkaTemplate;

    @Setter
    private KafkaTemplate byteKafkaTemplate;

    /**
     * kafka发送消息完全模板
     * @param topic 主题
     * @param value    messageValue
     * @param partitionNum 分区数 ,分区数必须大于0, 否则不使用分区
     * @param role 角色:bbc app erp...
     */
    public Map<String,Object> sndMesForTemplate(String topic, Object value, Integer partitionNum, String role, KafkaTemplate kafkaTemplate) throws JsonProcessingException {
        String key = role+"-"+value.hashCode();
        if(partitionNum>0){
            //表示使用分区
            int partitionIndex = getPartitionIndex(key, partitionNum);
            ListenableFuture<SendResult> result = kafkaTemplate.send(topic, partitionIndex, key, value);
            Map<String,Object> res = checkProRecord(result);
            return res;
        }else{
            ListenableFuture<SendResult> result = kafkaTemplate.send(topic, key, value);
            Map<String,Object> res = checkProRecord(result);
            return res;
        }
    }

    /**
     * 根据key值获取分区索引
     * @param key
     * @param partitionNum
     * @return
     */
    private int getPartitionIndex(String key, int partitionNum){
        if (key == null) {
            Random random = new Random();
            return random.nextInt(partitionNum);
        }
        else {
            int result = Math.abs(key.hashCode())%partitionNum;
            return result;
        }
    }

    /**
     * 检查发送返回结果record
     * @param res
     * @return
     */
    @SuppressWarnings("rawtypes")
    private Map<String,Object> checkProRecord(ListenableFuture<SendResult> res){
        Map<String,Object> m = new HashMap<String,Object>();
        if(res!=null){
            try {
                SendResult r = res.get();//检查result结果集
                /*检查recordMetadata的offset数据，不检查producerRecord*/
                Long offsetIndex = r.getRecordMetadata().offset();
                if(offsetIndex!=null && offsetIndex>=0){
                    m.put("code", KafkaMsgConstant.SUCCESS_CODE);
                    m.put("message", KafkaMsgConstant.SUCCESS_MES);
                    return m;
                }else{
                    m.put("code", KafkaMsgConstant.KAFKA_NO_OFFSET_CODE);
                    m.put("message", KafkaMsgConstant.KAFKA_NO_OFFSET_MES);
                    return m;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                m.put("code", KafkaMsgConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message", KafkaMsgConstant.KAFKA_SEND_ERROR_MES);
                return m;
            } catch (ExecutionException e) {
                e.printStackTrace();
                m.put("code", KafkaMsgConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message", KafkaMsgConstant.KAFKA_SEND_ERROR_MES);
                return m;
            }
        }else{
            m.put("code", KafkaMsgConstant.KAFKA_NO_RESULT_CODE);
            m.put("message", KafkaMsgConstant.KAFKA_NO_RESULT_MES);
            return m;
        }
    }

//    @Override
//    public void sendSeriObj(String topicName, Serializable serializedObj) throws JsonProcessingException {
//        Map<String,Object> res = sndMesForTemplate(topicName, serializedObj, 0, "KafkaSender", objKafkaTemplate);
//        log.debug(res.toString());
//    }

//    @Override
//    public void sendSeriObjByte(String topicName, String jsonStr) throws JsonProcessingException {
//        Map<String,Object> res = sndMesForTemplate(topicName, jsonStr.getBytes(), 0, "KafkaSender", byteKafkaTemplate);
//        log.debug(res.toString());
//    }

    /**
     * 好像kafkaTemplate只能发送string
     */
    @Override
    public void sendTopic(String topicName, String message) throws JsonProcessingException {
        Map<String,Object> res = sndMesForTemplate(topicName, message, 0, "KafkaSender", strKafkaTemplate);
        log.debug(res.toString());
    }

    @Override
    public void sendQueue(String topicName, String message) throws JsonProcessingException {
        sendTopic(topicName, message);
    }

}
