package com.xidian.iot.database.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xidian.iot.database.entity.Scene;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 返回给前端展示的scene及其node和nodeAttribute
 * @author: Hansey
 * @date: 2020-09-16 23:11
 */
@Data
@JsonIgnoreProperties(value = {"handler"})
public class SceneVo extends Scene implements Serializable {

    List<NodeVo> nodeVos;
}
