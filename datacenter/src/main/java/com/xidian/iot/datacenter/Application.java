package com.xidian.iot.datacenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author mrl
 * @Title: Application
 * @Package
 * @Description:
 * @date 2020/9/24 11:31 上午
 */
@Slf4j
public class Application {

    final static String MSG_NUM = "--msgNum=";

    final static String SHUTDOWN = "shutdown";

    public static void main(String[] args) throws IOException {

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/application-context.xml");

        ServerSocket serverSocket = context.getBean(ServerSocket.class);
        log.info("服务器已经启动，等待客户端连接");

        String msg;
        do {
            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            msg = br.readLine();
            log.info("接受到客户端的消息：{}", msg);
            String response;
            if ("shutdown".equals(msg)) {
                response = "Shutdown successful. See you next time";
            } else {
                response = "Your msg : " + msg;
            }
            PrintStream ps = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
            ps.println(response);
            ps.flush();
            ps.close();
            br.close();
        } while (!SHUTDOWN.equals(msg));//遇到关机命令就退出

        serverSocket.close();
        context.close();

//        String msgNumArg = null;
//        if(args != null && Stream.of(args).anyMatch(arg -> arg.contains(MSG_NUM))){
//            msgNumArg=Stream.of(args).filter(arg -> arg.contains(MSG_NUM)).findFirst().get();
//        }
    }
}
