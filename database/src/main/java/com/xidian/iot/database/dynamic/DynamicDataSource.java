package com.xidian.iot.database.dynamic;

import com.xidian.iot.database.util.DataSourceCheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mrl
 * @Title: DynamicDataSource
 * @Package
 * @Description: datasource动态选择控制器，通过key获取targetDataSources中某一可用datasource
 * @date 2020/11/23 5:12 下午
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    private List<Object> writeDataSources; //写数据源
    private List<Object> readDataSources; //多个读数据源
    private final Map<Object, Object> allDataSources = new HashMap<>();
    private final List<String> unreachableDatasourceName = new CopyOnWriteArrayList<>(); //不可用的datasource的名字集合
    private int writeDatasourceSize = 0; //写数据源个数
    private int readDataSourceSize = 0; //读数据源个数
    private AtomicLong counter = new AtomicLong(0);
    private static final Long MAX_POOL = Long.MAX_VALUE;
    private final Lock lock = new ReentrantLock();
    public void setWriteDataSources(List<Object> writeDataSources) {
        this.writeDataSources = writeDataSources;
    }
    public void setReadDataSources(List<Object> readDataSources) {
        this.readDataSources = readDataSources;
    }

    @Override
    public void afterPropertiesSet() {
        if (this.writeDataSources ==null || this.writeDataSources.size()<=0) {
            throw new IllegalArgumentException("Property 'writeDataSources' is required");
        }
        AtomicInteger tmp = new AtomicInteger(0);
        writeDataSources.stream().forEach(datasource -> allDataSources.put(DynamicDataSourceGlobal.WRITE.name()+(tmp.getAndIncrement()), datasource));
        writeDatasourceSize = writeDataSources.size();
        tmp.set(0);
        if (this.readDataSources!=null && this.readDataSources.size()>0) {
            readDataSources.stream().forEach(datasource -> allDataSources.put(DynamicDataSourceGlobal.READ.name()+(tmp.getAndIncrement()), datasource));
            readDataSourceSize = readDataSources.size();
        }
        setTargetDataSources(allDataSources);
        super.afterPropertiesSet();
    }
    @Override
    protected Object determineCurrentLookupKey() {
        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();
        String datasourceName = null;
        //如果没有给出read数据源，或者没有设置读写标志，或者读写标志为写，就用write数据源
        if(readDataSourceSize <= 0 || dynamicDataSourceGlobal == null
                || dynamicDataSourceGlobal == DynamicDataSourceGlobal.WRITE) {
            //找到一个可用的datasource
            do {
                datasourceName = DynamicDataSourceGlobal.WRITE.name() + (getCounterValSafely() % writeDatasourceSize);
            } while (unreachableDatasourceName.contains(datasourceName));
            return datasourceName;
        }
        //找到一个可用的datasource
        do {
            datasourceName = DynamicDataSourceGlobal.READ.name() + (getCounterValSafely() % readDataSourceSize);
        } while (unreachableDatasourceName.contains(datasourceName));
        return datasourceName;
    }

    private long getCounterValSafely(){
        long currValue = counter.getAndIncrement();
        if((currValue + 1) >= MAX_POOL) {
            try {
                lock.lock();
                if((currValue + 1) >= MAX_POOL) {
                    counter.set(0);
                }
            } finally {
                lock.unlock();
            }
        }
        return currValue;
    }

    //需要在spring配置文件中将此函数绑定到定时器，用来定时检测可用datasource
    public void check(){
        StringBuilder reachableName = new StringBuilder();
        allDataSources.entrySet().stream().forEach(entry -> {
            if(DataSourceCheckUtil.checkDataSourceAlive((DataSource) entry.getValue(), false)){
                unreachableDatasourceName.remove(entry.getKey());
                reachableName.append(entry.getKey()).append("\t");
            }else {
                if(!unreachableDatasourceName.contains(entry.getKey())){
                    unreachableDatasourceName.add((String) entry.getKey());
                }
            }
        });
        log.info("reachable datasource: {}", reachableName.toString());
    }
}
