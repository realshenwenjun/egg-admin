/*
 * Created on 2006-7-4
 *
 */
package com.dskj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author q
 * 
 *         功能：
 */
public class DateUtil {
	public static final String normalTimeFormat = "yyyy-MM-dd HH:mm:ss";
	public static final String normalDateFormat = "yyyy-MM-dd";

	public static String formatDate(Date date) {
		String ret = "";

		SimpleDateFormat sdf = new SimpleDateFormat(normalDateFormat);
		try {
			ret = sdf.format(date);
		} catch (Exception e) {
		}

		return ret;
	}

	public static String formatTime(Date date) {
		String ret = "";

		SimpleDateFormat sdf = new SimpleDateFormat(normalTimeFormat);
		try {
			ret = sdf.format(date);
		} catch (Exception e) {
		}

		return ret;
	}

	public static String formatDate(Date date, String format) {
		String ret = "";

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			ret = sdf.format(date);
		} catch (Exception e) {
		}

		return ret;
	}

	public static Date parseDate(String dateString, String formate) {
		Date ret = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
			sdf.setLenient(false);
			ret = sdf.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static Date rollDate(int rollDateCount) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, rollDateCount);
		return cal.getTime();
	}

	public static Date rollDate(Date date, int rollDateCount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, rollDateCount);
		return cal.getTime();
	}

	public static String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}

	public static Date getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			return sdf.parse(sdf.format(cal.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getNowDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		return sdf.format(cal.getTime());
	}

	public static Date parseDate(String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(s);
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 作者：赵林
	 * 
	 * 创建日期：2010-04-21
	 * 
	 * 说明：获取上一日
	 * 
	 * 参数及返回值说明：
	 * 
	 * @return
	 */
	public static String[] getLastDay() {
		String[] result = new String[2];
		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		result[0] = formatDate(calendar.getTime());

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		result[1] = formatDate(calendar.getTime(), normalTimeFormat);

		return result;
	}

	/**
	 * 
	 * 作者：赵林
	 * 
	 * 创建日期：2010-08-10
	 * 
	 * 说明：获取月末最后一日
	 * 
	 * 参数及返回值说明：
	 * 
	 * @return
	 */
	public static String getMonthLastDay(String year, String month) {
		int _year = Integer.parseInt(year);
		int _month = Integer.parseInt(month.startsWith("0") ? month.substring(1) : month) - 1;

		String result = "";
		Calendar calendar = Calendar.getInstance();

		calendar.set(_year, _month, 1);
		calendar.add(GregorianCalendar.MONTH, 1);
		calendar.add(GregorianCalendar.DATE, -1);
		result = formatDate(new java.sql.Date(calendar.getTime().getTime())) + " 23:59:59";

		return result;
	}

	/**
	 * 
	 * 作者：张陶
	 * 
	 * 创建日期：2008-12-17
	 * 
	 * 说明：获取上一周, 自然周(从周日到周六)
	 * 
	 * 参数及返回值说明：
	 * 
	 * @return
	 */
	public static String[] getLastWeek() {
		String[] result = new String[2];
		Calendar calendar = Calendar.getInstance();
		int minus = calendar.get(GregorianCalendar.DAY_OF_WEEK);
		calendar.add(GregorianCalendar.DATE, -minus);
		calendar.set(GregorianCalendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		result[0] = formatDate(calendar.getTime());
		calendar.add(GregorianCalendar.DATE, 6);
		result[1] = formatDate(calendar.getTime());
		return result;
	}

	/**
	 * 
	 * 作者：张陶
	 * 
	 * 创建日期：2008-12-17
	 * 
	 * 说明：获取上一周，倒推7天
	 * 
	 * 参数及返回值说明：
	 * 
	 * @return
	 */
	public static String[] getLastNaturalWeek() {
		String[] result = new String[2];
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		result[1] = formatDate(calendar.getTime());
		calendar.add(GregorianCalendar.DATE, -7);
		result[0] = formatDate(calendar.getTime());
		return result;
	}

	/**
	 * 
	 * 作者：赵林
	 * 
	 * 创建日期：2010-08-04
	 * 
	 * 说明：获取一个月，自然月(1~月末)
	 * 
	 * 参数及返回值说明：
	 * 
	 * @return
	 */
	public static String[] getMonth(String year, String month) {

		int _year = Integer.parseInt(year);
		int _month = Integer.parseInt(month.startsWith("0") ? month.substring(1) : month) - 1;

		String[] result = new String[2];
		Calendar calendar = Calendar.getInstance();

		calendar.set(_year, _month, 1);
		result[0] = formatDate(new java.sql.Date(calendar.getTime().getTime()));

		calendar.add(GregorianCalendar.MONTH, 1);
		calendar.add(GregorianCalendar.DATE, -1);
		result[1] = formatDate(new java.sql.Date(calendar.getTime().getTime())) + " 23:59:59";

		return result;
	}

	/**
	 * 
	 * 作者：赵林
	 * 
	 * 创建日期：2010-08-04
	 * 
	 * 说明：获取完整月区间，自然月(1~月末)
	 * 
	 * 参数及返回值说明：
	 * 
	 * @return
	 */
	public static String[] getMonth(String startYear, String startMonth, String endYear, String endMonth) {

		int _startYear = Integer.parseInt(startYear);
		int _startMonth = Integer.parseInt(startMonth.startsWith("0") ? startMonth.substring(1) : startMonth) - 1;

		String[] result = new String[2];
		Calendar calendar = Calendar.getInstance();

		calendar.set(_startYear, _startMonth, 1);
		result[0] = formatDate(new java.sql.Date(calendar.getTime().getTime()));

		int _endYear = Integer.parseInt(endYear);
		int _endMonth = Integer.parseInt(endMonth.startsWith("0") ? endMonth.substring(1) : endMonth) - 1;

		calendar.set(_endYear, _endMonth, 1);
		calendar.add(GregorianCalendar.MONTH, 1);
		calendar.add(GregorianCalendar.DATE, -1);
		result[1] = formatDate(new java.sql.Date(calendar.getTime().getTime())) + " 23:59:59";

		return result;
	}

	/**
	 * 
	 * 作者：张陶
	 * 
	 * 创建日期：2008-12-17
	 * 
	 * 说明：获取上一月，自然月(1~月末)
	 * 
	 * 参数及返回值说明：
	 * 
	 * @return
	 */
	public static String[] getLastMonth() {
		String[] result = new String[2];
		Calendar calendar = Calendar.getInstance();

		calendar.set(calendar.get(GregorianCalendar.YEAR), calendar.get(GregorianCalendar.MONTH), 1);
		calendar.add(GregorianCalendar.DATE, -1);
		result[1] = formatDate(new java.sql.Date(calendar.getTime().getTime()));

		calendar.add(GregorianCalendar.DATE, -(calendar.get(GregorianCalendar.DATE) - 1));
		result[0] = formatDate(new java.sql.Date(calendar.getTime().getTime()));

		return result;
	}

	/**
	 * 
	 * 作者：张陶
	 * 
	 * 创建日期：2008-12-17
	 * 
	 * 说明：获取上一月，倒推30天
	 * 
	 * 参数及返回值说明：
	 * 
	 * @return
	 */
	public static String[] getLastNaturalMonth() {
		String[] result = new String[2];
		Calendar calendar = Calendar.getInstance();

		calendar.add(GregorianCalendar.DATE, -1);
		result[1] = formatDate(new java.sql.Date(calendar.getTime().getTime()));

		calendar.add(GregorianCalendar.DATE, -30);
		result[0] = formatDate(new java.sql.Date(calendar.getTime().getTime()));

		return result;
	}

	public static int getMonthSub(String date1, String date2) {
		String yearStr1 = date1.split("-")[0];
		String monthStr1 = date1.split("-")[1];
		String dayStr1 = date1.split("-")[2];
		int year1 = Integer.parseInt(yearStr1);
		int month1 = Integer.parseInt(monthStr1.startsWith("0") ? monthStr1.substring(1) : monthStr1);
		int day1 = Integer.parseInt(dayStr1.startsWith("0") ? dayStr1.substring(1) : dayStr1);

		String yearStr2 = date2.split("-")[0];
		String monthStr2 = date2.split("-")[1];
		String dayStr2 = date2.split("-")[2];
		int year2 = Integer.parseInt(yearStr2);
		int month2 = Integer.parseInt(monthStr2.startsWith("0") ? monthStr2.substring(1) : monthStr2);
		int day2 = Integer.parseInt(dayStr2.startsWith("0") ? dayStr2.substring(1) : dayStr1);

		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(year1, month1, day1);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(year2, month2, day2);

		int m = calendar2.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH);
		int y = calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);

		return y * 12 + m;
	}

	public static int daysBetween(Date now, Date returnDate) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(now);
		cReturnDate.setTime(returnDate);
		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays(intervalMs);
	}

	/**
	 * 说明：获取两个日期之间的天数差 date0 <= date1
	 */
	public static int getDaySub(String date0, String date1) {
		int result = -1;
		try {
			Calendar startDay = Calendar.getInstance();
			Calendar endDay = Calendar.getInstance();
			startDay.setTime(DateUtil.parseDate(date0));
			endDay.setTime(DateUtil.parseDate(date1));

			if (startDay.get(Calendar.YEAR) == endDay.get(Calendar.YEAR)) {
				return endDay.get(Calendar.DAY_OF_YEAR) - startDay.get(Calendar.DAY_OF_YEAR);
			} else {
				Calendar tmp = Calendar.getInstance();
				result = startDay.getActualMaximum(Calendar.DAY_OF_YEAR) - startDay.get(Calendar.DAY_OF_YEAR);
				for (int i = startDay.get(Calendar.YEAR) + 1; i < endDay.get(Calendar.YEAR); i++) {
					tmp.set(i, tmp.get(Calendar.MONTH), tmp.get(Calendar.DAY_OF_MONTH));
					result += tmp.getActualMaximum(Calendar.DAY_OF_YEAR);
				}
				result += endDay.get(Calendar.DAY_OF_YEAR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 说明：获取两个日期之间的天数差 getDaySub() 方法当不是同一年的时候会返回正数。
	 */
	public static int getDaySubReplace(String date0, String date1) {
		int result = -1;
		try {
			Calendar startDay = Calendar.getInstance();
			Calendar endDay = Calendar.getInstance();
			startDay.setTime(DateUtil.parseDate(date0));
			endDay.setTime(DateUtil.parseDate(date1));
			result = endDay.get(Calendar.DAY_OF_YEAR) - startDay.get(Calendar.DAY_OF_YEAR);
		} catch (Exception e) {
		}

		return result;
	}

	/**
	 * 
	 * 功能:
	 * <p>
	 * 作者 李双 Jul 4, 2012 2:33:55 PM
	 * 
	 * @param date0
	 * @param date1
	 * @return
	 */
	public static int getHoureSub(String date0, String date1) {
		int result = -1;
		try {
			Calendar startDay = Calendar.getInstance();
			Calendar endDay = Calendar.getInstance();
			startDay.setTime(parseDate(date0, normalTimeFormat));
			endDay.setTime(parseDate(date1, normalTimeFormat));
			// result= endDay.get(Calendar.HOUR_OF_DAY) -
			// startDay.get(Calendar.HOUR_OF_DAY);
			long res = (endDay.getTimeInMillis() - startDay.getTimeInMillis()) / (1000 * 60 * 60L);
			result = (int) res;
		} catch (Exception e) {
		}

		return result;
	}

	public static int getMinuteSub(String date0, String date1) {
		int result = -1;
		try {
			Calendar startDay = Calendar.getInstance();
			Calendar endDay = Calendar.getInstance();
			startDay.setTime(parseDate(date0, normalTimeFormat));
			endDay.setTime(parseDate(date1, normalTimeFormat));
			// result= endDay.get(Calendar.HOUR_OF_DAY) -
			// startDay.get(Calendar.HOUR_OF_DAY);
			long res = (endDay.getTimeInMillis() - startDay.getTimeInMillis()) / (1000 * 60L);
			result = (int) res;
		} catch (Exception e) {
		}

		return result;
	}

	public static long getMilliseconds(Date now, Date returnDate) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(now);
		cReturnDate.setTime(returnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return intervalMs;
	}

	private static int millisecondsToDays(long intervalMs) {
		return (int) (intervalMs / (1000 * 86400));
	}

	private static void setTimeToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}

	/**
	 * 
	 * 作者：张陶
	 * 
	 * 创建日期：2009-12-7
	 * 
	 * 说明：获取前半个小时
	 * 
	 * 参数及返回值说明：
	 * 
	 * @return
	 */
	public static String getLastHalfHour() {
		Calendar cNow = Calendar.getInstance();
		cNow.add(Calendar.MINUTE, -30);
		return formatDate(cNow.getTime(), normalTimeFormat);
	}

	/**
	 * 张陶 获取minute分钟前的时间
	 * 
	 * @param minute
	 * @return
	 */
	public static String getTimeBeforeMinutes(int minute) {
		Calendar cNow = Calendar.getInstance();
		cNow.add(Calendar.MINUTE, minute);
		return formatDate(cNow.getTime(), normalTimeFormat);
	}

	/**
	 * 
	 * @return
	 */
	public static ArrayList getMonthList() {
		ArrayList ml = new ArrayList();
		Calendar cal = Calendar.getInstance();
		cal.set(2006, 10, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar now = Calendar.getInstance();
		while (cal.before(now) || cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)) {
			ml.add(sdf.format(cal.getTime()));
			cal.add(Calendar.MONTH, 1);
		}

		return ml;
	}

	/**
	 * 说明：获取某日期前n天的日期
	 */
	public static String getBackFromDate(String date, int days) {

		String newDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.parseDate(date));
		calendar.add(Calendar.DAY_OF_YEAR, -days);
		newDate = DateUtil.formatDate(calendar.getTime());

		return newDate;
	}

	/**
	 * 说明：获取某日期后n天的日期
	 */
	public static String getForwardFromDate(String date, int days) {

		String newDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.parseDate(date));
		calendar.add(Calendar.DAY_OF_YEAR, days);
		newDate = DateUtil.formatDate(calendar.getTime());

		return newDate;
	}

	/**
	 * 
	 * 作者：赵林
	 * 
	 * 创建日期：2010-08-04
	 * 
	 * 说明：获取一个日期区间段内的所有日期
	 * 
	 * 参数及返回值说明：
	 * 
	 * @return
	 */
	public static List getDates(String date0, String date1) {

		List list = new ArrayList();

		Calendar calendar0 = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();

		calendar0.setTime(DateUtil.parseDate(date0));
		calendar1.setTime(DateUtil.parseDate(date1));

		while (calendar0.compareTo(calendar1) <= 0) {

			String date = DateUtil.formatDate(calendar0.getTime());
			list.add(date);

			calendar0.add(Calendar.DAY_OF_YEAR, 1);
		}

		return list;
	}

	/**
	 ** 说明：获取一个日期区间段内,每半小时时间间隔的所有时间段
	 * 
	 * 数及返回值说明：date0: 起始日期，date1:结束日期，startHour:每日起始小时，endHour：每日结束小时
	 */
	public static List getHalfHourDateList(String date0, String date1, int startHour, int endHour) {
		List list = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(sdf.parse(date0));
			c2.setTime(sdf.parse(date1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (endHour == 0)
			endHour = 24;
		while (c2.after(c1)) {
			if (c1.get(Calendar.HOUR_OF_DAY) >= endHour || c1.get(Calendar.HOUR_OF_DAY) < startHour) {
				c1.add(Calendar.MINUTE, 30);
				continue;
			}
			String[] date = new String[2];
			String startDate = formatDate(c1.getTime(), "yyyy-MM-dd HH:mm");
			c1.add(Calendar.MINUTE, 30);
			String endDate = formatDate(c1.getTime(), "yyyy-MM-dd HH:mm");
			date[0] = startDate;
			date[1] = endDate;
			list.add(date);
		}
		return list;
	}

	/**
	 * 比较两个时间的大小，Time1>Time2 返回1；Time1<Time2 返回-1；Time1=TIme2 返回0
	 * 
	 * @param Time1
	 * @param Time2
	 * @return
	 */
	public static int compareTime(String Time1, String Time2) {
		try {
			Date dt1 = parseDate(Time1, normalTimeFormat);
			Date dt2 = parseDate(Time2, normalTimeFormat);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * 功能:获取当前月初
	 * 
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return formatDate(calendar.getTime());
	}

	/**
	 * 
	 * 功能:根据条件获取 日期 例如2001-9 获取：2001-09-1
	 * 
	 * @param query
	 *            : 2001-9
	 * @return
	 */
	public static String getQueryFirstDayOfMonth(String query) {
		if (null != query && !query.trim().equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] day = query.split("-");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.parseInt(day[0]));
			calendar.set(Calendar.MONTH, Integer.parseInt(day[1]) - 1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			return sdf.format(calendar.getTime());
		}
		return "";
	}

	/**
	 * 
	 * 功能:根据条件获取 日期 例如2001-9 获取：2001-09-30
	 * 
	 * @param query
	 *            : 2001-9
	 * @return
	 */
	public static String getQueryLastDayOfMonth(String query) {
		if (null != query && !query.trim().equals("")) {
			String[] day = query.split("-");
			return getMonthLastDay(day[0], day[1]);
		}
		return "";
	}

	// 分割 传入yyyy-MM-dd HH:mm:ss 分割成1 yyyy-MM-dd 2 HH: 3 mm: 4 ss
	public static String[] splitDateTime(String time) {
		String[] result = { "", "", "", "" };
		if (time == null || time.length() < 1) {
			return result;
		}
		if (time.split(" ").length > 0) {
			result[0] = time.split(" ")[0];
			String hours = time.split(" ")[1];
			if (hours.split(":").length > 0) {
				result[1] = hours.split(":")[0];
				result[2] = hours.split(":")[1];
				result[3] = hours.split(":")[2];
			}
		}

		return result;
	}

	/**
	 * 
	 * 功能: 判断一个时间的 时辰是否在 某个范围之内
	 * <p>
	 * 作者 李双 Apr 6, 2012 2:10:32 PM
	 * 
	 * @param str
	 *            日期字符串
	 * @param start
	 *            开始范围
	 * @param end
	 *            结束范围
	 * @return
	 */
	public static boolean isRangeTime(Calendar ca, int start, int end) {
		if (start <= ca.get(Calendar.HOUR_OF_DAY) && ca.get(Calendar.HOUR_OF_DAY) < end) {
			return true;
		}
		return false;
	}

	/**
	 * 获取时间的年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat(normalDateFormat);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取时间的月 1-12
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat(normalDateFormat);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取时间的月 1-12
	 * 
	 * @param date
	 * @return
	 */
	public static int getDate(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat(normalDateFormat);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cal.get(Calendar.DATE);
	}

	/**
	 * 获得小时 24小时制
	 * 
	 * @param time
	 * @return
	 */
	public static int getHour(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat(normalTimeFormat);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取某个月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static int getLastDateByMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 获取上一个周日的日期
	 * 
	 * @return
	 */
	public static String getLastSunDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getNowDate());
		return DateUtil.formatDate(DateUtil.rollDate(-(cal.get(Calendar.DAY_OF_WEEK) - 1)));
	}

	/**
	 * 获取上个月最后一天的日期
	 * 
	 * @return
	 */
	public static String getLastMonthDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getNowDate());
		return DateUtil.formatDate(DateUtil.rollDate(-(cal.get(Calendar.DAY_OF_MONTH))));
	}

	/***
	 * 获取N个月前的月份,不包含当前月
	 * 
	 * @param monthNum
	 * @return
	 */
	public static String getPreMonth(int monthNum) {
		if (monthNum - (DateUtil.getMonth(DateUtil.getNow()) - 1) > 0) {
			// 跨年
			if ((monthNum - (DateUtil.getMonth(DateUtil.getNow()) - 1)) / 12 > 0) {
				// 跨多年
				return (DateUtil.getYear(DateUtil.getNow()) - ((monthNum - (DateUtil.getMonth(DateUtil.getNow()) - 1)) % 12 == 0 ? 0 : 1) - (monthNum - (DateUtil
						.getMonth(DateUtil.getNow()) - 1)) / 12) + "-" + (12 - (monthNum - DateUtil.getMonth(DateUtil.getNow())) % 12);
			} else {
				return (DateUtil.getYear(DateUtil.getNow()) - 1) + "-" + (12 - (monthNum - DateUtil.getMonth(DateUtil.getNow())));
			}
		} else {
			return DateUtil.getYear(DateUtil.getNow()) + "-" + ((DateUtil.getMonth(DateUtil.getNow()) - monthNum));
		}
	}

	/**
	 * 获取当前日期是一年中的第几周，按周一到周日为一周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekByYear(String date) {
		Date d = DateUtil.parseDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			week -= 1;
		}
		return week;
	}

	/**
	 * 功能：获得时间的long型
	 * 
	 * @param dateString
	 * @param formate
	 * @return
	 */
	public static long getTime(String dateString, String formate) {
		long time = 0;
		try {
			Date ret = null;
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
			ret = sdf.parse(dateString);
			time = ret.getTime();
		} catch (Exception e) {
		}
		return time;
	}

	/**
	 * 由单据号解析日期，code 单据号， numLength 末尾编号位数 只适用于 xx20120101xxxx 型
	 */
	public static String getDateByCode(String code, int numLength) {

		String date = getNowDateStr();

		int yearIndex = code.length() - numLength - 8;
		int month = code.length() - numLength - 4;
		int day = code.length() - numLength - 2;
		int num = code.length() - numLength;

		try {
			date = code.substring(yearIndex, month) + "-" + code.substring(month, day) + "-" + code.substring(day, num);
			if (parseDate(date) == null) {
				date = getNowDateStr();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 
	 * 功能:获取昨天日期
	 * <p>
	 * 作者 李双 Jan 18, 2013 4:00:59 PM
	 * 
	 * @return
	 */
	public static String getBeforOneDay() {
		return DateUtil.formatDate(DateUtil.rollDate(-1), normalDateFormat);
	}

	/**
	 * 取今天的当前小时数
	 * 
	 * @return 0-23
	 */
	public static int getHour() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	/**
	 * 获取当前日期的前几天
	 * 
	 * @param sDate
	 * @param iDays
	 * @return
	 */
	public static String getLateDate(String sDate, int iDays) {
		String sLateDate = "";
		Calendar calendar = Calendar.getInstance();
		try {
			String[] time = sDate.split(" ");
			String[] arrDate = time[0].split("-");
			int iYear = Integer.valueOf(arrDate[0]);
			int iMonth = Integer.valueOf(arrDate[1]);
			int iDay = Integer.valueOf(arrDate[2]);

			calendar.set(iYear, iMonth, iDay);
			calendar.add(Calendar.MONTH, -1);// 因为Month值从0开始，所以取得的值应该减去1
			calendar.add(Calendar.DATE, iDays);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 构造日期格式化器
			date = calendar.getTime();
			sLateDate = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sLateDate;
	}

	/**
	 * 获取当前日期是星期几
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static int getWeekOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 7;

		return w;
	}
}