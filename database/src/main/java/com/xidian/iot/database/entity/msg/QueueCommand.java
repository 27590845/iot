package com.xidian.iot.database.entity.msg;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mrl
 * @Title: QueueCommand
 * @Package
 * @Description: 命令下发包装类  一个队列命令，包括一个场景的一个节点的多组命令
 * @date 2020/9/11 9:19 下午
 */
public class QueueCommand implements Serializable {

    private static final long serialVersionUID = -3666610046470901294L;

    /**
     * 队列名称。
     */
    public static final String QUEUE_NAME = "QUEUE_SCENE_COMMAND";
    /**
     * memcache前缀
     */
    public static final String SENCE_ONLINE_FRE = "QUEUE_SCENE_COMMAND";

    /**
     * 场景所关联网关设备的出厂序列号。
     */
    @Getter
    @Setter
    private String sceneSn;

    /**
     * 节点序列号。
     */
    @Getter
    @Setter
    private String nodeSn;

    /**
     * 节点命令组。
     */
    private Map<String, String> commands;

    /**
     * 添加一组命令。
     *
     * @param key 命令组key。
     * @param value 命令组value。
     */
    public void addCommand(String key, String value) {
        if (commands == null) {
            commands = new HashMap<String, String>();
        }
        commands.put(key, value);
    }

    /**
     * 获得下发的命令。
     * <p>
     * 格式如：{id:id,key1:value1,key2,value2}，id固定。key和value不固定,由{@link #commands}决定 。
     *
     * @return 下发的命令。
     */
    public Map<String, String> getCommand() {

        // 命令内容
//        Map<String, String> command = new HashMap<String, String>();
        commands.put("sn", nodeSn);
//        command.putAll(command);

        // 下发命令体
//        Map<String, Object> issuedCommand = new HashMap<String, Object>();
//        issuedCommand.put("command", command);
        return commands;
    }
}
