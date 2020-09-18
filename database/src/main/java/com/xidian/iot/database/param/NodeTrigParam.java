package com.xidian.iot.database.param;

import com.xidian.iot.database.valid.EnumValidation;
import com.xidian.iot.database.valid.ValidGroup;
import com.xidian.iot.database.entity.NodeTrig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author mrl
 * @Title: NodeTrigParam
 * @Package
 * @Description: api接口参数
 * @date 2020/9/18 11:20 上午
 */
@ApiModel("触发器(规则)传输封装类")
public class NodeTrigParam extends NodeTrig {

    @ApiModelProperty("触发器名称")
    @NotBlank(groups = {ValidGroup.INSERT.class}, message = "触发器名称不能为空")
    @Override
    public String getNtName() {
        return super.getNtName();
    }

    @ApiModelProperty("触发器描述")
    @Override
    public String getNtDesc() {
        return super.getNtDesc();
    }

    @ApiModelProperty("触发器触发间隔时间，间隔时间内不会触发")
    @NotNull(groups = {ValidGroup.INSERT.class}, message = "间隔时间不能为Null")
    @Min(groups = {ValidGroup.INSERT.class}, value = 0)
    @Override
    public Integer getNtInvl() {
        return super.getNtInvl();
    }

    @ApiModelProperty("重用标志，代表是否重复执行，0代表重复，1代表不重复(触发一次后失效)")
    @EnumValidation(groups = {ValidGroup.INSERT.class}, ints = {0, 1}, message = "非法的重用标志")
    @Override
    public Byte getNtRept() {
        return super.getNtRept();
    }

    @ApiModelProperty("可用标志，代表该触发器是否可用，0代表可用，1代表不可用")
    @EnumValidation(groups = {ValidGroup.INSERT.class}, ints = {0, 1}, message = "非法的可用标志")
    @Override
    public Byte getNtExec() {
        return super.getNtExec();
    }

    @ApiModelProperty("失效时间，当前时间超过该时间则触发器失效，默认永不失效")
    @Override
    public Date getNtExpr() {
        return super.getNtExpr();
    }
}
