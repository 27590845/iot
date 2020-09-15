package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.databiz.service.NodeAttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 节点属性
 * @author: Hansey
 * @date: 2020-09-13 11:05
 */
@Api(tags = "/nodeAttr", description = "提供节点属性操作的相关接口")
@RestControllerAdvice
public class NodeAttrController {
    @Autowired
    private NodeAttrService nodeAttrService;

    @ApiOperation(value = "添加节点属性")
    @PostMapping("/{sceneSn}/{nodeSn}")
    public HttpResult addNodeAttrs(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                  @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable("nodeSn") String nodeSn,
                                   @ApiParam(name = "NodeUpdateParam", value = "节点属性列表") @RequestBody() List<NodeAttr> nodeAttrs) {
//        return HttpResult.responseOK(nodeAttrService.addNodeAttr(param));
        return HttpResult.oK();
    }

//    @ApiOperation(value = "删除节点")
//    @DeleteMapping("/{sceneSn}/{nodeSn}")
//    public HttpResult delScene(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
//                               @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable(value = "nodeSn") String nodeSn) {
//        nodeService.delNode(sceneSn, nodeSn);
//        return HttpResult.oK().message("删除场景成功");
//    }
//
//    @ApiOperation(value = "更新节点名称和描述")
//    @PutMapping("/{sceneSn}/{nodeSn}")
//    public HttpResult updateScene(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
//                                  @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable(value = "nodeSn") String nodeSn,
//                                  @ApiParam(name = "NodeUpdateParam", value = "节点更新信息") @Valid @RequestBody NodeUpdateParam param) {
//        if (StringUtils.isBlank(param.getNodeName())&&StringUtil.isBlank(param.getNodeDesc())){
//            return HttpResult.generateErrorResult(ExceptionEnum.PARAMETER_VERIFICATION_ERROR);
//        }
//        nodeService.updateNode(sceneSn, nodeSn, param);
//        return HttpResult.oK().message("更新成功");
//    }
//
//    @ApiOperation(value = "根据Sn获取指定场景接口")
//    @GetMapping("/{sceneSn}/{nodeSn}")
//    public HttpResult getNode(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
//                              @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable("nodeSn") String nodeSn) {
//        return HttpResult.responseOK(nodeService.getNodeBySn(sceneSn, nodeSn));
//    }
}
