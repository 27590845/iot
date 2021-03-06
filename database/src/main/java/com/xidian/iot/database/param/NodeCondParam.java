package com.xidian.iot.database.param;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.valid.EnumValidation;
import com.xidian.iot.database.valid.NumLenValidation;
import com.xidian.iot.database.valid.ValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
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

    /**
     * 场景名称
     */
    @ApiModelProperty(hidden = true)
    private String sceneName;

    /**
     * 节点名称
     */
    @ApiModelProperty(hidden = true)
    private String nodeName;

    /**
     * 属性名称
     */
    @ApiModelProperty(hidden = true)
    private String naName;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(hidden = true)
    @Override
    public Long getNcId() {
        return super.getNcId();
    }

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(hidden = true)
    @Override
    public Long getNtId() {
        return super.getNtId();
    }

    @ApiModelProperty("节点标识")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "节点标识不能为空")
    @Pattern(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, regexp = "[0-9a-zA-Z]{1,6}", message = "非法的节点标识")
    @Override
    public String getNodeSn() {
        return super.getNodeSn();
    }

    @ApiModelProperty("场景标识")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "场景标识不能为空")
    @Pattern(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, regexp = "[0-9a-zA-Z]{1,20}", message = "非法的场景标识")
    @Override
    public String getSceneSn() {
        return super.getSceneSn();
    }

    @ApiModelProperty("关联节点属性的ID")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "节点属性ID不能为空")
    @NumLenValidation(lens = {18,19}, binary = false, message = "非法的节点属性ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @Override
    public Long getNaId() {
        return super.getNaId();
    }

    @ApiModelProperty("触发器条件的操作符，1>，2>=，3<，4<=，5==，6新值，7冻结，8复活")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "操作符不能为空")
    @EnumValidation(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, ints = {1,2,3,4,5,6,7,8}, message = "非法的操作符")
    @Override
    public Byte getNcOp() {
        return super.getNcOp();
    }

    @ApiModelProperty("触发器条件的操作数")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "操作数不能为空")
    @Pattern(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, regexp = "^(-?\\d+)(\\.\\d+)?$", message = "操作数必须是合法有理数")
    @Override
    public String getNcVal() {
        return super.getNcVal();
    }

    @ApiModelProperty("符合条件该条件，需要连续满足操作结果的次数")
    @NotNull(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, message = "符合条件所需的连续满足次数不能为空")
    @Min(groups = {ValidGroup.INSERT.class,ValidGroup.UPDATE.class}, value = 0)
    @Override
    public Integer getNcFitTime() {
        return super.getNcFitTime();
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNaName() {
        return naName;
    }

    public void setNaName(String naName) {
        this.naName = naName;
    }
}
