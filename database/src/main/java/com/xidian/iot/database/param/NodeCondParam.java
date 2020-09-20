package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.valid.EnumValidation;
import com.xidian.iot.database.valid.ValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author mrl
 * @Title: NodeCondParam
 * @Package
 * @Description: 触发器条件传输参数封装类
 * @date 2020/9/19 3:21 下午
 */
@ApiModel("触发器(规则)条件传输参数封装类")
public class NodeCondParam extends NodeCond {

    @Override
    public String getNodeSn() {
        return super.getNodeSn();
    }

    @Override
    public String getSceneSn() {
        return super.getSceneSn();
    }

    @ApiModelProperty("关联节点属性的ID")
    @NotNull(groups = {ValidGroup.INSERT.class}, message = "节点属性ID不能为空")
    @Pattern(regexp = "/^\\d{16}|\\d{19}$/", message = "非法的节点属性ID")
    @Override
    public Long getNaId() {
        return super.getNaId();
    }

//    @ApiModelProperty("关联节点的ID")
//    @NotNull(groups = {ValidGroup.INSERT.class}, message = "节点ID不能为空")
//    @Pattern(regexp = "/^\\d{16}|\\d{19}$/", message = "非法的节点ID")
//    @Override
//    public Long getNodeId() {
//        return super.getNodeId();
//    }

    @ApiModelProperty("触发器条件的操作符，1>，2>=，3<，4<=，5==，6新值，7冻结，8复活")
    @NotNull(groups = {ValidGroup.INSERT.class}, message = "操作符不能为空")
    @EnumValidation(ints = {1,2,3,4,5,6,7,8}, message = "非法的操作符")
    @Override
    public Byte getNcOp() {
        return super.getNcOp();
    }

    @ApiModelProperty("触发器条件的操作数")
    @NotNull(groups = {ValidGroup.INSERT.class}, message = "操作数不能为空")
    @Pattern(regexp = "/^(-?\\d+)(\\.\\d+)?$/", message = "操作数必须是合法有理数")
    @Override
    public String getNcVal() {
        return super.getNcVal();
    }

    @ApiModelProperty("符合条件该条件，需要连续满足操作结果的次数")
    @NotNull(groups = {ValidGroup.INSERT.class}, message = "符合条件所需的连续满足次数不能为空")
    @Min(value = 0)
    @Override
    public Integer getNcFitTime() {
        return super.getNcFitTime();
    }
}
