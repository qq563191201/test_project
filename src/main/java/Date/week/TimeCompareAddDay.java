package Date.week;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeCompareAddDay {
	/**
	 * @title
	 * @description 
	 * @author Xuxiaobing
	 * @date 2015-4-23 下午03:27:17 
	 * @version v1.0
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException{
		String startTime = "2015-04-20 22:27:00";
		SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatDay1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatDay2 = new SimpleDateFormat("yyyy-MM-dd HH");
    	Date start = formatDay.parse(startTime);
    	Date thistime = new Date();
    	Long time = thistime.getTime()-start.getTime();
    	long day = time/(24*60*60*1000);
    	long hour = (time/(60*60*1000)-day*24);
    	long min = ((time/(60*1000))-day*24*60-hour*60);
    	long days = addDay(start.getTime());
//    	long ss = (time/1000-day*24*60*60-hour*60*60-min*60);
    	Map<Date, Double> map = new LinkedHashMap<Date, Double>();
    	if(days>2||days==2){
    		String stateDay = startTime.substring(0, 10);
    		for(int i=0;i<days;i++){
    			Date dayTime = formatDay1.parse(stateDay);
    			dayTime.setDate(dayTime.getDate()+i);
    			map.put(dayTime, 1D);
    		}
    	}
    	if(days<2&&hour<24){
    		String stateDay = startTime.substring(0, 13);
    		for(int i=0;i<hour;i++){
    			Date dayTime = formatDay2.parse(stateDay);
    			dayTime.setHours(dayTime.getHours()+i);
    			map.put(dayTime, 1D);
    		}
    	}    	
	}
	
	/**
	   * 计算规则————按自然天数计算
	   * 实现方式：
	   * 1，程序启动时记录保护开始时间
	   * 2，此后进主界面与此时间求差值，在此后(0, 24]小时区间内不跨到第二天记1天，跨天记2天，以此类推(24, 48]小时区间不跨到第三天记2天，跨天记3天...画数轴就知道怎么回事了
	* */
	public static long addDay(long start){
		//程序启动时
		//需要计算运行天数时，计算差值
		long end = System.currentTimeMillis();
		long days = (end - start) / (1000 * 60 * 60 * 24);
		//运行天数从1开始计数
		long runningDays = days + 1;
		//判断是否跨天，若跨天，运行天数还要+1
		long probableEndMillis = start + (1000 * 60 * 60 * 24) * days;
		if(new Date(probableEndMillis).getDay() != new Date(end).getDay())
		{
			runningDays++;
		}
		return runningDays;
	}
}
