package com.dreamfactory.hotelmanager.tools;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangpeidong on 16/4/8.
 */
public class TimeHelper {
    private static TimeHelper ourInstance = new TimeHelper();

    public static TimeHelper getInstance() {
        return ourInstance;
    }

    private TimeHelper() {
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**  * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss  *   * @param dateDate  * @return  */
    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param k
     * @return
     */
    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

//    /**
//     * 得到二个字符串日期间的间隔天数
//     */
//    public static String getStringDays(String sj1, String sj2) {
//        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
//        long day = 0;
//        try {
//            java.util.Date date = myFormatter.parse(sj1);
//            java.util.Date mydate = myFormatter.parse(sj2);
//            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
//        } catch (Exception e) {
//            return "";
//        }
//        return day + "";
//    }

    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        java.util.Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 两个字符串时间之间的差值（单位：秒）
     */
    public static long getStringSeconds(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0;
        try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) /1000;
        } catch (Exception e) {
            return 0;
        }
        return day ;
    }

//    /**
//     * 两个时间之间的差值（单位：秒）
//     *
//     * @param date1
//     * @param date2
//     * @return
//     */
//    public static long getSeconds(String date1, String date2) {
//        if (date1 == null || date1.equals(""))
//            return 0;
//        if (date2 == null || date2.equals(""))
//            return 0;
//        // 转换为标准时间
//        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        java.util.Date date = null;
//        java.util.Date mydate = null;
//        try {
//            date = myFormatter.parse(date1);
//            mydate = myFormatter.parse(date2);
//        } catch (Exception e) {
//        }
//        long day = (date.getTime() - mydate.getTime()) / 1000;
//        return day;
//    }

    public static boolean isNowBetweenDates(String begin,String end){
        String now =TimeHelper.getStringDateShort();
        if (!(TimeHelper.getDays(begin,now)>0)&&TimeHelper.getDays(now,end)<0)
            return true;
        else
            return false;
    }

    public static boolean isNowBetweenTwoTime(String begin,String end){
        String now =TimeHelper.getStringDate();
        if (!(TimeHelper.getStringSeconds(begin, now)>0)&&TimeHelper.getStringSeconds(now,end)<0)
            return true;
        else
            return false;
    }

}