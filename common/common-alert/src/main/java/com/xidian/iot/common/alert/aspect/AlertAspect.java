package com.xidian.iot.common.alert.aspect;

import com.xidian.iot.common.alert.alertsender.AlertFactory;
import com.xidian.iot.common.alert.util.JsonUtil;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bc、wmr
 * @date  2020-09-16 21:00
 */

@Aspect
@Component
public class AlertAspect {


    @Pointcut(value = "@annotation(com.xidian.iot.common.alert.aspect.SendAlert)")
    private void pointcut(){}

    //AfterThrowing并不能完全处理异常，该异常依然会传播到上一级调用者。(使用示例见test文件夹 运行test0)
    @AfterThrowing(value = "pointcut() && @annotation(sendAlert)", throwing = "ex")
    public void sendAlert(JoinPoint joinPoint, SendAlert sendAlert, ArithmeticException ex) {

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        String destination = sendAlert.destination();
        byte type = sendAlert.alertType();

        //根据报警条件生成报警内容
        List<NodeCondExt> nodeCondList = new ArrayList<>();
        nodeCondList = JsonUtil.toObject(sendAlert.content(),nodeCondList.getClass());
        String content = createContent(nodeCondList);

        //若捕获某种特定异常(此处为除以0的算术异常)，开始发送alert
        if (ex instanceof ArithmeticException) {
            AlertFactory.getAlert(type,destination,content);
        }
    }

    private String createContent(List<NodeCondExt> nodeCondList){
        StringBuffer sb = new StringBuffer();

        // 逐条处理条件
        Scene scene = null;
        Node node = null;
        NodeAttr nodeAttr = null;
        for (NodeCondExt nodeCondition : nodeCondList) {

            scene = nodeCondition.getScene();
            node = nodeCondition.getNode();

            // 若场景和节点不存在，则不进行处理
            if (scene == null || node == null) {
                continue;
            }

            nodeAttr = nodeCondition.getNodeAttribute();
            String unit = nodeAttr==null? "":nodeAttr.getNaUnit();
            // 封装文字
            sb.append(scene.getSceneName()).append("的").append(node.getNodeName()).append("为").append(nodeCondition.getCurrentValue()).append(unit).append(",");
        }

        // 接去掉最后一个逗号
        if (sb.length() > 0) {
            sb = sb.replace(sb.length() - 1, sb.length(), ".");
        }

        return sb.toString();
    }
}
