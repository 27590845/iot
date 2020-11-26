package com.xidian.iot.database.util;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author mrl
 * @Title: DataSourceCheckUtil
 * @Package com.xidian.iot.database.util
 * @Description: common MqMessageListener
 * @date 2020/11/24 8:20 下午
 */
@Slf4j
public class DataSourceCheckUtil {

    /**
     * 检查datasource是否可用（可以获取connection并且connection未关闭，则可用）
     * @param dataSource 要检测的datasource
     * @param closeable 检测完后是否关闭连接（存在不需要关闭的场景，比如通过连接池获取的连接）
     * @return
     */
    public static boolean checkDataSourceAlive(DataSource dataSource, boolean closeable) {
        boolean result = false;
        Connection cc = null;
        try {
            cc = dataSource.getConnection();
            if (cc != null && !cc.isClosed()) {
                result = true;
            }
        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            if(cc != null && closeable){
                try {
                    cc.close();
                } catch (Exception e) {
                    log.error(e.toString());
                    return result;
                }
            }
            return result;
        }
    }
}
