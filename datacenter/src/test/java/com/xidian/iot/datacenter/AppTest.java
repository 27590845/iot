package com.xidian.iot.datacenter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.mq.MqSender;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.datacenter.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.*;
import java.net.Socket;
import java.util.*;

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

    final static String sceneSn = "186610102200000008";
    final static String nodeSn = "000001";
    final static String topic = "node.updata."+sceneSn;

    @Resource
    MqSender mqSender;

    @Test
    public void appTest() throws JsonProcessingException, InterruptedException {
//        String msg = "{\"datastreams\":[{\"TVOC\":69,\"hum\":54.0,\"at\":1597737850021,\"pm2p5\":33,\"co2\":509,\"pm10\":59,\"sn\":\""+nodeSn+"\",\"ch20\":19,\"tem\":32.0}]}";
        String msg = "{\"datastreams\":[{\"test\":40.0,\"at\":1600570048,\"sn\":\""+nodeSn+"\"}]}";
        mqSender.sendQueue(topic, msg);
        Thread.sleep(60000);
//        for(int i=0; i< 1000; i++){
//            mqSender.sendTopic(topic, msg);
//            Thread.sleep(10000);
//        }
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

    @Test
    public void socketClient() throws IOException {
        Socket socket = new Socket("localhost",9999);
        PrintStream ps=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
        BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));

        ps.println("hello word!!");
        ps.flush();

        String info=br.readLine();
        System.out.println(info);
        ps.close();
        br.close();
    }

    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void redisTest(){
        System.out.println(redisTemplate.getExpire("check.dataceter"));
    }
}
