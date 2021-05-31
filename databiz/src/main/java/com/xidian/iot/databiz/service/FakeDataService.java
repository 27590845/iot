package com.xidian.iot.databiz.service;

/**
 * 制造假数据
 * @author: Hansey
 * @date: 2021-05-28 21:15
 */
public interface FakeDataService {

    /**
     * 产生假数据
     * @param sceneSn 需要产生数据的网关
     * @param avg 网关数据的平均值
     * @param variance 方差
     * @param period 周期/频率
     * @return void
     * */
    void makeFakeDate(String sceneSn,int avg,int variance,int period);
}