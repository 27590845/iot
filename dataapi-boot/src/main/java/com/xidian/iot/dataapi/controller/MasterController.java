package com.xidian.iot.dataapi.controller;

import com.xidian.iot.common.util.RandomUtil;
import com.xidian.iot.common.util.StringUtil;
import com.xidian.iot.common.util.compress.Point;
import com.xidian.iot.common.util.compress.Proc;
import com.xidian.iot.common.util.compress.sdt.SdtProc;
import com.xidian.iot.common.util.uid.UidPrefixFactory;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.databiz.service.SceneService;
import com.xidian.iot.databiz.service.UidGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
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
                    "        window.location = \"/html/data_center/index.html\";\n" +
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

    @ApiOperation(value = "欢迎界面1", notes = "测试带参数的欢迎信息")
    @GetMapping("/grafana/{sceneSn}")
    public void grafanaRedirect(@ApiParam(name = "sceneSn", value = "网关号") @PathVariable String sceneSn){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        try {
            request.getRequestDispatcher("/dashboard/db/"+sceneSn+"?orgId=1").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}