package com.xidian.iot.database.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xidian.iot.database.valid.EnumValidation;
import com.xidian.iot.database.valid.ReptValidation;
import com.xidian.iot.database.valid.ValidGroup;
import com.xidian.iot.database.entity.NodeTrig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author mrl
 * @Title: NodeTrigParam
 * @Package
 * @Description: api接口参数
 * @date 2020/9/18 11:20 上午
 */
@ApiModel("触发器(规则)传输封装类")
@JsonIgnoreProperties(value = "handler")
public class NodeTrigParam extends NodeTrig implements Serializable {

    @ApiModelProperty("触发器名称")
    @NotBlank(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "触发器名称不能为空")
    @Length(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, min = 1, max = 32, message = "触发器命名长度须为1～32")
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
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "间隔时间不能为Null")
    @Min(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, value = 0)
    @Override
    public Integer getNtInvl() {
        return super.getNtInvl();
    }

    @ApiModelProperty("重用标志，代表是否重复执行，0代表重复，1代表不重复(触发一次后失效)")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "重用标志不能为Null")
    @EnumValidation(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, ints = {0, 1}, message = "非法的重用标志")
    @Override
    public Byte getNtRept() {
        return super.getNtRept();
    }

    @ApiModelProperty("可用标志，代表该触发器是否可用，0代表可用，1代表不可用")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "可用标志不能为Null")
    @EnumValidation(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, ints = {0, 1}, message = "非法的可用标志")
    @Override
    public Byte getNtExec() {
        return super.getNtExec();
    }

    @ApiModelProperty("失效时间，当前时间超过该时间则触发器失效，默认永不失效")
    @Future
    @Override
    public Date getNtExpr() {
        return super.getNtExpr();
    }

    @Getter
    @Setter
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "触发器条件列表不能为空")
    @Size(min = 1, message = "触发器至少包含一个条件")
    @ReptValidation(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "条件列表中不能有重复")
    @Valid
    private List<NodeCondParam> nodeCondParams;

    @Getter
    @Setter
//    @NotNull(groups = {ValidGroup.INSERT.class}, message = "触发器联动命令列表不能为空")
    @ReptValidation(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "联动命令列表中不能有重复")
    @Valid
    private List<NodeActCmdParam> nodeActCmdParams;

    @Getter
    @Setter
//    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "触发报警信息表不能为空")
    @Valid
    NodeActAlertParam nodeActAlertParam;

    @Getter
    @Setter
//    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "触发报警信息表不能为空")
    @Valid
    private List<NodeActAlertParam> nodeActAlertParams;
}
