package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.param.NodeAttrParam;
import com.xidian.iot.database.param.ValidList;
import com.xidian.iot.database.valid.ValidGroup;
import com.xidian.iot.databiz.service.NodeAttrService;
import com.xidian.iot.databiz.service.NodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 节点属性
 *
 * @author: Hansey
 * @date: 2020-09-13 11:05
 */
@Api(tags = "/nodeAttr", description = "提供节点属性操作的相关接口")
@RestControllerAdvice
@RequestMapping("/nodeAttr")
public class NodeAttrController {
    @Autowired
    private NodeAttrService nodeAttrService;
    @Resource
    private NodeService nodeService;

    @ApiOperation(value = "批量添加节点属性")
    @PostMapping("/{sceneSn}/{nodeSn}")
    public HttpResult addNodeAttrs(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                   @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable("nodeSn") String nodeSn,
                                   @ApiParam(name = "nodeAttrs", value = "节点属性列表") @Validated(ValidGroup.INSERT.class) @RequestBody() ValidList<NodeAttrParam> nodeAttrs) {
        //先检查批量添加的是否有重复的keys
        nodeAttrService.checkReptAttrKeys(nodeAttrs);
        //检查是否存在该节点
        Node node = nodeService.getNodeVoBySn(sceneSn, nodeSn);
        //批量添加的传感器属性keys在该节点是否存在
        nodeAttrService.checkExistAttrKeys(node.getNodeId(), nodeAttrs);
        return HttpResult.responseOK(nodeAttrService.addNodeAttr(sceneSn, nodeSn, node.getNodeId(), nodeAttrs));
    }

    @ApiOperation(value = "根据sceneSn、nodeSn、naKeys、批量删除节点属性")
    @DeleteMapping("/{sceneSn}/{nodeSn}/{naKeys}")
    public HttpResult delNodeAttr(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                  @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable(value = "nodeSn") String nodeSn,
                                  @ApiParam(name = "naKeys", value = "节点属性列表 例如tem1,tem2,tem3,tem4这种格式") @PathVariable(value = "naKeys") String naKeys) {
        List<String> naKeyLists = Arrays.asList(naKeys.split(","));
        nodeAttrService.delNodeAttrs(sceneSn, nodeSn, naKeyLists);
        return HttpResult.oK().message("删除场景成功");
    }

    @ApiOperation(value = "根据sceneSn、nodeSn删除该节点下所有属性")
    @DeleteMapping("/{sceneSn}/{nodeSn}")
    public HttpResult delNodeAttrs(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                   @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable(value = "nodeSn") String nodeSn) {
        nodeAttrService.delNodeAttrs(sceneSn, nodeSn, null);
        return HttpResult.oK().message("删除场景成功");
    }

    //
    @ApiOperation(value = "根据naId更新节点属性")
    @PutMapping("/{naId}")
    public HttpResult updateNodeAttr(@ApiParam(name = "naId", value = "节点属性Id") @PathVariable("naId") Long naId,
                                     @ApiParam(name = "NodeAttrParam", value = "节点属性更新信息") @Valid @RequestBody NodeAttrParam param) {
        nodeAttrService.updateNodeAttr(naId, param);
        return HttpResult.oK().message("更新成功");
    }

    @ApiOperation(value = "根据sceneSn、nodeSn、naKey获取节点属性信息")
    @GetMapping("/{sceneSn}/{nodeSn}/{naKey}")
    public HttpResult getNodeAttr(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                  @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable("nodeSn") String nodeSn,
                                  @ApiParam(name = "naKey", value = "节点属性标识") @PathVariable(value = "naKey") String naKey) {
        return HttpResult.responseOK(nodeAttrService.getNodeAttr(sceneSn, nodeSn, naKey));
    }
}
