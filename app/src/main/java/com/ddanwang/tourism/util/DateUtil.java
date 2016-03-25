package com.ddanwang.tourism.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 字符串公共处理类
 *
 * @author rui
 */
public class DateUtil {

    private static final String TAG = "DateUtil";
    private static double EARTH_RADIUS = 6378.137;
    public static final String DATE_TYPE1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TYPE2 = "yyyyMMddHHmmss";
    public static final String DATE_TYPE3 = "yyyy-MM-dd";
    public static final String DATE_TYPE4 = "MM月dd日";
    public static final String DATE_TYPE5 = "yyyyMM";
    public static final String DATE_TYPE6 = "yyyy";
    public static final String DATE_TYPE7 = "MM";
    public static final String DATE_TYPE8 = "yyyy年MM月dd日 HH:mm";
    public static final String DATE_TYPE9 = "MM/dd";
    public static final String DATE_TYPE10 = "yyyy/MM/dd";
    public static final String DATE_TYPE18 = "yyyy年MM月dd日 HH:mm:ss";

    public static final String DATE_TYPE11 = "MM/ddE";
    public static final String DATE_TYPE12 = "MM/dd日  E";

    public static final String DATE_TYPE13 = "HH:mm";
    public static final String DATE_TYPE14 = "yyyy年MM月dd日";
    public static final String DATE_TYPE15 = "MM月dd日 HH:mm";
    public static final String DATE_TYPE16 = "yyyy-MM-dd HH:mm";
    public static final String DATE_TYPE17 = "HH:mm:ss";
    public static final String DATE_TYPE19 = "yyyy年MM月";

    public static final String DATE_TYPE20 = "yyyyMMdd";
    public static final String DATE_TYPE21 = "yyyy年";
    public static final String DATE_TYPE22 = "dd";
    public static final String DATE_TYPE23 = "HH";
    public static final String DATE_TYPE24 = "MM月";

    private static DecimalFormat fnum1 = new DecimalFormat("#,##0.00"); // 精确到小数点后两位，整数部分三位分隔
    private static DecimalFormat fnum2 = new DecimalFormat(",###"); // 整数三位分隔

    /**
     * 获取指定格式时间 用于命名或者时间处理
     *
     * @return
     */
    public static String getDateFormat(String dateType) {
        Date date = new Date();
        Long nowTime = date.getTime();
        String strTime = "";
        Timestamp a = new Timestamp(nowTime);
        SimpleDateFormat df = new SimpleDateFormat(dateType);
        strTime = df.format(a);
        return strTime;

    }


    /**
     * 获取指定格式时间 用于命名或者时间处理
     *
     * @return
     */
    public static String getDateFormat(Date date, String dateType) {
        if (date != null) {
            Long nowTime = date.getTime();
            String strTime = "";
            Timestamp a = new Timestamp(nowTime);
            SimpleDateFormat df = new SimpleDateFormat(dateType);
            strTime = df.format(a);
            return strTime;
        } else {
            return "";
        }
    }

    /*
    * 将当前日期加减n天数。 如传入整型-5 意为将当前日期减去5天的日期 如传入整型5 意为将当前日期加上5天后的日期 返回字串 例(19990203)
    */
    public static Date dateAdd(int days) {
        // 日期处理模块 (将日期加上某些天或减去天数)返回字符串
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        return canlendar.getTime();
    }

    /**
     * 得到几天后的时间
     */

    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 判断时间大小
     *
     * @param dt1
     * @param dt2
     * @return
     */
    public static int compareDate(Date dt1, Date dt2) {
        if (dt1.getTime() > dt2.getTime()) {
            System.out.println(dt1 + "在 " + dt2 + " 前");
            return 1;
        } else if (dt1.getTime() < dt2.getTime()) {
            System.out.println(dt1 + "在" + dt2 + "后");
            return -1;
        } else {//相等
            return 0;
        }
    }

    /**
     * 获取指定格式时间 用于命名或者时间处理
     *
     * @return
     */
    public static String getDateFormatByType(String dateType) {
        Date date = new Date();
        if (date != null) {
            Long nowTime = date.getTime();
            String strTime = "";
            Timestamp a = new Timestamp(nowTime);
            SimpleDateFormat df = new SimpleDateFormat(dateType);
            strTime = df.format(a);
            return strTime;
        } else {
            return "";
        }
    }


    /**
     * 处理图片文件在SD卡上面的路径
     *
     * @param str
     * @return
     */
    public static String getFileUrl(String str) {
        String fileName = "";
        if (!TextUtils.isEmpty(str) && str.contains("/")) {
            String[] spitStr = str.split("/");
            fileName = spitStr[spitStr.length - 1];
        }
        return fileName;
    }

    /**
     * get window x,y
     *
     * @param context
     * @return dm DisplayMetrics
     */
    public static DisplayMetrics getWindowXY(Context context) {
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static Date getDateFormat(String strDate, String type) {
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat(type);
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {

        return str == null || "".equals(str);
    }

    public static String getCommonFormatDate(String date) {
        String strDate = "";
        if (DateUtil.getDateFormat(DateUtil.DATE_TYPE3).equals(
                DateUtil.getDateFormat(DateUtil.getDateFormat(date, DateUtil.DATE_TYPE1), DateUtil.DATE_TYPE3))) {
            strDate = DateUtil.getDateFormat(DateUtil.getDateFormat(date, DateUtil.DATE_TYPE1), DateUtil.DATE_TYPE17);
        } else {
            strDate = DateUtil.getDateFormat(DateUtil.getDateFormat(date, DateUtil.DATE_TYPE1), DateUtil.DATE_TYPE3);
        }
        return strDate;
    }

}
