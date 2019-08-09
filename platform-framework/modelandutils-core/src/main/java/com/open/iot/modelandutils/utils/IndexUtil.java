package com.open.iot.modelandutils.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 索引生成规则
* @ClassName: IndexUtil  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author huy  
* @date 2018年12月21日  
*
 */
public class IndexUtil {

	private static AtomicLong count = new AtomicLong();
	
//	private static DateFormat defaultFormatter_YMD = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 系统维护的告警索引
	 */
//	public static final String CACHE_ALARM_SYS_IDX = "ALARM_SYS_IDX";
	
//	public static long getNextSysIdx(long curIdx) {
//		count.set(curIdx);
//		count.incrementAndGet();
//		return count.get()+(long)(Math.random()*10000+1);
//	}
	
	public static long getRandomIdx() {
		return (long)(Math.random()*100000+1);
	}
	public static String generateIndex(long domainId,long timeS,String bandNo) {
		String dateString=DateUtil.format(new Date(timeS),"yyyyMMddHHmmss");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("%05d", domainId));
		stringBuilder.append(dateString);
		stringBuilder.append(bandNo);
		return stringBuilder.toString();
	}
	public static String generateAlarmIndex(long domainId,long timeS,/*int alarmCode,*/long sysIdx) {
		String dateString=DateUtil.format(new Date(timeS),"yyyyMMddHHmmss");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("%05d", domainId));
		stringBuilder.append(dateString);
		stringBuilder.append(String.format("%08d", sysIdx));
		return stringBuilder.toString();
	}
}
