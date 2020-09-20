package com.xidian.iot.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间相关的工具类
 * @author: Hansey
 * @date: 2020-09-20 10:10
 */
public class TimeUtil {
    /**
     * 获取毫秒级别的时间戳
     * 输出结果:1438692801766
     */
    public static long getTimeStamp() {
        Date date = new Date();
        long times = date.getTime();
        return times;
    }

    /**
     * @Description: 给定时间获取时间戳
     * @Param: [date]
     * @return: long
     */
    public static long getTimeStamp(Date date) {
        long times = date.getTime();
        return times;
    }
    /**
     * @Description: 给定时间获取时间戳
     * @Param: [date]
     * @return: long
     */
    public static Date stringToDate(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取格式化的时间
     * 输出格式：2015-08-04 20:55:35
     */
    public static String getFormatDate() {
        Date date = new Date();
        long times = date.getTime();//时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 将时间戳转化为标准时间
     * 输出：Tue Oct 07 12:04:36 CST 2014
     */
    public static Date timestampToDate() {
        long times = getTimeStamp();
        Date date = new Date(times);
        return date;
    }

    /**
     * 获取过去第几月的1号0点的
     *
     * @param past
     * @return
     */
    public static Date getPastMonthDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -past);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    /**
     * 获取未来第几月的1号0点的
     *
     * @param past
     * @return
     */
    public static Date getPastMonthDate(Date date,int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, past);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取过去第几月的最后一天
     *
     * @param past
     * @return
     */
    public static Date getPastMonthEndDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -past);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取月份
     *
     * @param
     * @return
     */
    public static String getMonth(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        return formatter.format(data) + "月";
    }

    /**
     * 获取年月份
     *
     * @param
     * @return
     */
    public static String getYearMonth(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM");
        return formatter.format(data) + "月";
    }

    public static Date getPastHoursDate(Date nowDate, int past) {
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(nowDate);//把当前时间赋给日历
        calendar.add(Calendar.HOUR_OF_DAY, -past);  //设置为前beforeNum小时
        return calendar.getTime();   //得到前beforeNum小时的时间
    }

    /**
     * @Description: 获取过去几分钟的日期
     * @Param: [nowDate, past]
     * @return: java.util.Date
     */
    public static Date getPastMinutesDate(Date nowDate, int past) {
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(nowDate);//把当前时间赋给日历
        calendar.add(Calendar.MINUTE, -past);// 5分钟之前的时间
        return calendar.getTime();   //得到前beforeNum小时的时间
    }

    /**
     * @Description: 获取过去几秒钟的日期
     * @Param: [nowDate, past]
     * @return: java.util.Date
     */
    public static Date getPastSecondsDate(Date nowDate, int past) {
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(nowDate);//把当前时间赋给日历
        calendar.add(Calendar.SECOND, -past);
        return calendar.getTime();   //得到前beforeNum小时的时间
    }

    public static boolean isExceededInterval(long time, long i) {
        long now = getTimeStamp();
        long diff = (now - time);
        return diff >= (i * 60 * 1000);
    }
    /**
     * @Description: 判断是不是今天
     * @Param: [mbt]
     * @return: boolean
     */
    public static boolean isToday(Date mbt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(mbt);
        Calendar calendar = Calendar.getInstance();
        int y1 = calendar.get(Calendar.YEAR);//获取年份
        int d1 = calendar.get(Calendar.DAY_OF_YEAR);//获取年中第几天
        int y2 = cal.get(Calendar.YEAR);
        int d2 = cal.get(Calendar.DAY_OF_YEAR);
        if (y1 == y2 && d1 == d2){//判断是不是同一年的同一天
            return true;
        }else {
            return false;
        }

    }
    /**
     * @Description: 主要得到套餐的结束时间 imt为月份 结束时间的格式是23:59:59
     * @Param: [mbt, imt]
     * @return: java.util.Date
     */
    public static Date getLastMonthDate(Date mbt, Integer imt) {
        //如果套餐的时间是12月或者24月正好一年直接加上年份然后时间变为23:59:59
        //如果套餐的时间不是年套餐就是30天为周期 直接加上30天
        Calendar cal = Calendar.getInstance();
        cal.setTime(mbt);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mbt);
        calendar.add(Calendar.MONTH, imt);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    /**
     * @Description: 判断相对于现在是不是过去
     * @Param: [mbt]
     * @return: boolean
     */
    public static boolean isPast(Date mbt) {
        Date now = new Date();
        long nowTime = now.getTime();
        long mbtLong = mbt.getTime();
        return mbtLong<nowTime;
    }
}
