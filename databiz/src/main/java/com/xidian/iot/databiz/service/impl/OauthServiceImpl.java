package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.cache.RedisUtil;
import com.xidian.iot.common.util.JWTUtil;
import com.xidian.iot.database.dto.PayloadDto;
import com.xidian.iot.databiz.service.OauthService;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 授权
 *
 * @author: Hansey
 * @date: 2020-10-19 10:30
 */
@Service
public class OauthServiceImpl implements OauthService {

    private final static ExpiringMap map = ExpiringMap.builder().expiration(60l * 60 * 24 * 30, TimeUnit.SECONDS).variableExpiration()
            .expirationPolicy(ExpirationPolicy.CREATED)
            .build();

    @Resource
    private RedisUtil redisUtil;

    @Override
    public Map<String, Object> getToken() {
        String token = null;
        Long expiresIn = null;
        token = (String) map.get("token");
        //先到本地内存去加载
        if (token != null) {
            expiresIn = map.getExpectedExpiration("token") / 1000;
        } else {
            //再到redis中去加载
            token = (String) redisUtil.get("access_token");
            expiresIn = redisUtil.getExpire("access_token");
            if (token == null) {
                //redis中也没有就直接生成
                token = generateToken();
                expiresIn = 60l * 60 * 24 * 30;
            }
            //存入本地内存中
            map.put("token", token, expiresIn, TimeUnit.SECONDS);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("access_token", token);
        res.put("token_type", "Bearer");
        res.put("scope", "iot");
        res.put("expires_in", expiresIn);
        return res;
    }

    public String getAuthToken(){
        String token = null;
        Long expiresIn = 60l * 60 * 24 * 30;
        token = (String) map.get("token");
        if (token == null) {
            token = (String) redisUtil.get("access_token");
            if (token == null) {
                token = generateToken();
            }
            map.put("token", token, expiresIn, TimeUnit.SECONDS);
            return null;
        }
        return token;
    }

    private String generateToken() {
        String token = UUID.randomUUID().toString();
        //设置三十天过期
        redisUtil.set("access_token", token, 60 * 60 * 24 * 30);
        return token;
    }

    public Map<String, Object> generateJWT(String nm, String pwd) {
        Date expireDate = DateUtils.addDays(new Date(), 14);
        PayloadDto payloadDto = PayloadDto.builder()
                .sub("jwt")
                .iat(new Date().getTime())
                .exp(expireDate.getTime())
                .build();
        String token = JWTUtil.generateTokenByHMAC(payloadDto.toString());
        String token1 = JWTUtil.generateTokenByRSA(payloadDto.toString());
        Map<String, Object> res = new HashMap<>();
        UUID.randomUUID().toString();
        res.put("access_token", token);
        res.put("access_token1", token1);
        res.put("token_type", "bearer");
        res.put("scope", "iot");
        res.put("expire_date", expireDate);
        return res;
    }
}
