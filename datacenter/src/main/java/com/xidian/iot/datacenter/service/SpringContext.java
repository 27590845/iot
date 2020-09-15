package com.xidian.iot.datacenter.service;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.Resource;

/**
 * @author mrl
 * @Title: SpringContext
 * @Package
 * @Description: 用于获取spring上下文    copy from langyan
 * @date 2020/9/10 5:21 下午
 */
public class SpringContext implements ApplicationContextAware {

    /**
     * Spring上下文，因为获得task时得到的非单例，为保证线程安全直接从上下文中获得bean对象。
     */
    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
