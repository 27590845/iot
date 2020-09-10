package com.xidian.iot.database.entity;

import java.util.Date;

/**
 * 场景表，场景是数据上传与命令下发的最基本单位。逻辑上可看作管理着多个数据单元的交互窗口；物理上可看作通信终端，比如dtu
 *
 * @author mrl
 * @date   2020/09/09
 */
public class Scene {
    /**
     * 场景ID
     */
    private Long sceneId;

    /**
     * 场景网关号
     */
    private String sceneSn;

    /**
     * 场景名称
     */
    private String sceneName;

    /**
     * 地点名称
     */
    private String sceneLoc;

    /**
     * 经度
     */
    private Double sceneLng;

    /**
     * 纬度
     */
    private Double sceneLat;

    /**
     * 海拔
     */
    private Double sceneEl;

    /**
     * 描述
     */
    private String sceneDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneSn() {
        return sceneSn;
    }

    public void setSceneSn(String sceneSn) {
        this.sceneSn = sceneSn == null ? null : sceneSn.trim();
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

    public String getSceneLoc() {
        return sceneLoc;
    }

    public void setSceneLoc(String sceneLoc) {
        this.sceneLoc = sceneLoc == null ? null : sceneLoc.trim();
    }

    public Double getSceneLng() {
        return sceneLng;
    }

    public void setSceneLng(Double sceneLng) {
        this.sceneLng = sceneLng;
    }

    public Double getSceneLat() {
        return sceneLat;
    }

    public void setSceneLat(Double sceneLat) {
        this.sceneLat = sceneLat;
    }

    public Double getSceneEl() {
        return sceneEl;
    }

    public void setSceneEl(Double sceneEl) {
        this.sceneEl = sceneEl;
    }

    public String getSceneDesc() {
        return sceneDesc;
    }

    public void setSceneDesc(String sceneDesc) {
        this.sceneDesc = sceneDesc == null ? null : sceneDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}