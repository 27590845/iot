package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.valid.EnumValidation;
import com.xidian.iot.database.valid.NumLenValidation;
import com.xidian.iot.database.valid.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
<<<<<<< HEAD

import javax.validation.constraints.NotNull;

/**
 * 触发报警消息表
 * @author: Hansey
 * @date: 2020-12-02 20:03
 */
public class NodeActAlertParam extends NodeActAlert {

    @ApiModelProperty("警报类型 1短信 2email")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "警报类型不能为空")
    @EnumValidation(groups = {ValidGroup.INSERT.class}, ints = {1,2}, message = "非法的操作符")
    @Override
    public Byte getNaaType() {
        return super.getNaaType();
    }
    @ApiModelProperty("发送报警对象")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "发送报警对象不能为空")
    @Override
    public String getNaaVal() {
        return super.getNaaVal();
    }
    @ApiModelProperty("发送内容")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "发送内容不能为空")
    @Override
    public String getNaaContent() {
        return super.getNaaContent();
=======
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 节点报警信息
 *
 * @author: wmr
 * @date: 2020-11-25 19:14:26
 */
@Data
public class NodeActAlertParam {
    @ApiModelProperty(value = "触发器ID")
    @NotNull(message = "触发器ID不能为空",groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class})
    @NumLenValidation(lens = {18,19},binary = false,message = "非法的触发器ID",groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class})
    private Long ntId;

    @ApiModelProperty(value = "警报类型，1为短信，2为email，3为站内信")
    @NotNull(message = "警报类型不能为空",groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class})
    @EnumValidation(ints = {1,2,3},message = "非法的警报类型",groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class})
    private Byte naaTyoe;

    @ApiModelProperty(value = "警报的接受者，由警报类型决定，1为手机号，2为email地址，3为站内信用户ID（admin）")
    @NotBlank(message = "警报的接受者不能为空",groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class})
    private String naaVal;

    @ApiModelProperty(value = "发送内容")
    @NotBlank(message = "发送内容不能为空",groups = {ValidGroup.INSERT.class})
    private String naaContent;

    public NodeActAlert build(Long naaId){
        NodeActAlert nodeActAlert = new NodeActAlert();
        nodeActAlert.setNaaId(naaId);
        nodeActAlert.setNtId(ntId);
        nodeActAlert.setNaaVal(naaVal);
        nodeActAlert.setNaaType(naaTyoe);
        nodeActAlert.setNaaContent(StringUtils.isNotBlank(naaContent)?naaContent:null);
        return nodeActAlert;
>>>>>>> 1828fdaa3820f6191fc506a06a8bf688986a00af
    }
}
