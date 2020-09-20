package com.xidian.iot.database.param;

import com.xidian.iot.database.entity.NodeActCmd;
import io.swagger.annotations.ApiModel;

/**
 * @author mrl
 * @Title: NodeActCmdParam
 * @Package
 * @Description: 触发器命令下发动作传输类
 * @date 2020/9/19 7:49 下午
 */
@ApiModel("触发器命令下发动作传输类")
public class NodeActCmdParam extends NodeActCmd {

    @Override
    public Long getNcId() {
        return super.getNcId();
    }

    @Override
    public String getNodeSn() {
        return super.getNodeSn();
    }

    @Override
    public String getSceneSn() {
        return super.getSceneSn();
    }
}
