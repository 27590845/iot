package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.valid.EnumValidation;
import com.xidian.iot.database.valid.NumLenValidation;
import com.xidian.iot.database.valid.ValidGroup;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 触发报警消息表
 * @author: Hansey
 * @date: 2020-12-02 20:03
 */
public class NodeActAlertParam extends NodeActAlert {

    @ApiModelProperty("警报类型 1短信 2email")
    @NotNull(groups = {ValidGroup.INSERT.class}, message = "警报类型不能为空")
    @EnumValidation(groups = {ValidGroup.INSERT.class}, ints = {1,2}, message = "非法的操作符")
    @Override
    public Byte getNaaType() {
        return super.getNaaType();
    }
    @ApiModelProperty("发送报警对象")
    @NotNull(groups = {ValidGroup.INSERT.class}, message = "发送报警对象不能为空")
    @Override
    public String getNaaVal() {
        return super.getNaaVal();
    }
    @ApiModelProperty("发送内容")
    @NotNull(groups = {ValidGroup.INSERT.class}, message = "发送内容不能为空")
    @Override
    public String getNaaContent() {
        return super.getNaaContent();
    }
}
