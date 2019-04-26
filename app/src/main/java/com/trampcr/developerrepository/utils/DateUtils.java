package com.trampcr.developerrepository.utils;

import android.content.Context;
import android.text.TextUtils;

import com.trampcr.developerrepository.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by trampcr on 2018/10/24.
 */

public class DateUtils {
    public static final String UNKNOWN_APP_INSTALL_DATE = "未知";

    public static final long UNIT_SECOND = 1000;
    public static final long UNIT_MINUTE = UNIT_SECOND * 60;
    public static final long UNIT_HOUR = UNIT_MINUTE * 60;
    public static final long UNIT_DAY = UNIT_HOUR * 24;
    public static final long ONE_DAY = 1000 * 60 * 60 * 24;

    public static String formatDateSimple(long date) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        return simpledateformat.format(date);
    }

    public static Date getTodayZeroPoint() {
        GregorianCalendar now = new GregorianCalendar();
        return new GregorianCalendar(now.get(GregorianCalendar.YEAR), now.get(GregorianCalendar.MONTH),
                now.get(GregorianCalendar.DAY_OF_MONTH)).getTime();
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     */
    public static boolean isDateOneBigger(long currentTime, long lastTime) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentT = sdf.format(currentTime);
        String lastT = sdf.format(lastTime);

        if (TextUtils.isEmpty(currentT) || TextUtils.isEmpty(lastT)) {
            return false;
        }
        Date dateCurrent = null;
        Date dateLast = null;
        try {
            dateCurrent = sdf.parse(currentT);
            dateLast = sdf.parse(lastT);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dateCurrent != null && dateLast != null) {
            if (dateCurrent.getTime() > dateLast.getTime()) {
                isBigger = true;
            } else if (dateCurrent.getTime() < dateLast.getTime()) {
                isBigger = false;
            }
        }

        return isBigger;
    }

    public static Date getYesterdayZeroPoint(Date goleDay){
        GregorianCalendar now = new GregorianCalendar();
        now.setTime(goleDay);
        if (now.get(Calendar.HOUR_OF_DAY) == 0 && now.get(Calendar.MINUTE) == 0 && now.get(Calendar.SECOND) == 0){
            return  new Date(now.getTimeInMillis() - ONE_DAY);
        }else{
            return  new Date(now.getTimeInMillis() - now.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000
                    - now.get(Calendar.MINUTE) * 60 * 1000 - now.get(Calendar.SECOND) *1000 - ONE_DAY);
        }
    }

    public static boolean isToday(long timeInMillis) {
        boolean flag = false;
        Calendar today = Calendar.getInstance();
        Calendar cale = Calendar.getInstance();
        cale.setTimeInMillis(timeInMillis);
        flag = cale.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                && cale.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                && cale.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH);
        return flag;
    }


    /**
     * 获取两个时间相差多少天
     * @param time1
     * @param time2
     * @return
     */
    public static long getDayDiff(long time1, long time2){
        return Math.abs(time2 - time1) / (1000 * 3600 * 24);
    }


    /**
     * 从2014年开始到现在多少天
     * @return
     */
    public static int getDaysFromThisYear() {
        int days = (int) ((System.currentTimeMillis() - 1388505600000L) / ONE_DAY);
        return days;
    }


    private static SimpleDateFormat sDATE_FMT_DEFAULT = null;
    private static SimpleDateFormat sDATE_FMT_EN = null;

    public static synchronized String getDateFormatStandard(Context context, long time, LanguageCountry language) {
        Date date = new Date(time);
        if (date.getYear() != 70) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(time));
            Calendar now = Calendar.getInstance();

            if (now.get(Calendar.YEAR) == c.get(Calendar.YEAR)) {
                if(now.get(Calendar.DAY_OF_YEAR) == c.get(Calendar.DAY_OF_YEAR)) {
                    return context.getString(R.string.today);
                } else if(now.get(Calendar.DAY_OF_YEAR) - c.get(Calendar.DAY_OF_YEAR) == 1) {
                    return context.getString(R.string.yesterday);
                }
            }


            if (language.getLanguage().equals(LanguageCountry.LANGUAGE_OPTION_EN)) {
                if (null == sDATE_FMT_EN) {
                    sDATE_FMT_EN = new SimpleDateFormat("MMM dd, yyyy", new Locale("en"));
                }

                return sDATE_FMT_EN.format(time);
            } else {

                if (null == sDATE_FMT_DEFAULT)
                    sDATE_FMT_DEFAULT = new SimpleDateFormat("yyyy-MM-dd");

                return sDATE_FMT_DEFAULT.format(time);
            }
        }
        return context.getString(R.string.unknown_app_install_date);
    }

    public static synchronized String getDateFormatStandard2(Context context, long time) {
        Date date = new Date(time);
        if (date.getYear() != 70) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(time));

            if (null == sDATE_FMT_DEFAULT) {
                sDATE_FMT_DEFAULT = new SimpleDateFormat("yyyy-MM-dd");
            }

            return sDATE_FMT_DEFAULT.format(time);
        }
        String msg = "";
        try {
            msg = context.getString(R.string.unknown_app_install_date);
        }catch (Throwable b) {
            msg = UNKNOWN_APP_INSTALL_DATE;
        }
        return msg;
    }

    public static String formatTime(long time, Context context) {
        if (context == null) {
            return null;
        }
        String str;
        if (time >= UNIT_DAY) {
            str = context.getString(R.string.date_unit_day, time / UNIT_DAY);
        } else if (time >= UNIT_HOUR) {
            str = context.getString(R.string.date_unit_hour, time / UNIT_HOUR);
        } else if (time >= UNIT_MINUTE) {
            str = context.getString(R.string.date_unit_minute, time / UNIT_MINUTE);
        } else {
            str = context.getString(R.string.date_unit_second, time / UNIT_SECOND);
        }
        return str;
    }

    public static int getHourOfDay()
    {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return  hour;
    }

    public static boolean isCheckPeriod(long lastTimestamp, long intervalTimestamp) {
        long diffTimes = System.currentTimeMillis() - lastTimestamp;
        //考虑用户往前调整时间
        if (diffTimes > intervalTimestamp || diffTimes < 0)
            return true;

        return false;
    }
}
