package com.xidian.iot.datacenter.system;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SystemParam {

    private static ReadWriteLock timeStampLock;

    private volatile static int timeStampDiv;

    private volatile static boolean triggerEnable;

    private volatile static boolean reportEnable;

    static {
        timeStampLock = new ReentrantReadWriteLock();
        timeStampDiv = 1;
        triggerEnable = false;
        reportEnable = false;
    }

    public static int getTimeStampDiv() {
        return timeStampDiv;
    }

    public static void setTimeStampDiv(int timeStampDiv) {
        if(timeStampDiv<=0) return;
        SystemParam.timeStampDiv = timeStampDiv;
        System.out.printf("set success : %d \n", timeStampDiv);
    }

    public static boolean isTriggerEnable() {
        return triggerEnable;
    }

    public static void setTriggerEnable(boolean triggerEnable) {
        SystemParam.triggerEnable = triggerEnable;
    }

    public static boolean isReportEnable() {
        return reportEnable;
    }

    public static void setReportEnable(boolean reportEnable) {
        SystemParam.reportEnable = reportEnable;
    }

    public static String getDesc() {
        return "{\n" +
                "   SystemParam:{\n" +
                "       timeStampDiv:"+timeStampDiv+",\n" +
                "       triggerEnable:"+triggerEnable+",\n" +
                "       reportEnable:"+reportEnable+"\n" +
                "   }\n" +
                "}";
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(2000);

        for(int i=0;i<1000;i++){
            int finalI = i;
            new Thread(()->{
                try {
                    latch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setTimeStampDiv(finalI);
                latch2.countDown();
            }).start();
        }

        for(int i=0;i<1000;i++){
            new Thread(()->{
                try {
                    latch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("get success : %d \n", getTimeStampDiv());
                latch2.countDown();
            }).start();
        }

        //开闸
        latch1.countDown();
        //等待线程全部执行完毕
        latch2.await();
    }
}
