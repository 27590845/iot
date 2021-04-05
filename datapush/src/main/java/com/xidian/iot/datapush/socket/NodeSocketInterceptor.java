package com.xidian.iot.datapush.socket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @desp websocket拦截器，用来记录用户标识，便于后面向特定用户发送消息
 *
 * HandshakeInterceptor
 * WebSocket握手请求的拦截器. 检查握手请求和响应, 对WebSocketHandler传递属性
 */
public class NodeSocketInterceptor implements HandshakeInterceptor {

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler handler, Exception exception) {

    }

    /**
     * @desp 将HttpSession中对象放入WebSocketSession中
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler handler,
                                   Map<String, Object> map) throws Exception {
        if(request instanceof ServerHttpRequest){
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            String id = servletRequest.getSession().getId();
            System.out.println("beforeHandshake: \n"+id);
            String sceneSn = servletRequest.getParameter(Constant.SCENE_SN);
            String node = servletRequest.getParameter(Constant.Node);
            map.put(Constant.SCENE_SN,sceneSn);
            map.put(Constant.Node,node);
            /*
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            if(session != null){
                //区分socket连接以定向发送消息
                map.put(Constant.SCENE_SN, session.getAttribute(Constant.SCENE_SN));
                map.put(Constant.Node,session.getAttribute(Constant.Node));
            }*/
        }

        return true;
    }

}
