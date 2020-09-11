package com.xidian.iot.datacenter.service;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.Resource;

/**
 * @author mrl
 * @Title: BaseTask
 * @Package
 * @Description: task的超类，包含了线程池的内容    copy from langyan
 * @date 2020/9/10 5:23 下午
 */
public class BaseTask extends SpringContext {

    /**
     * 线程池引用
     */
    @Resource
    protected TaskExecutor taskExecutor;

}
