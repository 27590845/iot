package com.xidian.iot.datacenter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.mq.MqSender;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.databiz.service.NodeAttrService;
import com.xidian.iot.databiz.service.NodeCondService;
import com.xidian.iot.datacenter.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author mrl
 * @Title: AppTest
 * @Package
 * @Description:
 * @date 2020/9/11 10:26 下午
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/application-context.xml"})
public class AppTest {

    final static String sceneSn = "186610102211000001";
    final static String nodeSn = "000001";
    final static String topic = "hello_topic."+sceneSn;

    @Resource
    MqSender mqSender;

    @Test
    public void appTest() throws JsonProcessingException, InterruptedException {
//        String msg = "{\"datastreams\":[{\"TVOC\":69,\"hum\":54.0,\"at\":1597737850021,\"pm2p5\":33,\"co2\":509,\"pm10\":59,\"sn\":\""+nodeSn+"\",\"ch20\":19,\"tem\":32.0}]}";
        String msg = "{\"datastreams\":[{\"tem1\":110,\"tem2\":44.0,\"at\":1600570048,\"sn\":\""+nodeSn+"\"}]}";
        for(int i=0; i< 1000; i++){
            mqSender.send(topic, msg);
            Thread.sleep(10000);
        }
    }

    @Resource
    CommonService commonService;

    @Test
    public void getNodeCondExtByNtId() {
        Long ntId = 123456L;
        List<NodeCondExt> nodeCondExts = commonService.getNodeCondExts(ntId);
        System.out.println(nodeCondExts);
    }

    @Test
    public void getNodeCondExts(){
        String sceneSn="A18600", nodeSn="K99990";
        Set<String> naKeys = new HashSet<>();
        naKeys.add("tem1");
        List<NodeCondExt> nodeCondExts = commonService.getNodeCondExts(sceneSn, nodeSn, naKeys);
        System.out.println(nodeCondExts);
    }
}
