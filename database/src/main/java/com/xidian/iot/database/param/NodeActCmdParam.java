package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.NodeActCmd;
import com.xidian.iot.database.valid.ValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author mrl
 * @Title: NodeActCmdParam
 * @Package
 * @Description: 触发器命令下发动作传输类
 * @date 2020/9/19 7:49 下午
 */
@ApiModel("触发器命令下发动作传输类")
public class NodeActCmdParam extends NodeActCmd {

    @ApiModelProperty("动作关联的具体命令")
    @NotNull(groups = {ValidGroup.INSERT.class}, message = "关联的具体命令不能为空")
//    @Pattern(groups = {ValidGroup.INSERT.class}, regexp = "/^\\d{6}|\\d{19}$/", message = "非法的命令ID")
    @Override
    public Long getNcId() {
        return super.getNcId();
    }
}
