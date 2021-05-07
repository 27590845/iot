package com.xidian.iot.datapush.socket;


import com.alibaba.fastjson.JSONObject;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.databiz.service.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import com.xidian.iot.databiz.service.SceneService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 实现Websocket建立连接、发送消息、断开连接等时候的处理类。
 * @Param:
 * @return:
 */
@Service
public class NodeSocketHandler implements WebSocketHandler {

    @Autowired
    private SceneService sceneService;

    @Autowired
    private NodeService nodeService;

    private static final Logger logger;
    //在线用户列表
    private static final ArrayList<WebSocketSession> users;

    static{
        users = new ArrayList<WebSocketSession>();
        logger = LoggerFactory.getLogger(SceneSocketHandler.class);
    }




    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        logger.info("成功建立socket连接");
        users.add(session);
        List<Scene> hh= sceneService.getScenes(-1,-1);
        String sceneSn = (String) session.getAttributes().get(Constant.SCENE_SN);
        String node = (String) session.getAttributes().get(Constant.Node);
        JSONObject res = new JSONObject();
        NodeData nodeData=nodeService.getMongoLD(sceneSn,node);
        res.put("NodeData",nodeData);

        if(sceneSn != null){
            session.sendMessage(new TextMessage(JSONObject.toJSONString(res)));
        }
    }

    @Override
    public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1)
            throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable error)
            throws Exception {
        if(session.isOpen()){
            session.close();
        }
        logger.error("连接出现错误:"+error.toString());
        users.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1)
            throws Exception {
        logger.debug("连接已关闭");
        users.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param sceneSn
     * @param message
     */
    public void sendMessageToUser(String sceneSn, String node,TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get(Constant.SCENE_SN ).equals(sceneSn)&&user.getAttributes().get(Constant.Node ).equals(node)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //break;
            }
        }
    }

    /**
     * @Description: 获取当前建立建立的buyerId
     * @Param:
     * @return:
     */

    public List<Map<String,String>> getNodes() {
        List<Map<String,String>> list = new ArrayList<>();
        for (WebSocketSession user : users) {
            if (user.isOpen()) {
                Map<String,String>node=new HashMap<>();
                node.put(Constant.SCENE_SN,(String)user.getAttributes().get(Constant.SCENE_SN));
                node.put(Constant.Node,(String)user.getAttributes().get(Constant.Node));
                list.add(node);
            }
        }
        return list;
    }

}
