package com.xidian.iot.common.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.xidian.iot.common.util.exception.BusinessException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;

/**
 * JWT工具类
 * @author: Hansey
 * @date: 2020-10-20 16:07
 */
public class JWTUtil {
    private final static String SECRET = "MrlNBiofqhfeqihfpqpqiweunsmxnvmvnnwjlghlhfawihwf";

    // RSA keyPair Generator
    private static RSAKey rsaKey = null;

    static {
        //从classpath下获取RSA秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("/jwt.jks"), "123456".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
        //获取RSA公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //获取RSA私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        rsaKey = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
    }

    public static String generateTokenByHMAC(String payloadStr){
        //创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).
                type(JOSEObjectType.JWT)
                .build();
        //将负载信息封装到Payload中
        Payload payload = new Payload(payloadStr);
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            //创建HMAC签名器
            JWSSigner jwsSigner = new MACSigner(SECRET);
            //签名
            jwsObject.sign(jwsSigner);
        } catch (JOSEException e) {
            e.printStackTrace();
            throw new BusinessException(-1,"jws签名出现问题");
        }
        return jwsObject.serialize();
    }

    public static PayloadDto verifyTokenByHMAC(String token) throws ParseException, JOSEException {
        //从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        //创建HMAC验证器
        JWSVerifier jwsVerifier = new MACVerifier(SECRET);
        if (!jwsObject.verify(jwsVerifier)) {
            throw new BusinessException(-1, "token签名不合法！");
        }
        String payload = jwsObject.getPayload().toString();
        PayloadDto payloadDto = JsonUtil.toObject(payload, PayloadDto.class);
        if (payloadDto.getExp() < new Date().getTime()) {
            throw new BusinessException(-1, "token已过期！");
        }
        return payloadDto;
    }

    public static String generateTokenByRSA(String payloadStr) {
        //创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .build();
        //将负载信息封装到Payload中
        Payload payload = new Payload(payloadStr);
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            //创建RSA签名器
            JWSSigner jwsSigner = new RSASSASigner(rsaKey, true);
            //签名
            jwsObject.sign(jwsSigner);
        } catch (JOSEException e) {
            e.printStackTrace();
            throw new BusinessException(-1,"jws签名出现问题");
        }
        return jwsObject.serialize();
    }

    public PayloadDto verifyTokenByRSA(String token) throws ParseException, JOSEException {
        //从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        RSAKey publicRsaKey = rsaKey.toPublicJWK();
        //使用RSA公钥创建RSA验证器
        JWSVerifier jwsVerifier = new RSASSAVerifier(publicRsaKey);
        if (!jwsObject.verify(jwsVerifier)) {
            throw new BusinessException(-1, "token签名不合法！");
        }
        String payload = jwsObject.getPayload().toString();
        PayloadDto payloadDto = JsonUtil.toObject(payload, PayloadDto.class);
        if (payloadDto.getExp() < new Date().getTime()) {
            throw new BusinessException(-1, "token已过期！");
        }
        return payloadDto;
    }

    public static class PayloadDto{
        //@ApiModelProperty("主题")
        private String sub;
        //@ApiModelProperty("签发时间")
        private Long iat;
        //@ApiModelProperty("过期时间")
        private Long exp;

        public String getSub() {
            return sub;
        }

        public void setSub(String sub) {
            this.sub = sub;
        }

        public Long getIat() {
            return iat;
        }

        public void setIat(Long iat) {
            this.iat = iat;
        }

        public Long getExp() {
            return exp;
        }

        public void setExp(Long exp) {
            this.exp = exp;
        }
    }
}
