package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.Scene;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;

/**
 * @author: Hansey
 * @date: 2020-09-10 21:14
 */
@Data
@NoArgsConstructor
@ApiModel(description = "网关修改信息")
public class SceneUpdateParam {
    @ApiModelProperty(value = "场景名称")
    private String sceneName;

    @ApiModelProperty(value = "地点名称")
    private String sceneLoc;

    @ApiModelProperty(value = "经度")
    @Range(min=-180, max=180)
    private Double sceneLng;

    @ApiModelProperty(value = "纬度")
    @Range(min=-90, max=90)
    private Double sceneLat;

    @ApiModelProperty(value = "海拔")
    private Double sceneEl;

    @ApiModelProperty(value = "描述")
    private String sceneDesc;

    @ApiModelProperty(value = "网关身份标识")
    private String sceneIdentifier;

    //过滤非法场景、地点名称、描述
    public SceneUpdateParam filterBlank(){
        this.sceneName = (StringUtils.isNotBlank(sceneName)?sceneName:null);
        this.sceneLoc = (StringUtils.isNotBlank(sceneLoc)?sceneLoc:null);
        this.sceneDesc = (StringUtils.isNotBlank(sceneDesc)?sceneDesc:null);
        return this;
    }
    /**
     *  过滤非法场景、地点名称、描述
     * @param sceneId
     * @return com.xidian.iot.database.entity.Scene
     * */
    public Scene build(Long sceneId){
        Scene scene = new Scene();
        scene.setSceneId(sceneId);
        scene.setSceneName(StringUtils.isNotBlank(sceneName)?sceneName:null);
        scene.setSceneLoc(StringUtils.isNotBlank(sceneLoc)?sceneLoc:null);
        scene.setSceneDesc(StringUtils.isNotBlank(sceneDesc)?sceneDesc:null);
        scene.setSceneIdentifier(StringUtils.isNotBlank(sceneIdentifier)?sceneIdentifier:null);
        scene.setSceneLng(sceneLng);
        scene.setSceneLat(sceneLat);
        scene.setSceneEl(sceneEl);
        return scene;
    }
}
