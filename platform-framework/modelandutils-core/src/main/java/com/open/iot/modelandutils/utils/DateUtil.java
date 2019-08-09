package com.open.iot.modelandutils.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具
 * 
 */
public class DateUtil
{
//    public static void main(String[] args) {
//    	System.out.println(parse("2018-05-14 14:15:59",YYYY_MM_DD_HH_MM_SS).getTime());
//	}
//    private static DateFormat defaultFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
    
    
//    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 字符串解析成日期对象
     * 
     * @param source
     * @return
     */
    public static Date parse(String source)
    {
        Date date = null;
        try
        {
        	DateFormat defaultFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
            date = defaultFormatter.parse(source);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 字符串解析成日期对象
     * 
     * @param source
     * @return
     */
    public static Date parse(String source, String pattern)
    {
        Date date = null;
        try
        {
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            date = dateFormat.parse(source);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
    
    public static String format(Date date, String pattern)
    {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        if (date == null)
        {
            return "";
        }
        return dateFormat.format(date);
    }
    
    public static String format(Date date)
    {
        if (date == null)
        {
            return "";
        }
        DateFormat defaultFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return defaultFormatter.format(date);
    }
    
    public static Date getToday()
    {
        Date now = new Date();
        return parse(format(now, "yyyy-MM-dd"), "yyyy-MM-dd");
    }
    
    public static Date getOfDay(long timeS)
    {
        Date now = new Date(timeS);
        return parse(format(now, "yyyy-MM-dd"), "yyyy-MM-dd");
    }
    
    public static int getDayOfMonth(int year, int month)
    {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0); // 输入类型为int类型
        return c.get(Calendar.DAY_OF_MONTH);
    }
    
    public static String getDayDate()
    {
        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }
    
    public static String getWeekDays(int i, int offect)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // getTimeInterval(sdf);
        
        Calendar calendar1 = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        calendar1.setFirstDayOfWeek(Calendar.MONDAY);
        
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayOfWeek)
        {
            calendar1.add(Calendar.DAY_OF_MONTH, -1);
        }
        
        // 获得当前日期是一个星期的第几天
        int day = calendar1.get(Calendar.DAY_OF_WEEK);
        
        // 获取当前日期前（下）x周同星几的日期
        calendar1.add(Calendar.DATE, 7 * i);
        
        calendar1.add(Calendar.DATE, calendar1.getFirstDayOfWeek() - day);
        
        calendar1.add(Calendar.DATE, offect);
        
        String date = sdf.format(calendar1.getTime());
        
        return date;
    }
}
