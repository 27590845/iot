package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.Scene;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
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
    @Pattern(regexp = "^([0-9])$",message = "通讯协议码为一位")
    private String commCode;

    @ApiModelProperty(value = "使用场景码、一位")
    @Pattern(regexp = "^([0-9])$",message = "使用场景码仅一位")
    private String usageCode;

    @ApiModelProperty(value = "地点名称")
    private String sceneLoc;

    @ApiModelProperty(value = "经度")
    @NotNull(message = "经度不能为空")
    @Range(min=-180, max=180)
    private Double sceneLng;

    @ApiModelProperty(value = "纬度")
    @Range(min=-90, max=90)
    @NotNull(message = "纬度不能为空")
    private Double sceneLat;

    @ApiModelProperty(value = "海拔")
    private Double sceneEl;

    @ApiModelProperty(value = "描述")
    private String sceneDesc;

    @ApiModelProperty(value = "网关Sn")
    private String sceneSn;

    public Scene build(){
        Scene scene = new Scene();
        scene.setSceneName(sceneName);
        scene.setSceneLng(sceneLng);
        scene.setSceneLat(sceneLat);
        scene.setSceneEl(sceneEl);
        scene.setSceneSn(sceneSn);
        scene.setSceneLoc(StringUtils.isNotBlank(sceneLoc)?sceneLoc:null);
        scene.setSceneDesc(StringUtils.isNotBlank(sceneDesc)?sceneDesc:null);
        scene.setCreateTime(new Date());
        return scene;
    }
}
