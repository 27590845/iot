package com.xidian.iot.database.entity.custom;

import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.Scene;
import lombok.Data;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mrl
 * @Title: NodeCondExt
 * @Package
 * @Description: NodeCond扩展类
 * @date 2020/9/11 4:13 下午
 */
@Data
public class NodeCondExt extends NodeCond {

    public NodeCondExt(){}

    public NodeCondExt(NodeCond nodeCond){
        if (nodeCond == null) return;
        setNcId(nodeCond.getNcId());
        setNaId(nodeCond.getNaId());
        setSceneId(nodeCond.getSceneId());
        setSceneSn(nodeCond.getSceneSn());
        setNodeId(nodeCond.getNodeId());
        setNodeSn(nodeCond.getNodeSn());
        setNcOp(nodeCond.getNcOp());
        setNcVal(nodeCond.getNcVal());
        setNcFitTime(nodeCond.getNcFitTime());
    }

    /**
     * 此条件是否被满足，根据这个值决定是否检查触发器。
     */
    private boolean fit = false;
    /**
     * 条件每次比较时的值,只记录最有一次
     */
    private Double currentValue;
    /**
     * 当前满足次数，默认0
     */
    private int currentFitCount = 0;

    /**
     * 节点属性
     */
    private NodeAttr nodeAttribute;
    /**
     * 场景
     */
    private Scene scene;
    /**
     * 节点
     */
    private Node node;

    /**
     * 重置条件
     */
    public void reset() {
        fit = false;
        currentFitCount = 0;
        currentValue = 0d;
    }

    /**
     * 返回这个条件，需要检测的节点属性key。
     *
     * @return 节点属性key。
     */
    public String getNodeAttrKey() {
        return nodeAttribute.getNaKey();
    }

    /**
     * 判断一个值，是否满足这个节点条件。
     * <p>
     * 本方法，只对{@link #getNcOp()}5以内的操作进行判断。
     *
     * <pre>
     *  数字与操作符对应关系如下：
     *      1>
     *      2>=
     *      3<
     *      4<=
     *      5==
     * </pre>
     *
     * @param value
     *            待判断的值。
     * @return true 满足条件，false不满足条件。
     */
    public boolean compare(Double value) {

        if (value == null) {
            return false;
        }

        Double thisValue = NumberUtils.toDouble(getNcVal());

        int operatorChar = getNcOp();
        // 根据操作符比较
        if (operatorChar == 1) {
            return value.doubleValue() > thisValue.doubleValue();
        } else if (operatorChar == 2) {
            return value.doubleValue() >= thisValue.doubleValue();
        } else if (operatorChar == 3) {
            return value.doubleValue() < thisValue.doubleValue();
        } else if (operatorChar == 4) {
            return value.doubleValue() <= thisValue.doubleValue();
        } else if (operatorChar == 5) {
            return value.doubleValue() == thisValue.doubleValue();
        } else {
            return false;
        }
    }

    /**
     * 返回操作符对应的显示字符串
     *
     * @return 操作字符串
     */
    public String getOperatorCharStr() {
        int operatorChar = getNcOp();
        if (operatorChar == 1) {
            return StringEscapeUtils.escapeHtml4(">");
        } else if (operatorChar == 2) {
            return StringEscapeUtils.escapeHtml4(">=");
        } else if (operatorChar == 3) {
            return StringEscapeUtils.escapeHtml4("<");
        } else if (operatorChar == 4) {
            return StringEscapeUtils.escapeHtml4("<=");
        } else if (operatorChar == 5) {
            return StringEscapeUtils.escapeHtml4("==");
        } else if (operatorChar == 6) {
            return "新值";
        } else if (operatorChar == 7) {
            return "冻结";
        } else if (operatorChar == 8) {
            return "复活";
        } else {
            return "";
        }
    }

    /**
     * 返回操作符对应的显示中文
     *
     * @return 操作字符串
     */
    public String getOperatorCN() {
        int operatorChar = getNcOp();
        if (operatorChar == 1) {
            return "大于";
        } else if (operatorChar == 2) {
            return "大于等于";
        } else if (operatorChar == 3) {
            return "小于";
        } else if (operatorChar == 4) {
            return "小于等于";
        } else if (operatorChar == 5) {
            return "等于";
        } else if (operatorChar == 6) {
            return "新值";
        } else if (operatorChar == 7) {
            return "冻结";
        } else if (operatorChar == 8) {
            return "复活";
        } else {
            return "";
        }
    }

    public static List<NodeCondExt> getExts(List<NodeCond> nodeConds){
        if(nodeConds == null) return null;
        List<NodeCondExt> nodeCondExtList = new ArrayList<>();
        for(NodeCond nodeCond : nodeConds){
            nodeCondExtList.add(new NodeCondExt(nodeCond));
        }
        return nodeCondExtList;
    }
}
