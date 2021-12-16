package org.conference.common.utils;

import com.google.common.collect.Lists;
import org.conference.common.constant.DateConstant;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtils {
    private static DateTimeFormatter getDateTimeFormatter(String dateFormat) {
        return DateTimeFormatter.ofPattern(dateFormat);
    }

    /**
     * LocalDate 转 Date
     */
    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转 Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDate
     */
    public static LocalDate date2LocalDate1(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date 转 localDate
     */
    public static LocalDate date2LocalDate2(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        return localDate;
    }


    /**
     * Date 转 LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * instant 转 date
     */
    public static Date instant2Date(Instant instant) {
        try {
            return new Date(instant.toEpochMilli());
        } catch (ArithmeticException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * 获取当前日期
     *
     * @return String
     */
    public static String getNowDate2Str(String dateFormat) {
        return LocalDateTime.now().format(getDateTimeFormatter(dateFormat));
    }

    /**
     * Date ----> String 指定日期格式化
     *
     * @return String
     */
    public static String formatDate2Str(Date date, String dateFormat) {
        return getDateTimeFormatter(dateFormat).format((date.toInstant()));
    }

    /**
     * String ----> LocalDate 指定日期，格式化
     *
     * @return String
     */
    public static LocalDate formatStr2LocalDate(String date, String dateFormat) {
        return LocalDate.parse(date, getDateTimeFormatter(dateFormat));
    }

    /**
     * 将日期格式转换成时间戳
     */
    public static String date2TimeStamp(Date date) {
        return new SimpleDateFormat(DateConstant.DATETIME_BAR_SS_FORMAT).format(date);
    }

    /**
     * 将时间戳转换成日期格式
     */
    public static String TimeStamp2date(Long  timeStamp) {
        return new SimpleDateFormat(DateConstant.DATETIME_BAR_SS_FORMAT).format(timeStamp);
    }
    /**
     * 将英文星期转换成数字
     *
     * @param enWeek 英文星期
     * @return int，如果数字小于0，则检查，看是否输入错误 or 入参为null
     */
    public static int enWeek2Num(String enWeek) {
        if (DateConstant.MONDAY.equals(enWeek)) {
            return 1;
        } else if (DateConstant.TUESDAY.equals(enWeek)) {
            return 2;
        } else if (DateConstant.WEDNESDAY.equals(enWeek)) {
            return 3;
        } else if (DateConstant.THURSDAY.equals(enWeek)) {
            return 4;
        } else if (DateConstant.FRIDAY.equals(enWeek)) {
            return 5;
        } else if (DateConstant.SATURDAY.equals(enWeek)) {
            return 6;
        } else if (DateConstant.SUNDAY.equals(enWeek)) {
            return 7;
        } else {
            return -1;
        }
    }

    /**
     * 在当前时间的基础上进行加减
     */
    public static Date getOperationNowDate(int year, int month, int day, float hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return operationDate(cal, year, month, day, hour, minute);
    }

    /**
     * 在指定的时间上对时间进行加减
     */
    public static Date getOperationDate(Date date, int year, int month, int day, float hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return operationDate(cal, year, month, day, hour, minute);
    }

    public static Date operationDate(Calendar cal, int year, int month, int day, float hour, int minute) {
        if (!"null".equals(String.valueOf(year))) {
            cal.add(Calendar.YEAR, year);
        }
        if (!"null".equals(String.valueOf(month))) {
            cal.add(Calendar.MONTH, month);
        }
        if (!"null".equals(String.valueOf(day))) {
            cal.add(Calendar.DAY_OF_YEAR, day);
        }
        if (!"null".equals(String.valueOf(hour))) {
            cal.add(Calendar.MINUTE, (int) (hour * 60));
        }
        if (!"null".equals(String.valueOf(minute))) {
            cal.add(Calendar.MINUTE, minute);
        }
        return cal.getTime();
    }


    /**
     * 判断字符串是否为日期字符串
     */
    public static boolean isDate(String date) {
        try {
            getDateTimeFormatter(DateConstant.DATETIME_BAR_SS_FORMAT).parse(date);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Date时间date1和date2的时间差-单位秒
     */
    public static long getDateSubtractSS(Date date1, Date date2) {
        long cha = (date2.getTime() - date1.getTime()) / 1000;
        return cha;
    }

    /**
     * String时间date1和date2的时间差-单位秒
     */
    public static long getDateSubtractSS(String date1, String date2) {
        long rs = 0l;
        try {
            LocalDateTime start = LocalDateTime.parse(date1, getDateTimeFormatter(DateConstant.DATETIME_BAR_SS_FORMAT));
            LocalDateTime end = LocalDateTime.parse(date2, getDateTimeFormatter(DateConstant.DATETIME_BAR_SS_FORMAT));
            Duration duration = Duration.between(end, start);
            rs = Math.abs(duration.getSeconds());
        } catch (Exception e) {
            rs = -1;
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Date时间date1和date2的时间差-单位分钟
     */
    public static int getDateSubtractMM(Date date1, Date date2) {
        long cha = date2.getTime() - date1.getTime();
        return (int) cha / (1000 * 60);
    }

    /**
     * String时间date1和date2的时间差 -单位分钟
     */
    public static long getDateSubtractMM(String date1, String date2) {
        long rs = getDateSubtractSS(date1, date2);
        if (rs > 0) {
            return rs / 60;
        }
        return rs;
    }

    /**
     * 时间date1和date2的时间差-单位小时
     */
    public static int getDateSubtractHH(Date date1, Date date2) {
        long cha = (date2.getTime() - date1.getTime()) / 1000;
        return (int) cha / (60 * 60);
    }

    /**
     * 时间date1和date2的时间差-单位小时
     */
    public static long getDateSubtractHH(String date1, String date2) {
        long rs = getDateSubtractSS(date1, date2);
        if (rs > 0) {
            return rs / 3600;
        }
        return rs;
    }

    /**
     * 时间date1和date2的时间差-单位天
     */
    public static long getDateSubtractDay(String date1, String date2) {
        long rs = getDateSubtractSS(date1, date2);
        if (rs > 0) {
            return rs / 86400;
        }
        return rs;
    }

    /**
     * 时间date1和date2的时间差-单位天
     */
    public static int getDateSubtractDay(Date date1, Date date2) {
        long cha = date2.getTime() - date1.getTime();
        return (int) cha / (1000 * 60 * 60 * 24);
    }

    /**
     * 时间date1和date2的时间差-单位月
     */
    public static int getDateSubtractMonth(String date1, String date2) {
        int result;
        try {
            LocalDate start = LocalDate.parse(date1, getDateTimeFormatter(DateConstant.DATE_BAR_DD_FORMAT));
            LocalDate end = LocalDate.parse(date2, getDateTimeFormatter(DateConstant.DATE_BAR_DD_FORMAT));
            int year1 = start.getYear();
            int month1 = start.getMonthValue();
            int year2 = end.getYear();
            int month2 = end.getMonthValue();
            if (year1 == year2) {
                result = month2 - month1;
            } else {
                result = 12 * (year2 - year1) + month2 - month1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    /**
     * 时间date1和date2的时间差-单位月
     */
    public static int getDateSubtractMonth(Date date1, Date date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH);
        if (year1 == year2) {
            result = month2 - month1;
        } else {
            result = 12 * (year2 - year1) + month2 - month1;
        }
        return result;
    }

    /**
     * 时间date1和date2的时间差-单位年
     */
    public static int getDateSubtractYear(String date1, String date2) {
        int result;
        try {
            LocalDate start = LocalDate.parse(date1, getDateTimeFormatter(DateConstant.DATE_BAR_DD_FORMAT));
            LocalDate end = LocalDate.parse(date2, getDateTimeFormatter(DateConstant.DATE_BAR_DD_FORMAT));
            int year1 = start.getYear();
            int year2 = end.getYear();
            result = year2 - year1;
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    /**
     * 时间date1和date2的时间差-单位年
     */
    public static int getDateSubtractYear(Date date1, Date date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        result = year2 - year1;
        return result;
    }


    /**
     * 获取俩个时间的差结果用时秒表示
     *
     * @return 几小时:几分钟:几秒钟
     * @Summary: 此处可以讲计算结果包装成一个结构体返回便于格式化
     */
    public static String getTimeSubtractOne(String date1, String date2) {
        String result = "";
        try {
            long sss = getDateSubtractSS(date1, date2);
            int hh = (int) sss / (60 * 60);
            int mm = (int) (sss - hh * 60 * 60) / (60);
            int ss = (int) (sss - hh * 60 * 60 - mm * 60);
            result = hh + ":" + mm + ":" + ss;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取俩个时间的查结果用时秒表示
     *
     * @return 几天-几小时:几分钟:几秒钟
     * @Summary:此处可以讲计算结果包装成一个结构体返回便于格式化
     */
    public static String getTimeSubtractTwo(String date1, String date2) {
        String result = "";
        try {
            long sss = getDateSubtractSS(date1, date2);
            int dd = (int) sss / (60 * 60 * 24);
            int hh = (int) (sss - dd * 60 * 60 * 24) / (60 * 60);
            int mm = (int) (sss - dd * 60 * 60 * 24 - hh * 60 * 60) / (60);
            int ss = (int) (sss - dd * 60 * 60 * 24 - hh * 60 * 60 - mm * 60);
            result = dd + "-" + hh + ":" + mm + ":" + ss;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断是否在某个时间段内
     */
    public static boolean isBetween(String startTime, String endTime, Date date) {
        try {
            LocalDateTime start = LocalDateTime.parse(startTime, getDateTimeFormatter(DateConstant.DATETIME_BAR_SS_FORMAT));
            LocalDateTime end = LocalDateTime.parse(endTime, getDateTimeFormatter(DateConstant.DATETIME_BAR_SS_FORMAT));
            return between(localDateTime2Date(start), localDateTime2Date(end), date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean between(Date startTime, Date endTime, Date date) {
        return date.after(startTime) && date.before(endTime);
    }


    /**
     * 检测：输入年份是否是闰年？
     *
     * @param date 日期格式：yyyy-MM-dd
     * @return true：闰年，false：平年
     */
    public static boolean isLeapYear(String date) {
        return LocalDate.parse(date.trim()).isLeapYear();
    }

    /**
     * 切割日期。按照周期切割成小段日期段。例如： <br>
     *
     * @param startDate 开始日期（yyyy-MM-dd）
     * @param endDate   结束日期（yyyy-MM-dd）
     * @param period    周期（天，周，月，年）
     * @return 切割之后的日期集合
     * @example <li>startDate="2019-02-28",endDate="2019-03-05",period="day"</li>
     * <li>结果为：[2019-02-28, 2019-03-01, 2019-03-02, 2019-03-03, 2019-03-04, 2019-03-05]</li><br>
     * <li>startDate="2019-02-28",endDate="2019-03-25",period="week"</li>
     * <li>结果为：[2019-02-28,2019-03-06, 2019-03-07,2019-03-13, 2019-03-14,2019-03-20,
     * 2019-03-21,2019-03-25]</li><br>
     * <li>startDate="2019-02-28",endDate="2019-05-25",period="month"</li>
     * <li>结果为：[2019-02-28,2019-02-28, 2019-03-01,2019-03-31, 2019-04-01,2019-04-30,
     * 2019-05-01,2019-05-25]</li><br>
     * <li>startDate="2019-02-28",endDate="2020-05-25",period="year"</li>
     * <li>结果为：[2019-02-28,2019-12-31, 2020-01-01,2020-05-25]</li><br>
     */
    public static List<String> getDateRange(String startDate, String endDate, String period) {
        List<String> result = Lists.newArrayList();
        LocalDate end = LocalDate.parse(endDate, getDateTimeFormatter(DateConstant.DATE_BAR_DD_FORMAT));
        LocalDate start = LocalDate.parse(startDate, getDateTimeFormatter(DateConstant.DATE_BAR_DD_FORMAT));
        LocalDate tmp = start;
        switch (period) {
            case DateConstant.DAY:
                while (start.isBefore(end) || start.isEqual(end)) {
                    result.add(start.toString());
                    start = start.plusDays(1);
                }
                break;
            case DateConstant.WEEK:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    if (tmp.plusDays(6).isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + tmp.plusDays(6));
                    }
                    tmp = tmp.plusDays(7);
                }
                break;
            case DateConstant.MONTH:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfMonth = tmp.with(TemporalAdjusters.lastDayOfMonth());
                    if (lastDayOfMonth.isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + lastDayOfMonth);
                    }
                    tmp = lastDayOfMonth.plusDays(1);
                }
                break;
            case DateConstant.YEAR:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfYear = tmp.with(TemporalAdjusters.lastDayOfYear());
                    if (lastDayOfYear.isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + lastDayOfYear);
                    }
                    tmp = lastDayOfYear.plusDays(1);
                }
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 计算两个日期字符串之间相差多少个周期（天，月，年）
     *
     * @param date1 yyyy-MM-dd
     * @param date2 yyyy-MM-dd
     * @param node  三者之一:(day，month,year)
     * @return 相差多少周期
     */
    public static int peridCount(String date1, String date2, String node) {
        date1 = date1.trim();
        date2 = date2.trim();
        if (DateConstant.DAY.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getDays();
        } else if (DateConstant.MONTH.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getMonths();
        } else if (DateConstant.YEAR.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getYears();
        } else {
            return 0;
        }
    }

    /**
     * 指定日期月的最后一天（yyyy-MM-dd）
     *
     * @param curDate     日期格式（yyyy-MM-dd）
     * @param firstOrLast true：第一天，false：最后一天
     */
    public static String getMonthFirstOrLastDay(String curDate, boolean firstOrLast) {
        if (firstOrLast) {
            return LocalDate.parse(curDate, getDateTimeFormatter(DateConstant.DATE_BAR_DD_FORMAT)).with(TemporalAdjusters.firstDayOfMonth()).toString();
        } else {
            return LocalDate.parse(curDate, getDateTimeFormatter(DateConstant.DATE_BAR_DD_FORMAT)).with(TemporalAdjusters.lastDayOfMonth()).toString();
        }
    }

    /**
     * 获取下一个星期的日期
     *
     * @param curDay          yyyy-MM-dd
     * @param dayOfWeek       monday:1~sunday:7
     * @param isContainCurDay 是否包含当天，true：是，false：不包含
     * @return 日期（yyyy-MM-dd）
     */
    public static String getNextWeekDate(String curDay, int dayOfWeek, boolean isContainCurDay) {
        dayOfWeek = dayOfWeek < 1 || dayOfWeek > 7 ? 1 : dayOfWeek;
        if (isContainCurDay) {
            return LocalDate.parse(curDay).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dayOfWeek))).toString();
        } else {
            return LocalDate.parse(curDay).with(TemporalAdjusters.next(DayOfWeek.of(dayOfWeek))).toString();
        }
    }

    /**
     * 获取上一个星期的日期
     *
     * @param curDay    指定日期（yyyy-MM-dd）
     * @param dayOfWeek 数字范围（monday:1~sunday:7）
     * @param isCurDay  是否包含当天，true：是，false：不包含
     * @return 日期（yyyy-MM-dd）
     */
    public static String getPreWeekDate(String curDay, int dayOfWeek, boolean isCurDay) {
        dayOfWeek = dayOfWeek < 1 || dayOfWeek > 7 ? 1 : dayOfWeek;
        if (isCurDay) {
            return LocalDate.parse(curDay).with(TemporalAdjusters.previousOrSame(DayOfWeek.of(dayOfWeek))).toString();
        } else {
            return LocalDate.parse(curDay).with(TemporalAdjusters.previous(DayOfWeek.of(dayOfWeek))).toString();
        }
    }

    /**
     * 获取指定日期当月的最后或第一个星期日期
     *
     * @param curDay      指定日期（yyyy-MM-dd）
     * @param dayOfWeek   周几（1~7）
     * @param lastOrFirst true：最后一个，false本月第一个
     * @return 日期（yyyy-MM-dd）
     */
    public static String getFirstOrLastWeekDate(String curDay, int dayOfWeek, boolean lastOrFirst) {
        dayOfWeek = dayOfWeek < 1 || dayOfWeek > 7 ? 1 : dayOfWeek;
        if (lastOrFirst) {
            return LocalDate.parse(curDay).with(TemporalAdjusters.lastInMonth(DayOfWeek.of(dayOfWeek))).toString();
        } else {
            return LocalDate.parse(curDay).with(TemporalAdjusters.firstInMonth(DayOfWeek.of(dayOfWeek))).toString();
        }
    }
}
