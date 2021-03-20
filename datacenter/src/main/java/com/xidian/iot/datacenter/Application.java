package com.xidian.iot.datacenter;

import com.xidian.iot.common.util.GetProps;
import com.xidian.iot.datacenter.system.SystemParamShared;
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

    public static final GetProps getProps = new GetProps();

    final static String MSG_NUM = "--msgNum=";

    final static String SHUTDOWN = "shutdown";//关闭datacenter程序
    final static String GET_SYS_PARAM = "get-sys-param";//获取系统参数
    final static String MILLI_LEVEL = "time-level-milli";//上传数据存储到mongo的时间粒度，修改为毫秒级别
    final static String SEC_LEVEL = "time-level-sec";//上传数据存储到mongo的时间粒度，修改为秒级别
    final static String TRIGGER_ON = "trigger-on";//启用规则引擎
    final static String TRIGGER_OFF = "trigger-off";//关闭规则引擎
    final static String REPORT_ON = "report-on";//开启组件定时检测
    final static String REPORT_OFF = "report-off";//取消组件定时检测
    final static String REFRESH = "refresh";//刷新当前spring BeanFactory（慎用）

    public static void main(String[] args) throws IOException {
        // 配置环境
        System.setProperty("spring.profiles.active",getProps.getPropValue("spring.profiles.active"));
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/application-context.xml");

        GetProps getProps = context.getBean(GetProps.class);
        ServerSocket serverSocket = new ServerSocket(Integer.valueOf(getProps.getPropValue("socket.server.port")));
        SystemParamShared systemParamShared = context.getBean(SystemParamShared.class);
        log.info("服务器已经启动，等待客户端连接");

        String msg;
        do {
            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            msg = br.readLine();
            log.info("接受到客户端的消息：{}", msg);
            String response;
            switch (msg){
                case SHUTDOWN:
                    response = "Shutdown successful. See you next time";
                    break;
                case REFRESH:
                    context.refresh();
                    response = "refresh complete";
                    break;
                case GET_SYS_PARAM:
//                    response = SystemParam.getDesc();
                    response = systemParamShared.getDesc();
                    break;
                case MILLI_LEVEL:
//                    SystemParam.setTimeStampDiv(1);
                    systemParamShared.setTimeStampDiv(1);
                    response = "set timeStampDiv to 1";
                    break;
                case SEC_LEVEL:
//                    SystemParam.setTimeStampDiv(1000);
                    systemParamShared.setTimeStampDiv(1000);
                    response = "set timeStampDiv to 1000";
                    break;
                case TRIGGER_ON:
//                    SystemParam.setTriggerEnable(true);
                    systemParamShared.setTriggerEnable(true);
                    response = "set trigger enable";
                    break;
                case TRIGGER_OFF:
//                    SystemParam.setTriggerEnable(false);
                    systemParamShared.setTriggerEnable(false);
                    response = "set trigger disable";
                    break;
                case REPORT_ON:
//                    SystemParam.setReportEnable(true);
                    systemParamShared.setReportEnable(true);
                    response = "set report enable";
                    break;
                case REPORT_OFF:
//                    SystemParam.setReportEnable(false);
                    systemParamShared.setReportEnable(false);
                    response = "set report disable";
                    break;
                default:
                    response = "Your msg : " + msg + ". do nothing.";
                    break;
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
