package com.xidian.iot.datacenter.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author mrl
 * @Title: TestResource
 * @Package com.xidian.iot.datacenter
 * @Description: test resource
 * @date 2020/8/31 9:08 下午
 */
@Component
@Path("/test")
@Api("测试接口")
public class TestResource {

    private static final Logger logger = LoggerFactory.getLogger(TestResource.class);

    @ApiOperation(value = "welcome", notes = "欢迎界面")
    @Path("/welcome")
    @GET
//    @Produces("text/plain;charset=utf-8")
    public Response addActivity(@ApiParam(name = "param", value = "测试参数", required = false) String param) {
        logger.warn(String.format("request param is %s", param));
        return Response.ok("欢迎使用 datacenter").build();
    }

    @Path("/{param}")
    @GET
    @ApiOperation(value = "测试接口函数2", notes = "用于测试的接口函数：2")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getActivity(@PathParam("param") int param) {
        logger.warn(String.format("request param is %s", param));
        return Response.ok(param).build();
    }
}
