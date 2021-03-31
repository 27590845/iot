package com.xidian.iot.dataapi.interceptor;

import com.alibaba.fastjson.JSON;
import com.xidian.iot.common.util.constants.ExceptionEnum;
import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.databiz.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截器、验证token是否正确、在请求处理之前进行调用（Controller方法调用之前）
 *
 * @author: Hansey
 * @date: 2020-10-20 20:23
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private OauthService oauthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        try {
            //这里验证token是否正确、或者是否token是否过期
            String token = request.getParameter("access_token");
            HttpResult result = null;
            response.setContentType("application/json;charset=utf-8");
            //系统中的token
            String access_token = oauthService.getAuthToken();
            if(access_token==null){
                result = HttpResult.generateErrorResult(ExceptionEnum.EXPIRE_TOKEN);
                response.getWriter().write(JSON.toJSONString(result));
                return false;
            }
            if (token != null) {
                if(token.equals(access_token)) {
                    return true;
                }else {
                    result = HttpResult.generateErrorResult(ExceptionEnum.MISMATCH_TOKEN);
                    response.getWriter().write(JSON.toJSONString(result));
                    return false;
                }
            }
            //在请求头加Authorization
            String auth = request.getHeader("Authorization");
            if (auth != null) {
                if (!auth.substring(0, 6).equals("Bearer")) {
                    result = HttpResult.generateErrorResult(-1, "授权类型错误");
                    response.getWriter().write(JSON.toJSONString(result));
                    return false;
                }
                token = auth.substring(7);
                if(token.equals(access_token)) {
                    return true;
                }else {
                    result = HttpResult.generateErrorResult(ExceptionEnum.MISMATCH_TOKEN);
                    response.getWriter().write(JSON.toJSONString(result));
                    return false;
                }
            }
            result = HttpResult.generateErrorResult(-1, "token不可为空，请携带token请求数据");
            response.getWriter().write(JSON.toJSONString(result));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//         System.out.println("执行了TestInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }

}
