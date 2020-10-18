package com.xidian.iot.common.alert.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.xidian.iot.common.alert.AlertClient;
import com.xidian.iot.common.alert.AlertVo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 短信客户端
 *
 * @author wmr
 */
@Slf4j
public class SMSClient implements AlertClient {
    //地域ID
    @Setter
    private String regionId;
    //阿里云账号的ID
    @Setter
    private String accessKeyId;
    //阿里云账号的密码
    @Setter
    private String accessSecret;
    //阿里云api域
    @Setter
    private String sysDomain;
    //阿里云api版本
    @Setter
    private String sysVersion;
    //阿里云api操作
    @Setter
    private String sysAction;
    //短信签名名称
    @Setter
    private String signName;
    //短信模板ID
    @Setter
    private String templateCode;

    /**
     * 发送一条短信
     *
     * @param vo 短信
     *
     */
    @Override
    public boolean send(AlertVo vo) {
        SMSAlertVo smsAlertVo = (SMSAlertVo) vo;
        //创建DefaulAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        //设置短信参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(sysDomain);
        request.setSysVersion(sysVersion);
        request.setSysAction(sysAction);
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumbers",smsAlertVo.getPhoneNumber());
        request.putQueryParameter("SignName",signName);
        request.putQueryParameter("TemplateCode",templateCode);
        request.putQueryParameter("TemplateParam","{\"code\":\""+smsAlertVo.getContent()+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);  //发送短信
            log.info(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
            return false;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 发送多条短信
     *
     * @param list 短信列表
     *
     */
    @Override
    public void send(List<AlertVo> list) {
        if (null != list && list.size() > 0) {
            for (AlertVo alertVo : list) {
                send(alertVo);
            }
        }
    }
}
