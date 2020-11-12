package com.xidian.iot.dataapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.dataapi.controller.res.Page;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.param.SceneAddParam;
import com.xidian.iot.database.param.SceneUpdateParam;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.databiz.service.SceneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author: Hansey
 * @date: 2020-09-06 21:29
 */
@Api(tags = "/scene", description = "提供场景操作的相关接口")
@RestController
@RequestMapping("/scene")
public class SceneController {
    @Autowired
    private SceneService sceneService;
    @Autowired
    private NodeService nodeService;

    @ApiOperation(value = "分页获取当前用户下所有的网关号、不输入page和limit默认就是获取前五条数据")
    @GetMapping()
    public HttpResult getUserScenes(@ApiParam(name = "page", value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @ApiParam(name = "limit", value = "页数") @RequestParam(value = "limit", required = false, defaultValue = "5") int limit) {
        //分页一方面获取总条数、一方面获取数据、如果可以把page和limit也可以带着
        int total = sceneService.countScene();
        Page<Scene> scenePage = new Page<Scene>(total,page,limit);
        scenePage.setData(sceneService.getScenes(page,limit));
        return HttpResult.responseOK(scenePage);
    }

    @ApiOperation(value = "根据Sn获取指定场景及其场景下所有节点和属性")
    @GetMapping("/{sceneSn}")
    public HttpResult getScene(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn) {
        return HttpResult.responseOK(sceneService.getSceneVoBySn(sceneSn));
    }

    @ApiOperation(value = "添加场景")
    @PostMapping
    public HttpResult addScene(@ApiParam(name = "SceneAddParam", value = "场景信息") @Valid @RequestBody SceneAddParam param) {
        return HttpResult.responseOK(sceneService.addScene(param));
    }

    @ApiOperation(value = "删除场景")
    @DeleteMapping("/{sceneSn}")
    public HttpResult delScene(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn) {
        sceneService.delScene(sceneSn);
        return HttpResult.oK().message("删除场景成功");
    }

    @ApiOperation(value = "更新场景(支持部分更新)")
    @PutMapping("/{sceneSn}")
    public HttpResult updateScene(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                  @ApiParam(name = "SceneUpdateParam", value = "场景更新信息") @Valid @RequestBody SceneUpdateParam param) {
        sceneService.updateScene(sceneSn, param);
        return HttpResult.oK().message("更新场景成功");
    }

    @ApiOperation(value = "查看一个节点的数据、如果其中包含了st和et那么就表示的是起始时间和结束时间，没有就取出最后一次上传的数据")
    @GetMapping("/{sceneSn}/node/{nodeSn}")
    public HttpResult getNodeData(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn,
                                  @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable("nodeSn") String nodeSn,
                                  @ApiParam(name = "st", value = "开始时间st、et的格式为yyyy-MM-dd HH:mm:ss") @RequestParam(name = "st", required = false) String st,
                                  @ApiParam(name = "et", value = "结束时间同st的时间格式et的格式") @RequestParam(name = "et", required = false) String et) {
        //如果上传的st和et不为空说明查询的是历史时间
        if(StringUtils.isNotBlank(st)&&StringUtils.isNotBlank(et)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long stL = 0L, etL = 0L;
            try {
                stL = sdf.parse(st).getTime() / 1000;
                etL = sdf.parse(et).getTime() / 1000;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new BusinessException(-1, "传入的时间格式有误，请检查后重新上传");
            }
            return HttpResult.responseOK(nodeService.getMongoData(sceneSn,nodeSn,stL,etL));
        }
        return HttpResult.responseOK(nodeService.getMongoLD(sceneSn,nodeSn));
    }

    @ApiOperation(value = "查看一个节点的数据、15分钟内最新的消息")
    @GetMapping("/{sceneSn}/node/{nodeSn}/current")
    public HttpResult getCurrentNodesData(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn
            , @ApiParam(name = "nodeSn", value = "节点sn") @PathVariable("nodeSn") String nodeSn) {
        nodeService.getNodeBySn(sceneSn,nodeSn);//先查询是否存在该节点
        return HttpResult.responseOK(nodeService.getMongoCD(sceneSn,nodeSn));
    }

    @ApiOperation(value = "查看一个场景下15分钟内最新的数据")
    @GetMapping("/{sceneSn}/nodes/current")
    public HttpResult getLatestNodesData(@ApiParam(name = "sceneSn", value = "场景sn") @PathVariable("sceneSn") String sceneSn) {
        return HttpResult.responseOK(sceneService.getLatestNodesData(sceneSn));
    }
}
