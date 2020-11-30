package com.xidian.iot.database.dynamic;

/**
 * @author mrl
 * @Title: DynamicDataSourceHolder
 * @Package
 * @Description: 用于暂存当前选择的datasource
 * @date 2020/11/23 5:10 下午
 */
public final class DynamicDataSourceHolder {
    private static final ThreadLocal<DynamicDataSourceGlobal> holder = new ThreadLocal<DynamicDataSourceGlobal>();
    private DynamicDataSourceHolder() {
        //
    }
    public static void putDataSource(DynamicDataSourceGlobal dataSource){
        holder.set(dataSource);
    }
    public static DynamicDataSourceGlobal getDataSource(){
        return holder.get();
    }
    public static void clearDataSource() {
        holder.remove();
    }
}
