package com.xidian.iot.common.mq.kafka.constant;

/**
 * @author mrl
 * @Title: KafkaMsgConstant
 * @Package com.xidian.iot.common.mq.kafka.valid
 * @Description:
 * @date 2020/9/8 4:56 下午
 */
public class KafkaMsgConstant {

    public static final String SUCCESS_CODE = "00000";
    public static final String SUCCESS_MES = "成功";

    /*kakfa-code*/
    public static final String KAFKA_SEND_ERROR_CODE = "30001";
    public static final String KAFKA_NO_RESULT_CODE = "30002";
    public static final String KAFKA_NO_OFFSET_CODE = "30003";

    /*kakfa-mes*/
    public static final String KAFKA_SEND_ERROR_MES = "发送消息超时";
    public static final String KAFKA_NO_RESULT_MES = "未查询到返回结果";
    public static final String KAFKA_NO_OFFSET_MES = "未查到返回数据的offset";
}
