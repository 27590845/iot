package com.xidian.iot.databiz.service;

import com.nimbusds.jose.JOSEException;

import java.util.Map;

/**
 * 获取授权令牌
 * @author: Hansey
 * @date: 2020-10-19 10:30
 */
public interface OauthService {

    /**
     * 获取token令牌
     * 分布式集群、首先还是从本地的内存获取、
     * 本地内存有就直接返回，如果没有就到redis中去查找
     * redis中有就取出放到本地，redis还没有就直接生成并放入本地和redis
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * */
    Map<String,Object> getToken();

    /**
     * 得到系统中的token、如果已经过期就从新生成，并返回null
     * @param
     * @return java.lang.String
     * */
    String getAuthToken();
}
