package com.xidian.iot.datapush.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/**
 * WebSocketConfig主要的功能是 实现接口来配置Websocket请求的路径和拦截器
 *
 * @Configuration @Component
 * 只有一个实例对象，哪怕手动创建也只有一个； 系统会创建对象，如果手动创建，这两个不同
 *
 * @EnableWebMvc 需要以编程的方式指定视图文件相关配置
 *
 * @EnableWebSocket
 *
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Autowired
    private SceneSocketHandler sceneSocketHandler;

    @Autowired
    private com.xidian.iot.datapush.socket.NodeSocketHandler NodeSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //注册处理拦截器,拦截url为data/sceneSn的请求
        registry.addHandler(sceneSocketHandler, "data/sceneSn").addInterceptors(new SceneSocketInterceptor()).setAllowedOrigins("*");

        //注册SockJs的处理拦截器,拦截url为data/sceneSn的请求  对应浏览器不支持websocket协议时降级为轮训的请求
        registry.addHandler(sceneSocketHandler, "data/sceneSn").addInterceptors(new SceneSocketInterceptor()).withSockJS();
        //注册处理拦截器,拦截url为data/node的请求
        registry.addHandler(NodeSocketHandler, "data/node").addInterceptors(new NodeSocketInterceptor()).setAllowedOrigins("*");

        //注册SockJs的处理拦截器,拦截url为data/node的请求  对应浏览器不支持websocket协议时降级为轮训的请求
        registry.addHandler(NodeSocketHandler, "data/node").addInterceptors(new NodeSocketInterceptor()).withSockJS();

//        //定时发送广播，在这里写推送服务代码
//        Thread broadcast = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    socketHandler.sendMessageToUsers(
//                            new TextMessage("Socket Communication at"+new Date().getTime()));
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        broadcast.start();
    }

}