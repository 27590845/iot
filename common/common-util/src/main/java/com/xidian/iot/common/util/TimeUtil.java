package com.xidian.iot.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 时间相关的工具类
 * @author: Hansey
 * @date: 2020-09-20 10:10
 */
public class TimeUtil {

    /**
     * @description 给定日期获取时间戳，若date为null则返回当前日期时间戳
     * @param date 要操作的日期对象，默认为当前日期
     * @return
     */
    public static long getTimeStamp(Date date) {
        if(date == null) date = new Date();
        long times = date.getTime();
        return times;
    }

    /**
     * @description 给定日期字符串获取时间戳，若date为null则返回当前日期时间戳
     * @param dateStr 日期字符串，非空
     * @param pattern 格式字符串，默认根据time格式而定义，无法根据time格式定义时返回null
     * @return
     */
    public static long getTimeStamp(String dateStr, String pattern){
        return getTimeStamp(getDate(dateStr, pattern));
    }

    /**
     * @description 获取格式化的时间
     * @param date 要格式化的日期对象，默认为当前日期
     * @param pattern 格式字符串，默认为"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getFormatDate(Date date, String pattern) {
        if(date == null) date = new Date();
        if(pattern == null) pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateStr = formatter.format(date);
        return dateStr;
    }

    /**
     * @description 根据时间戳获取格式化日期字符串
     * @param timeStamp 要格式化的时间戳，默认为当前日期
     * @param pattern 格式字符串，默认为"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getFormatDate(long timeStamp, String pattern){
        return getFormatDate(new Date(timeStamp), pattern);
    }

    /**
     * @description 根据日期字符串获取日期
     * @param dateStr 日期字符串，非空
     * @param pattern 格式字符串，默认根据time格式而定义，无法根据time格式定义时返回null
     * @return
     */
    public static Date getDate(String dateStr, String pattern) {
        if(StringUtils.isBlank(dateStr)) return null;
        if(StringUtil.isBlank(pattern)) {
            if (Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", dateStr))
                pattern = "yyyy-MM-dd HH:mm:ss";
            else if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", dateStr))
                pattern = "yyyy-MM-dd";
            else
                return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description 根据时间戳获取日期
     * @param timeStamp
     * @return
     */
    public Date getDate(long timeStamp){
        return new Date(timeStamp);
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
     * @description 获取过去第几月的最后一天
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
     * @description 获取过去几个小时的日期
     * @param nowDate
     * @param past
     * @return
     */
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

    /**
     * 判断给定时间戳是否在当前时间的i毫秒之前
     * @param time
     * @param i
     * @return
     */
    public static boolean isExceededInterval(long time, long i) {
        long now = getTimeStamp(null);
        long diff = (now - time);
        return diff >= i;
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
