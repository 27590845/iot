package com.xidian.iot.datacenter.service.triger;

import com.xidian.iot.database.entity.*;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.datacenter.service.BaseTask;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author mrl
 * @Title: SendMessageTask
 * @Package
 * @Description: 触发器被触发后，发送相应的信息    copy from langyan
 * @date 2020/9/10 7:28 下午
 */
@Slf4j
public class SendMessageTask extends BaseTask implements Runnable {

    /**
     * 触发器ID。
     */
    @Setter
    private Long ntId;
    /**
     * 触发条件
     */
    @Setter
    private List<NodeCondExt> nodeCondExtList;
    /**
     * 场景数据访问接口。
     */
    @Resource
    private SceneDao sceneDao;
    /**
     * 节点数据访问接口。
     */
    @Resource
    private NodeDao nodeDao;
    /**
     * 节点属性数据访问接口。
     */
    @Resource
    private NodeAttrDao nodeAttrDao;
    /**
     * 节点属性数据访问接口。
     */
    @Resource
    private ProductAttributeDao productAttributeDao;
    /**
     * 节点触发器数据访问接口。
     */
    @Resource
    private NodeTrigDao nodeTrigDao;
    /**
     * 报警数据访问接口。
     */
    @Resource
    private NodeActionAlertDao nodeActionAlertDao;
    /**
     * zl 3.8 添加
     * 节点条件访问接口。
     */
    @Resource
    private AppDao appDao;
    /**
     * mp_openid数据访问接口
     */
    @Resource
    private MpOpenidDao  mpOpenidDao;
    /**
     * e-mail客户端
     */
    @Resource
    private EmailClient emailClient;
    /**
     * sms客户端
     */
    @Resource
    private SMSClient smsClient;
    /**
     * 站内信客户端
     */
    @Resource
    private SiteLetterClient siteLetterClient;
    /**
     * 发送微信消息客户端
     */
    @Resource
    private  WechatClient wechatClient;

    /**
     *
     */

    // 用于发送站内信
    private Scene firstScene;
    private Node firstNode;
    // 邮件警报标题
    @Setter
    private String nodeAlertTitle;

    /**
     * 任务从这里开始。
     */
    @Override
    public void run() {

        log.debug("do SendMessageTask,ntId=({})", ntId);
        // 查询触发器的消息列表
        for (Long naaId : nodeActionAlertDao.getNaaIdListByNtId(new NaaIdListByNtId(ntId))) {
            sendMessage(nodeActionAlertDao.getNodeActionAlertById(new NodeActionAlert(naaId)));
        }
    }

