package com.xidian.iot.common.tmp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mrl
 * @Title: UidGeneratorImpl
 * @Package
 * @Description: 临时Id生成器
 * @date 2020/9/14 11:14 上午
 */
@Slf4j
public class UidGeneratorImpl implements UidGenerator {

    private final Lock lock;
    private final long prefix;
    //本地自增变量也可以用AtomicLong替代，AtomicLong不需要锁保护
    private volatile long start;

    /**
     * Id生成器应保持全局唯一
     * @param lock ${@link Lock}的任意实现
     * @param uidPrefixFactory uid前缀工厂，通过工厂获取前缀，如果工厂为null，则由本地默认算法生成前缀
     */
    public UidGeneratorImpl(Lock lock, UidPrefixFactory uidPrefixFactory) {
        if(lock==null){
            throw new RuntimeException();
        }
        this.lock = lock;
        //前缀由master分配 固定32位二进制
        if(uidPrefixFactory==null){
            prefix = getDefaultPrefix();
        }else {
            prefix = uidPrefixFactory.getPrefix();
        }
        this.start = 0;
    }

    public long getDefaultPrefix(){
        return System.currentTimeMillis()/1000;
    }

    @Override
    public long getUID(long waitMis) throws InterruptedException {
        long uid = -1;
        if(lock.tryLock(waitMis, TimeUnit.MILLISECONDS)){
            try {
                uid = (prefix<<32) + (++start);
                System.out.printf("uid-----%08x\n", uid);
            } catch (Exception e) {
                System.out.println("exception-----"+e);
            } finally {
                lock.unlock();
            }
        }
        return uid;
    }

    public static void main(String[] args) {
        UidGeneratorImpl uidGeneratorImpl = new UidGeneratorImpl(new ReentrantLock(), new UidPrefixFactoryImpl());
        final int threadNum = 1000000;
        Thread[] threads = new Thread[threadNum];
        for(int i=0;i<threadNum;i++){
            threads[i] = new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    uidGeneratorImpl.getUID(10000);
                }
            });
        }
        for(Thread thread : threads){
            thread.start();
        }
    }
}
