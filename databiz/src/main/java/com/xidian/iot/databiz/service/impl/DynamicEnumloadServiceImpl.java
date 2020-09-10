package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.databiz.constants.CommunicateEnum;
import com.xidian.iot.databiz.constants.UsageEnum;
import com.xidian.iot.common.util.DynamicEnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;


/**
 * @author: Hansey
 * @date: 2020-09-09 22:07
 */
@Service
@Slf4j
public class DynamicEnumloadServiceImpl implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        InputStream inputStream1 = DynamicEnumloadServiceImpl.class.getResourceAsStream("/conf/communicate.properties");
        InputStream inputStream2 = DynamicEnumloadServiceImpl.class.getResourceAsStream("/conf/usage.properties");
        //实例化Properties类
        Properties properties1 = new Properties();
        Properties properties2 = new Properties();
        //调用load()方法加载properties文件，load里面传入InputSteam类型的参数或者Reader类型的参数
        try {
            properties1.load(new InputStreamReader(inputStream1, "UTF-8"));
            properties2.load(new InputStreamReader(inputStream2, "UTF-8"));
        } catch (IOException e) {
            log.info("动态枚举加载失败......");
            e.printStackTrace();
        }

        Enumeration em1 = properties1.propertyNames();
        Enumeration em2 = properties2.propertyNames();
        while (em1.hasMoreElements()) {
            String key = (String) em1.nextElement();
            String value = properties1.getProperty(key);
            String[] split = value.split("\\|");
            addEnum(CommunicateEnum.class, key, split);
        }

        while (em2.hasMoreElements()) {
            String key = (String) em2.nextElement();
            String value = properties2.getProperty(key);
            String[] split = value.split("\\|");
            addEnum(UsageEnum.class, key, split);
        }

        log.info("动态枚举加载成功......");
        for (CommunicateEnum testEnum : CommunicateEnum.values()) {
            log.info(testEnum.toString());
        }
        for (UsageEnum testEnum : UsageEnum.values()) {
            log.info(testEnum.toString());
        }
    }

    /**
     * 新增 Enum 枚举项
     *
     * @param enumType 枚举类
     * @param enumName 枚举 名称
     * @param objects  枚举项
     */
    private static <T extends Enum<?>> void addEnum(Class<T> enumType, String enumName, Object[] objects) {
        DynamicEnumUtil.addEnum(enumType, enumName, new Class<?>[]
                {String.class, String.class}, objects);
    }

}
