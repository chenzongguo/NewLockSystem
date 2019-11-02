package cn.njthl.HotelClean.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @描述 时间工具（需要joda-time）
 */
public class TimeUtils {

    /**
     * 获取当前系统时间
     *
     * @Description:
     * @Author 13050629
     * @Date 2014-7-28
     */
    public static String getDateTime6(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ,Locale.CHINA);
        String str = format.format(date);
        return str;
    }

    /**
     * 获取当前系统时间
     *
     * @Description:
     * @Author 13050629
     * @Date 2014-7-28
     */
    public static String getDateTime2(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm" ,Locale.CHINA);
        String str = format.format(date);
        return str;
    }

    /**
     * 获取当前系统时间
     *
     * @Description:
     * @Author 13050629
     * @Date 2014-7-28
     */
    public static String getDateTime16(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss" ,Locale.CHINA);
        String str = format.format(date);
        return str;
    }
//
//    /**
//     * 得到仿微信日期格式输出
//     *
//     * @param msgTimeMillis
//     * @return
//     */
//    public static String getMsgFormatTime(long msgTimeMillis) {
//        DateTime nowTime = new DateTime();
////        LogUtils.sf("nowTime = " + nowTime);
//        DateTime msgTime = new DateTime(msgTimeMillis);
////        LogUtils.sf("msgTime = " + msgTime);
//        int days = Math.abs(Days.daysBetween(msgTime, nowTime).getDays());
////        LogUtils.sf("days = " + days);
//        if (days < 1) {
//            //早上、下午、晚上 1:40
//            return getTime(msgTime);
//        } else if (days == 1) {
//            //昨天
//            return "昨天 " + getTime(msgTime);
//        } else if (days <= 7) {
//            //星期
//            switch (msgTime.getDayOfWeek()) {
//                case DateTimeConstants.SUNDAY:
//                    return "周日 " + getTime(msgTime);
//                case DateTimeConstants.MONDAY:
//                    return "周一 " + getTime(msgTime);
//                case DateTimeConstants.TUESDAY:
//                    return "周二 " + getTime(msgTime);
//                case DateTimeConstants.WEDNESDAY:
//                    return "周三 " + getTime(msgTime);
//                case DateTimeConstants.THURSDAY:
//                    return "周四 " + getTime(msgTime);
//                case DateTimeConstants.FRIDAY:
//                    return "周五 " + getTime(msgTime);
//                case DateTimeConstants.SATURDAY:
//                    return "周六 " + getTime(msgTime);
//            }
//            return "";
//        } else {
//            //12月22日
//            return msgTime.toString("MM月dd日 " + getTime(msgTime));
//        }
//    }
//
//    @NonNull
//    private static String getTime(DateTime msgTime) {
//        int hourOfDay = msgTime.getHourOfDay();
//        String when;
//        if (hourOfDay >= 18) {//18-24
//            when = "晚上";
//        } else if (hourOfDay >= 13) {//13-18
//            when = "下午";
//        } else if (hourOfDay >= 11) {//11-13
//            when = "中午";
//        } else if (hourOfDay >= 5) {//5-11
//            when = "早上";
//        } else {//0-5
//            when = "凌晨";
//        }
//        return when + " " + msgTime.toString("hh:mm");
//    }
public static String DateTimeToString(String _date) {
    if(_date ==null){
        return "";
    }
    String time [] = _date.split(",");
    _date = time[time.length-1];
    String text = "";
    String result;

    if (_date != null) {
        try {
            if (_date != null) {
                _date = _date.trim();
                String format;
                String format2;
                if (_date.length() == 8) {
                    format = "yyyyMMdd";
                    format2 = "yyyy-MM-dd";
                }
                else if (_date.length() == 12) {
                    format = "yyyyMMddHHmm";
                    format2 = "yyyy-MM-dd HH:mm";
                }
                else {
                    if (_date.length() != 14) {
                        result = text;
                        return result;
                    }
                    format = "yyyyMMddHHmmss";
                    format2 = "yyyy-MM-dd HH:mm:ss";
                }
                SimpleDateFormat sdFormat1 = new SimpleDateFormat(format, Locale.getDefault());
                SimpleDateFormat sdFormat2 = new SimpleDateFormat(format2, Locale.getDefault());
                Date date = sdFormat1.parse(_date);
                text = sdFormat2.format(date);
            }
        }
        catch (Exception var_4_B7) {
        }
    }
    result = text;
    return result;
}
}
