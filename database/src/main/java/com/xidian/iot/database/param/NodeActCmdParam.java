package com.xidian.iot.database.param;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xidian.iot.database.entity.NodeActCmd;
import com.xidian.iot.database.valid.NumLenValidation;
import com.xidian.iot.database.valid.ValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author mrl
 * @Title: NodeActCmdParam
 * @Package
 * @Description: 触发器命令下发动作传输类
 * @date 2020/9/19 7:49 下午
 */
@ApiModel("触发器命令下发动作传输类")
public class NodeActCmdParam extends NodeActCmd {

    @JsonSerialize(using = ToStringSerializer.class)
    @Override
    public Long getNtId() {
        return super.getNtId();
    }

    @JsonSerialize(using = ToStringSerializer.class)
    @Override
    public Long getNacId() {
        return super.getNacId();
    }

    @ApiModelProperty("动作关联的具体命令")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "关联的具体命令不能为空")
    @NumLenValidation(lens = {18,19}, binary = false, message = "非法的命令ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @Override
    public Long getNcId() {
        return super.getNcId();
    }
}
