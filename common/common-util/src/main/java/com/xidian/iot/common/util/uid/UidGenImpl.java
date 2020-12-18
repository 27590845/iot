package com.xidian.iot.common.util.uid;

import com.alibaba.fastjson.JSONObject;
import com.xidian.iot.common.util.HttpUtil;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author mrl
 * @Title: UidGenImpl
 * @Package
 * @Description: 临时Id生成器
 * @date 2020/9/14 11:14 上午
 */
@Slf4j
public class UidGenImpl implements UidGen {

    //本地自增变量也可以用AtomicLong替代，AtomicLong不需要锁保护，而且性能更高，但是用锁可以干更多事情
    private Long prefix;
    private AtomicLong start;
    @Setter
    private String prefixSourceUri;

    /**
     * Id生成器初始化
     */
    private synchronized void init() {
        //双重判断
        if(prefix != null) return;
        //prefixSourceUri不为null，则前缀由master分配 固定32位二进制
        if(prefixSourceUri ==null){
            prefix = System.currentTimeMillis()>>16;
        }else {
            Long result = tryGetObject(HttpUtil.sendGet(prefixSourceUri));
            if (result==null) return;
            prefix = result;
        }
        this.start = new AtomicLong(0);
    }

    private Long tryGetObject(String raw) {
        if (!JSONObject.isValidObject(raw)) return null;
        JSONObject jsonObject = JSONObject.parseObject(raw);
        if(jsonObject==null || !jsonObject.containsKey("data")) return null;
        return jsonObject.getLong("data");
    }

    @Override
    public long getUID(long waitMis) throws InterruptedException {
        while (prefix==null) init();
        long uid = -1;
        uid = (prefix<<32) + (start.addAndGet(1));
//        System.out.printf("uid-----%08x\n", uid);
        log.info("genId--------{}", uid);
        return uid;
    }


    public static void main(String[] args) throws InterruptedException {
        int threadNum = 2500;
        List<Long> idSet = new Vector<>();
        UidGenImpl uidGeneratorImpl = new UidGenImpl();
        Thread[] threads = new Thread[threadNum];
        for(int i=0;i<threadNum;i++){
            threads[i] = new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    idSet.add(uidGeneratorImpl.getUID(1000));
                }
            });
        }
        for(Thread thread : threads){
            thread.start();
        }

        Thread.sleep(5000);
        System.out.println(idSet.size());
        //检查是否有重复
        System.out.println(new HashSet(idSet).size());
    }
}
