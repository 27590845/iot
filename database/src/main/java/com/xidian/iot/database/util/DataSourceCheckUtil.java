package com.xidian.iot.database.util;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class DataSourceCheckUtil {

    public static boolean checkDataSourceAlive(DataSource dataSource) {
        boolean result = false;
        Connection cc = null;
        try {
            cc = dataSource.getConnection();
            if (cc != null && !cc.isClosed()) {
                result = true;
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            try {
                cc.close();
            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
        return result;
    }
}
