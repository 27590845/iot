package com.xidian.iot.database.param;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.valid.EnumValidation;
import com.xidian.iot.database.valid.NumLenValidation;
import com.xidian.iot.database.valid.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 触发报警消息表
 * @author: Hansey
 * @date: 2020-12-02 20:03
 */
public class NodeActAlertParam extends NodeActAlert {

    @ApiModelProperty(value = "触发报警消息表ID")
    @NotNull(message = "触发器ID不能为空",groups = {ValidGroup.UPDATE.class})
    @JsonSerialize(using = ToStringSerializer.class)
    @Override
    public Long getNaaId() {
        return super.getNaaId();
    }

    @ApiModelProperty(value = "触发器ID")
//    @NotNull(message = "触发器ID不能为空",groups = {ValidGroup.UPDATE.class})
    @JsonSerialize(using = ToStringSerializer.class)
    @Override
    public Long getNtId() {
        return super.getNtId();
    }

    @ApiModelProperty("警报类型，1为短信，2为email，3为站内信")
    @NotNull(groups = {ValidGroup.INSERT.class, ValidGroup.UPDATE.class}, message = "警报类型不能为空")
    @EnumValidation(groups = {ValidGroup.INSERT.class}, ints = {1, 2, 3}, message = "非法的警报类型")
    @Override
    public Byte getNaaType() {
        return super.getNaaType();
    }

    @ApiModelProperty("发送报警对象")
    @NotBlank(groups = {ValidGroup.INSERT.class, ValidGroup.UPDATE.class}, message = "发送报警对象不能为空")
    @Override
    public String getNaaVal() {
        return super.getNaaVal();
    }

    @ApiModelProperty("发送内容")
    @NotBlank(groups = {ValidGroup.INSERT.class, ValidGroup.UPDATE.class}, message = "发送内容不能为空")
    @Override
    public String getNaaContent() {
        return super.getNaaContent();
    }

    public NodeActAlert build() {
        NodeActAlert nodeActAlert = new NodeActAlert();
        nodeActAlert.setNaaId(this.getNaaId());
        nodeActAlert.setNtId(this.getNtId());
        nodeActAlert.setNaaVal(this.getNaaVal());
        nodeActAlert.setNaaType(this.getNaaType());
        nodeActAlert.setNaaContent(StringUtils.isNotBlank(this.getNaaContent()) ? this.getNaaContent() : null);
        return nodeActAlert;
    }
}
