package com.xidian.iot.dataapi.controller;

import com.xidian.iot.common.util.RandomUtil;
import com.xidian.iot.common.util.compress.Point;
import com.xidian.iot.common.util.compress.Proc;
import com.xidian.iot.common.util.compress.sdt.SdtProc;
import com.xidian.iot.common.util.uid.UidPrefixFactory;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.databiz.service.UidGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * master 节点特有的接口
 * @author: Hansey
 * @date: 2020-09-05 21:36
 */

@Api(tags = "master 节点特有的接口")
@RestController
@RequestMapping("/master")
public class MasterController {

    @ApiOperation(value = "欢迎界面1", notes = "测试带参数的欢迎信息")
    @GetMapping("/{param}")
    public HttpResult welcome1(@ApiParam(name = "param", value = "测试参数", required = false) @PathVariable String param){
        return new HttpResult(param);
    }

    @ApiOperation(value = "欢迎2", notes = "测试参数为json的欢迎信息")
    @PostMapping("/welcome2")
    public HttpResult welcome2(@ApiParam(name = "param", value = "测试参数对象", required = false) @RequestBody Scene scene){
        return HttpResult.oK()
                .code(1)
                .message("dddd")
                .data("sss")
                .success(false);
    }

    @Autowired(required = false)
    private UidPrefixFactory uidPrefixFactory;

    @ApiOperation(value = "idPrefix", notes = "SimpleIdGenerator测试用，用于获取master的id前缀")
    @GetMapping("/idPrefix")
    public HttpResult getIdPrefix(){
        if(uidPrefixFactory==null){
            return HttpResult.oK()
                    .code(-1)
                    .message("SimpleIdGenerator未启用")
                    .success(false);
        }else {
            return HttpResult.oK()
                    .code(0)
                    .message("获取id前缀成功")
                    .data(uidPrefixFactory.getPrefix())
                    .success(true);
        }
    }

    @Autowired
    private UidGenerator uidGenerator;

    @ApiOperation(value = "idGen", notes = "Id生成测试")
    @GetMapping("/idGen")
    public HttpResult getId(){
        return HttpResult.oK()
                .code(0)
                .message("获取id成功")
                .data(uidGenerator.getUID())
                .success(true);
    }

    @ApiOperation(value = "/compress/test", notes = "实时数据压缩、解压缩测试")
    @GetMapping("/compress/test")
    public HttpResult getSdtImage(double accuracyE, double mass){
        Proc sdtProc = new SdtProc();
        List<Point> originPoints = new ArrayList<>();
        mass = Math.abs(mass);
        for(int i=0; i<1000; i++){
            Point point = new Point();
            point.setX(i);
//            point.setY(RandomUtil.nextInt(10, 19));
            point.setY(Math.sin((double) i / 10));
            //加入躁点
            if(i%3 == 0){
                point.setY(point.getY()+ RandomUtil.nextDouble(-mass, mass));
            }
            originPoints.add(point);
        }
        List<Point> compressPoints = sdtProc.compress(originPoints, accuracyE);
        List<Point> uncompressPoints = sdtProc.unCompress(compressPoints, 0, 999, 1);
        return HttpResult.oK().code(0).message("原点数："+originPoints.size()+"; 压缩后点数："+compressPoints.size()).data(
                new List[]{originPoints, compressPoints, uncompressPoints}
        ).success(true);
    }
}
