package com.xidian.iot.common.alert.aspect;

import com.xidian.iot.common.alert.email.EmailAlertClient;
import com.xidian.iot.common.alert.email.EmailAlertVo;
import com.xidian.iot.common.alert.email.SimpleEmailAlertVo;
import com.xidian.iot.database.entity.NodeActAlert;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author bc
 * @date  2020-09-10 21:00
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
        String content = sendAlert.content();
        AlertType type = sendAlert.alertType();

        //若捕获某种特定异常(此处为除以0的算术异常)，开始发送alert
        if (ex instanceof ArithmeticException) {
            EmailAlertClient client = new EmailAlertClient();

            SimpleEmailAlertVo emailVo = new SimpleEmailAlertVo();
            emailVo.addEmail(destination);
            emailVo.setTitle("报警信息：");
            emailVo.setContent(content);

            System.out.println(destination+" " +content);
            client.send(emailVo);
        }
    }

}
