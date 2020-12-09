package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.valid.EnumValidation;
import com.xidian.iot.database.valid.NumLenValidation;
import com.xidian.iot.database.valid.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
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
    }
}
