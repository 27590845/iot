package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.Scene;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.*;
import java.awt.*;
import java.util.Date;

/**
 * @author: Hansey
 * @date: 2020-09-09 15:38
 */
@Data
@NoArgsConstructor
//@Builder
@ApiModel(description = "网关添加信息")
public class SceneAddParam {

    @ApiModelProperty(value = "场景名称")
    @NotBlank(message = "场景名称不能为空")
    private String sceneName;

    @ApiModelProperty(value = "通讯协议码、一位")
    @Pattern(regexp = "^[1-9]\\d*$",message = "通讯协议码为一位请从新确认")
    private String commCode;

    @ApiModelProperty(value = "使用场景码、一位")
    @Pattern(regexp = "^[1-9]\\d*$",message = "使用场景码仅一位请从新确认")
    private String usageCode;

    @ApiModelProperty(value = "地点名称")
    private String sceneLoc;

    @ApiModelProperty(value = "经度")
    @NotNull(message = "经度不能为空")
    @Pattern(regexp = "^-?((0|1?[0-7]?[0-9]?)(([.][0-9]{1,4})?)|180(([.][0]{1,4})?))$",message = "经度范围错误")
    private Double sceneLng;

    @ApiModelProperty(value = "纬度")
    @NotNull(message = "纬度不能为空")
    @Pattern(regexp = "^-?((0|[1-8]?[0-9]?)(([.][0-9]{1,4})?)|90(([.][0]{1,4})?))$",message = "纬度范围错误")
    private Double sceneLat;

    @ApiModelProperty(value = "海拔")
    private Double sceneEl;

    @ApiModelProperty(value = "描述")
    private String sceneDesc;

    public Scene build(){
        Scene scene = new Scene();
        scene.setSceneName(sceneName);
        scene.setSceneLng(sceneLng);
        scene.setSceneLat(sceneLat);
        scene.setSceneEl(sceneEl);
        scene.setSceneLoc(StringUtils.isNotBlank(sceneLoc)?sceneLoc:null);
        scene.setSceneDesc(StringUtils.isNotBlank(sceneDesc)?sceneDesc:null);
        scene.setCreateTime(new Date());
        return scene;
    }
}
