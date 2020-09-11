package com.xidian.iot.database.entity.custom;

import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.entity.NodeActCmd;
import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.NodeTrig;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author mrl
 * @Title: NodeTrigEx
 * @Package
 * @Description: NodeTrig的扩展
 * @date 2020/9/10 5:59 下午
 */
@Data
public class NodeTrigEx extends NodeTrig {

    // 最后执行的时间
    private Date lastRunTime;

    /**
     * 条件列表
     */
    private List<NodeCond> conditionList;
    /**
     * 命令列表
     */
    private List<NodeActCmd> commandList;
    /**
     * 报警列表
     */
    private List<NodeActAlert> alertList;
}
