package com.dts.qlnhanvien.base;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Rin
 */
public class DateUtil {
    private DateUtil() {
    }

    /**
     * @param format Kiểu định dạng：yyyy-MM-dd
     * @param time   13：1380123456789
     * @return Kết quả định dạng (yyyy-MM-dd)
     */
    public static String formatToString(String format, long time) {
        if (time == 0) {
            return "";
        }
        return new SimpleDateFormat(format).format(new Date(time));
    }

    /**
     * Chuyển đổi thời gian: chuyển đổi các ký tự ngày thành số nguyên dài
     *
     * @param format Kiểu định dạng：yyyy-MM-dd
     * @return 13 Số hợp lệ(1380123456789)
     */
    public static long formatToLong(String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        return Timestamp.valueOf(f.format(new Date())).getTime();
    }

    /**
     * Nhận năm nay
     *
     * @return yyyy (2016)
     */
    public static int getYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public static int getIntMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }

    /**
     * Nhận tháng hiện tại
     *
     * @return MM (06)
     */
    public static String getMonth() {
        Calendar cal = Calendar.getInstance();
        return new DecimalFormat("00").format(cal.get(Calendar.MONTH));
    }


    /**
     * Mô tả chức năng: Ngày định dạng
     *
     * @param dateStr String Ngày nhân vật
     * @param format  String Định dạng
     * @return Date Ngày
     */
    public static Date parseDate(String dateStr, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            String dt = dateStr.replaceAll("-", "/");
            dt = dateStr;
            if ((!dt.equals("")) && (dt.length() < format.length())) {
                dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]", "0");
            }
            Date date = dateFormat.parse(dt);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Mô tả chức năng: Ngày định dạng
     *
     * @param dateStr String Ngày ký tự: định dạng YYYY-MM-DD
     * @return Date
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd");
    }


    public static int getFirstDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
            calendar.add(Calendar.DATE, -1); // Substract 1 day until first day of week.
        }
        long firstDayOfWeekTimestamp = calendar.getTimeInMillis();
        return buildPartDate(firstDayOfWeekTimestamp);
    }

    public static Date parseDateTime(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Mô tả chức năng: Ngày đầu ra được định dạng
     *
     * @param date   Date
     * @param format String
     * @return
     */
    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                DateFormat dateFormat = new SimpleDateFormat(format);
                result = dateFormat.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * @param date Date
     * @return
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    /**
     * @param date Date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }


    public static String getDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String getDateMonthYear(Date date) {
        return format(date, "dd/MM/yyyy");
    }


    public static String getTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    public static String getDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date addDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }


    public static String add(String date, int day) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        long d = df.parse(date).getTime();
        long millis = d + ((long) day) * 24 * 3600 * 1000;
        return df.format(new Date(millis));
    }


    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    public static int diffHour(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (3600 * 1000));
    }


    public static String getMonthBegin(String strdate) {
        Date date = parseDate(strdate);
        return format(date, "yyyy-MM") + "-01";
    }

    public static String getMonthEnd(String strdate) {
        Date date = parseDate(getMonthBegin(strdate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 2);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime());
    }


    public static String formatDate(Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }


    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static int getDay(String beginDate, String endDate) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long to = df.parse(endDate).getTime();
        long from = df.parse(beginDate).getTime();
        return (int) ((to - from) / (1000 * 60 * 60 * 24));
    }


    public static int yearDateDiff(String startYear, String endYear) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        try {
            startDate.setTime(sdf.parse(startYear));
            endDate.setTime(sdf.parse(endYear));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR));
    }




    public static int buildPartYear(Timestamp date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String time = dateFormat.format(date);
        return Integer.parseInt(time);
    }

    public static int buildPartYear(Long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String date = dateFormat.format(time);
        return Integer.parseInt(date);
    }

    public static int buildPartTime(Long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
        String date = dateFormat.format(time);
        return Integer.parseInt(date);
    }

    public static String getStringPartTime(Date time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
        return dateFormat.format(time);
    }


    public static int buildPartDate(Long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(time);
        return Integer.parseInt(date);
    }

    public static int buildPartMonth(Long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String date = dateFormat.format(time);
        return Integer.parseInt(date);
    }

    public static Date parse(int partDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.parse(partDate + "");
    }

    public static int buildPartM(Long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        String date = dateFormat.format(time);
        return Integer.parseInt(date);
    }

    public static int buildPartDay(Long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String date = dateFormat.format(time);
        return Integer.parseInt(date);
    }

    public static Date nextDays(int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, amount);
        Date newYear = cal.getTime();
        return new Date(newYear.getTime());
    }

    public static Date nextDays(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, amount);
        Date newYear = cal.getTime();
        return new Date(newYear.getTime());
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 23, 59, 59);
        return calendar.getTime();
    }


}
