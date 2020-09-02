package com.xidian.iot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author mrl
 * @Title: GetProps
 * @Package com.xidian.iot.common.util
 * @Description: 获取配置文件属性值
 * @date 2020/9/1 4:53 下午
 */
public class GetProps {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Properties prop = null;
    private Map<String, String> amap = null;
    private String propValue = null;

    public GetProps(){
        super();
        readConfig("/conf/application.properties");
    }

    /**
     * 暂时禁止调用此构造函数
     * @param filePath
     */
    private GetProps(String filePath) {
        readConfig(filePath);
    }

    /**
     * 读取配置文件，获取所有的属性
     *双端份额终端邪恶的fee非非分只想
     * @param filePath 文件路径
     */

    private void readConfig(String filePath) {
        prop = new Properties();
        amap = new HashMap<>();
        try {
            prop.load(this.getClass().getResourceAsStream(filePath));
            for (String key : prop.stringPropertyNames()) {
                amap.put(key, prop.getProperty(key));
            }
        } catch (IOException e) {
            logger.error("文件读取失败" + e.getStackTrace());
            e.printStackTrace();
        }
    }

    /**
     * 获取某一属性值
     * @param propName
     * @return
     */
    public String getPropValue(String propName) {
        return prop.getProperty(propName);
    }

    /**
     * 获取prop对象
     * @return
     */
    public Properties getProp() {
        return prop;
    }


    /**
     * 获取配置文件中的所有配置
     * @return
     */
    public Map<String, String> getAmap() {
        return amap;
    }
}
