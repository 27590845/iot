package com.xidian.iot.datacenter.resource;

import com.xidian.iot.database.pojo.Scene;
import com.xidian.iot.datacenter.service.SceneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author mrl
 * @Title: SceneResource
 * @Package com.xidian.iot.datacenter.resource
 * @Description: scene interface resource
 * @date 2020/9/1 11:18 上午
 */
@Api("场景资源接口")
@Path("scene")
@Component
public class SceneResource {

    @Resource
    private SceneService sceneService;

    @ApiOperation(value = "list", notes = "")
    @Path("list/{page}/{limit}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getScene(@PathParam("page")int page, @PathParam("limit") int limit){
        List<Scene> scenes = sceneService.getScene(page, limit);
        return Response.ok(scenes).build();
    }
}
