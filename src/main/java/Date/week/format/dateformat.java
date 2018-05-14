package Date.week.format;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Date.week.Timestamps;

public class dateformat {
	public static void main(String args[]) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时");
		SimpleDateFormat format1 = new SimpleDateFormat("MM");
		String ss = format1.format(new Date());
//		if(ss.equals("11")){
//			System.out.println(ss);
//		}else{
//			
//		}
		for(int i=0;i<100;i++){
			if(i==10){
				continue;
			}
			System.out.println(i);
		}
	}
	
	/**
	 * 获取昨天日期
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {     
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, year);     
        cal.set(Calendar.MONTH, month);     
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));  
       return  new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());  
    } 
	
	//获取昨天日期
	public static Timestamp getLastDay(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		Calendar calendar=Calendar.getInstance();  
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-1);//让日期-1
		String inputtime = format1.format(new Date())+"-"+calendar.get(Calendar.DATE);
		String time = format.format(new Date())+""+calendar.get(Calendar.DATE);
		Timestamp start = Timestamps.StrToTimestamp(time+"000000");
		System.out.println(time);
		Timestamp end = Timestamps.StrToTimestamp(time+"230000");
		return end;
	}
}