    /**
     * 发送报警消息
     * <p>
     */
    private void sendMessage(NodeActionAlert nodeActionAlert) {

        // 发送警报
        log.info("will do alert [{}]", nodeActionAlert);

        if (AlertActionType.EMAIL.getCode() == nodeActionAlert.getActionType()) {
            // 发送邮件
            SimpleEmailVo emailVo = new SimpleEmailVo();
            emailVo.addEmail(nodeActionAlert.getValue());// 邮件地址
            emailVo.setTitle(nodeAlertTitle);// 邮件标题
            emailVo.setContent(nodeActionAlert.getContent());// 邮件内容
            emailClient.send(emailVo);
            log.info("Sending Email to [{}]", nodeActionAlert.getValue());
        } else if (AlertActionType.MESSAGE.getCode() == nodeActionAlert.getActionType()) {
            // 发送短信
            SMSMessageVo smsVo = new SMSMessageVo();
            smsVo.setUserName(nodeActionAlert.getNtId().toString());// 记录触发器ID
            smsVo.setPhoneNumber(nodeActionAlert.getValue());// 电话号码
            smsVo.setContent(nodeActionAlert.getContent());// 短信内容
            smsClient.send(smsVo);
            log.info("Sending Message to [{}]", nodeActionAlert.getValue());
        }else if(nodeActionAlert.getActionType().intValue()==4){//4为微信报警
            MpOpenid mpOpenid=mpOpenidDao.getByOpenid(nodeActionAlert.getValue());
            if(mpOpenid==null ){
                log.error("该openid:"+mpOpenid+" 没有对用的记录");
                return;
            }
            //mcId, appId,appSecret
            log.info("the param mcId:  "+mpOpenid.getMcId());
            log.info("the param appId:  "+mpOpenid.getMc().getAppId());
            log.info("the param appSecret:  "+mpOpenid.getMc().getAppSecret());
            String accessToken=WechatUtil.getAccessToken(mpOpenid.getMcId(), mpOpenid.getMc().getAppId(), mpOpenid.getMc().getAppSecret());
            WechatTextMessageVo vo=new WechatTextMessageVo();
            vo.setAccessToken(accessToken);
            vo.setContent(nodeActionAlert.getContent());
            vo.setOpenid(nodeActionAlert.getValue());
            log.info("微信vo:  "+vo.toString());
            wechatClient.send(vo);
        } else if(AlertActionType.ACTIVEMQ.getCode() == nodeActionAlert.getActionType()){
            try {
                ActiveMqClient.sendMessage("alert."+nodeActionAlert.getValue(),nodeActionAlert.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //zl 3.9 修改
        else if(nodeActionAlert.getActionType().intValue() == 7){
            for(NodeCondExt nodeCondExt : nodeCondExtList){
                try {
                    String clientid = nodeActionAlert.getValue();
                    String topicName = "Application.push."+clientid;
                    //以json字符串封装
                    HashMap<String , String> map = new HashMap<String , String>();
                    map.put("sceneSn", nodeCondExt.getSceneSn());
                    map.put("nodeSn", nodeCondExt.getNodeSn());
                    map.put("currentValue", nodeCondExt.getCurrentValue().toString());
                    String content = JsonUtil.toJson(map);
                    //String content = "{\"" +"currentValue"+"\":\"" + nodeCondExt.getCurrentValue().toString()+"\"}";
                    //通过currentValue(clientid)去表app查询app_secret
                    String key = appDao.getAppByClientid(clientid);
                    //key对content加密
                    DesPwdUtil des = new DesPwdUtil(key);
                    String encryptcontent = des.encrypt(content);
                    ActiveMqClient.sendMessage(topicName,encryptcontent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        NodeTrig nodeTrig = nodeTrigDao.getNodeTrigById(new NodeTrig(nodeActionAlert.getNtId()));
        log.info("sendMessage() get nodeTrig by nodeActionAlert,nodeTrig=[{}]", nodeTrig);

        // 发送站内信，属性设置顺序不能颠倒，因firstScene,firstNode
        SiteLetterVo siteLetterVo = new SiteLetterVo();
        siteLetterVo.setAlertContent(nodeActionAlert.getContent());// 报警内容
        siteLetterVo.setCreateTime(new Date());// 创建时间
        siteLetterVo.setUserId(nodeTrig.getUserId());// 用户ID
        siteLetterVo.setActionType(nodeActionAlert.getActionType());// 报警类型
        siteLetterVo.setIsRead(ReadStatus.UNREAD.getCode());// 未读
        siteLetterVo.setNtId(nodeTrig.getNtId());// 触发器ID
        siteLetterVo.setCurrentValue(getCurrentValue());// 触发时的当前值
        siteLetterVo.setAlertValue(nodeActionAlert.getValue());// 报警电话、邮件
        if (firstScene != null) {
            siteLetterVo.setSceneId(firstScene.getSceneId());
            siteLetterVo.setSceneSn(firstScene.getSnValue());
            siteLetterVo.setSceneTitle(firstScene.getTitle());// 场景标题
        }
        if (firstNode != null) {
            siteLetterVo.setNodeId(firstNode.getNodeId());
            siteLetterVo.setNodeName(firstNode.getName());// 节点名称
        }
        siteLetterClient.send(siteLetterVo);
        log.info("Sending WebSite to [{}]", nodeTrig.getUserId());

    }

    /**
     * 获得当前值
     *
     * @return
     */
    private String getCurrentValue() {
        StringBuffer sb = new StringBuffer();

        // 逐条处理条件
        Scene scene = null;
        Node node = null;
        NodeAttr nodeAttr = null;
        ProductAttribute proudctAttribute = null;
        for (NodeCondExt nodeCondExt : nodeCondExtList) {

            scene = sceneDao.getSceneBySn(new Scene(nodeCondExt.getSceneSn()));
            node = nodeDao.getNodeBySceneSnNodeSn(new Node(scene.getSnValue(), nodeCondExt.getNodeSn()));

            // 若场景和节点不存在，则不进行处理
            if (scene == null || node == null) {
                continue;
            }

            if (firstScene == null) {
                firstScene = scene;
            }
            if (firstNode == null) {
                firstNode = node;
            }

            // 获得单位
            String unit = null;
            if (node.isProductNode()) {
                proudctAttribute = productAttributeDao.getProductAttributeById(new ProductAttribute(nodeCondExt.getNaId()));
                unit = proudctAttribute.getUnit();
            } else {
                nodeAttr = nodeAttrDao.getNodeAttrById(new NodeAttr(nodeCondExt.getNaId()));
                unit = nodeAttr.getUnit();
            }

            // 封装文字
            sb.append(scene.getTitle()).append("的").append(node.getName()).append("为").append(nodeCondExt.getCurrentValue()).append(unit).append(",");
        }

        // 接去掉最后一个逗号
        if (sb.length() > 0) {
            sb = sb.replace(sb.length() - 1, sb.length(), ".");
        }

        return sb.toString();
    }
}
