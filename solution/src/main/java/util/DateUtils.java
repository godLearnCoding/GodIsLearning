package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * <pre>
 * 作者：shenliang
 * 项目：
 * 类说明：时间转换类
 * 日期：2018年10月11日
 * 备注：
 * </pre>
 */
public class DateUtils {
  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
  private static final SimpleDateFormat sdfmat = new SimpleDateFormat("yyyy-MM-dd");
  private static final SimpleDateFormat datesdfmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  /**
   *
   * @param date
   * @param time
   * @return yyyy-MM-dd HH:mm:ss
   */
  public static String getStrbyStr(String date, String time) {
    String dates = "";
    if (null == date || date.length() == 0) {
      return "";
    }
    if (null != date &&  date.length() != 0) {
      dates = getStrbyStr(date) + " " + time;
      return dates;
    }
    return getStrbyStr(date);
  }

  /**
   *
   * @param date yyyyMMdd
   * @return yyyy-MM-dd
   */
  public static String getStrbyStr(String date) {
    String dates = "";
    try {
      Date format = sdf.parse(date);
      dates = sdfmat.format(format);
    } catch (ParseException e) {
    }
    return dates;
  }

  /**
   * 格式转换
   *
   * @param date
   * @return yyyyMMdd
   */
  public static Date getDatebyString(String date) {
    Date format = null;
    try {
      format = sdf.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return format;
  }

  /**
   * 格式转换
   *
   * @param date
   * @return yyyy-MM-dd HH:mm:ss
   */
  public static String getYMDString(Date date) {
    String format = datesdfmat.format(date);
    return format;
  }

  /**
   *
   * @param date
   * @return yyyyMMdd
   */
  public static String getDateFormatString(Date date) {
    String format = sdf.format(date);
    return format;
  }

  /**
   * 格式转换
   *
   * @param date
   * @return yyyy-MM-dd
   */
  public static String getDateForStr(Date date) {
    String format = sdfmat.format(date);
    return format;
  }

  /**
   * 格式转换
   *
   * @param
   *         date   yyyy-MM-dd转换成yyyyMMdd
   * @return
   */
  public static String getStrFormatStr(String date) {
    Date d = null;
    try {
      d = sdfmat.parse(date);
      String format = sdf.format(d);
      return format;
    } catch (ParseException e) {

    }
    return "";
  }

  /**
   * 天数间隔
   *
   * @param before
   * @param end
   * @return
   */
  public static int getDaybetweenDay(String before, String end) {
    Date beforeDate = null;
    Date endDate = null;
    try {
      beforeDate = sdfmat.parse(before);
      endDate = sdfmat.parse(end);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    int a = (int) ((endDate.getTime() - beforeDate.getTime()) / (1000 * 3600 * 24));
    return a;
  }

  /**
   * 根据天数间隔查询当前日期N天前的日期
   *
   * @param day
   * @return
   */
  public static String getDayBeforeDay(String day) {
    Calendar c = Calendar.getInstance();
    int a = Integer.valueOf(day);
    c.setTime(new Date());
    c.add(Calendar.DATE, -a);
    Date d = c.getTime();
    String beforeDay = sdf.format(d);
    return beforeDay;
  }

  /**
   * 根据天数间隔查询当前日期N天前的日期
   *
   * @param day
   * @return
   */
  public static String getYYYY_MM_ddBeforeDay(String day) {
    Calendar c = Calendar.getInstance();
    int a = Integer.valueOf(day);
    c.setTime(new Date());
    c.add(Calendar.DATE, -a);
    Date d = c.getTime();
    String beforeDay = sdfmat.format(d);
    return beforeDay;
  }

  /**
   * 根据月份间隔查询当前日期N月前的日期
   *
   * @param day
   * @return
   */
  public static String getDayBeforeMonth(String day) {
    Calendar c = Calendar.getInstance();
    int a = Integer.valueOf(day);
    c.setTime(new Date());
    c.add(Calendar.MONDAY, -a);
    Date d = c.getTime();
    String beforeDay = sdf.format(d);
    return beforeDay;
  }

  /**
   * 根据年份间隔查询当前日期的日期
   *
   * @param day
   * @return
   */
  public static String getDayBeforeYear(String day) {
    Calendar c = Calendar.getInstance();
    int a = Integer.valueOf(day);
    c.setTime(new Date());
    c.add(Calendar.YEAR, -a);
    Date d = c.getTime();
    String beforeDay = sdf.format(d);
    return beforeDay;
  }

  // 获取日期的所属季度
  public static int getSeason(Date date) {
    int season = 0;
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int month = c.get(Calendar.MONTH);
    switch (month) {
      case Calendar.JANUARY:
      case Calendar.FEBRUARY:
      case Calendar.MARCH:
        season = 1;
        break;
      case Calendar.APRIL:
      case Calendar.MAY:
      case Calendar.JUNE:
        season = 2;
        break;
      case Calendar.JULY:
      case Calendar.AUGUST:
      case Calendar.SEPTEMBER:
        season = 3;
        break;
      case Calendar.OCTOBER:
      case Calendar.NOVEMBER:
      case Calendar.DECEMBER:
        season = 4;
        break;
      default:
        break;
    }
    return season;
  }

  /**
   * 获取某季度最后一天日期
   *
   * @param year
   * @param startSeason
   * @return
   */
  public static Date getSeasonEndDate(int year, int startSeason) {
    Calendar temp = Calendar.getInstance();
    switch (startSeason) {
      case 1:
        temp.set(year, Calendar.MARCH + 1, 0);
        break;
      case 2:
        temp.set(year, Calendar.JUNE + 1, 0);
        break;
      case 3:
        temp.set(year, Calendar.SEPTEMBER + 1, 0);
        break;
      case 4:
        temp.set(year, Calendar.DECEMBER + 1, 0);
        break;

      default:
        break;
    }
    return temp.getTime();
  }

  public static Date getSeasonStartDate(int year, int startSeason) {
    Calendar temp = Calendar.getInstance();
    switch (startSeason) {
      case 1:
        temp.set(year, Calendar.JANUARY, 1);
        break;
      case 2:
        temp.set(year, Calendar.APRIL, 1);
        break;
      case 3:
        temp.set(year, Calendar.JULY, 1);
        break;
      case 4:
        temp.set(year, Calendar.OCTOBER, 1);
        break;

      default:
        break;
    }
    return temp.getTime();
  }

  /**
   * 获取本年度首日日期
   *
   * @return
   */
  public static Date getFirstDayOfYear() {
    Calendar calendar = Calendar.getInstance();
    // calendar.setTime(new Date());
    int year = calendar.get(Calendar.YEAR);
    calendar.set(year, Calendar.JANUARY, 1);
    return calendar.getTime();
  }

  /**
   * 获取本年度末日日期
   *
   * @return
   */
  public static Date getLastDayOfYear() {
    Calendar calendar = Calendar.getInstance();
    // calendar.setTime(new Date());
    int year = calendar.get(Calendar.YEAR);
    calendar.set(year, Calendar.DECEMBER, 31);
    return calendar.getTime();
  }

  public static Date getFirstDayOfYear(int year) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, Calendar.JANUARY, 1);
    return calendar.getTime();
  }

  public static Date getLastDayOfYear(int year) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, Calendar.DECEMBER, 31);
    return calendar.getTime();
  }

  /**
   * 获取日期列表(currentDate倒推days天数)
   *
   * @param currentDate
   * @param days
   * @return
   */
  public static List<Date> getDateListBefore(Date currentDate, long days) {
    List<Date> dateList = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentDate);
    for (int i = 0; i < days; i++) {
      calendar.add(Calendar.DAY_OF_MONTH, -1);
      dateList.add(calendar.getTime());
    }
    return dateList;
  }

  public static void main(String[] args) {

    System.out.println(getStrbyStr("20190409", "00:00:50"));
  }
}
