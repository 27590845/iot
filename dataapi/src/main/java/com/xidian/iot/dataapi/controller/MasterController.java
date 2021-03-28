package com.xidian.iot.dataapi.controller;

import com.xidian.iot.common.util.RandomUtil;
import com.xidian.iot.common.util.StringUtil;
import com.xidian.iot.common.util.compress.Point;
import com.xidian.iot.common.util.compress.Proc;
import com.xidian.iot.common.util.compress.sdt.SdtProc;
import com.xidian.iot.dataapi.controller.res.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mariuszgromada.math.mxparser.Function;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * master 节点特有的接口
 * @author: Hansey
 * @date: 2020-09-05 21:36
 */

@Api(tags = "master 节点特有的接口")
@RestController
@RequestMapping("/master")
public class MasterController {

    public static final String MAIN_HTML =
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\"/>\n" +
            "    <title>Marco-跳转页面</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<script type=\"application/javascript\">\n" +
            "    setTimeout(function () {\n" +
            "        window.location = \"/static/html/data_center/index.html\";\n" +
            "    },1500);\n" +
            "</script>\n" +
            "<b style=\"margin: 20px\">跳转中，请稍等···</b>\n" +
            "</body>\n" +
            "</html>";

    @ApiOperation(value = "返回登录/主页面", notes = "返回登录/主页面")
    @GetMapping
    public void main(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        try {
            response.getWriter().write(MAIN_HTML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "欢迎界面1", notes = "测试带参数的欢迎信息")
    @GetMapping("/{param}")
    public HttpResult welcome1(@ApiParam(name = "param", value = "测试参数") @PathVariable String param){
        return new HttpResult(param);
    }

    @ApiOperation(value = "/compress/test", notes = "实时数据压缩、解压缩测试")
    @GetMapping("/compress/test")
    public HttpResult getSdtImage(double accuracyE, double mass, String functionStr, int pointNum, int fCollect){
        if(StringUtil.isBlank(functionStr)) return null;

        Proc sdtProc = new SdtProc();

        Map<String, List<Point>> lines = new HashMap<>();
        Map<String, Function> functions = new HashMap<>();
        lines.put("origin", new ArrayList<>());
        for (String tmp : functionStr.split(";")){
            functions.put(tmp, new Function(tmp));
            lines.put(tmp, new ArrayList<>());
        }

        //如果采样周期非正整数，则随机采样
        boolean isRandomCollect = fCollect<=0;
        double lastY=0;
        mass = Math.abs(mass);

        for(int i=0; i<pointNum; i++){
            double y = 0;
            for (Map.Entry function : functions.entrySet()){
                double tmp = ((Function)function.getValue()).calculate(i);
                lines.get(function.getKey()).add(new Point(i, tmp));
                y += tmp;
            }
            if(fCollect<=0 || i%fCollect == 0){
                lastY = y;
                if(isRandomCollect){
                    fCollect = RandomUtil.nextInt(1, 100);
                }
            }
            //加入躁点
            if(i%2 == 0){
                lastY += RandomUtil.nextDouble(-mass, mass);
            }
            lines.get("origin").add(new Point(i, lastY));
        }
        List<Point> compressPoints = sdtProc.compress(lines.get("origin"), accuracyE);
        lines.put("uncompressed", sdtProc.unCompress(compressPoints, 0, pointNum, 1));
        return HttpResult.oK().code(0)
                .message("原点数："+lines.get("origin").size()+"; 压缩后点数："+compressPoints.size())
                .data(lines).success(true);
    }
}
