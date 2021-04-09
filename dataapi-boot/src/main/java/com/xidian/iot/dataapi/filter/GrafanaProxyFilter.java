package com.xidian.iot.dataapi.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class GrafanaProxyFilter extends ZuulFilter {

    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestURI = ctx.getRequest().getRequestURI();
        if(requestURI.startsWith("/d/")){ // /d开头的请求的相应是一个html页面，即dashboard主页面，这里对他加工一下
            InputStream stream = ctx.getResponseDataStream();
            try {
                String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
                String newbody = body.substring(0, body.length() - "</html>".length() - 1)
                        + script("/js/http.js")
                        + script("/js/dashboard/app.js")
                        + "</html>";
                ctx.setResponseBody(newbody);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String script(String file) {
        return "<script src=\"" + file + "\"></script>";
    }

    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 999;
    }
}
