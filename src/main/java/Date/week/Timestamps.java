package Date.week;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Timestamps {
	public static void main(String args[]) throws ParseException{
//		Date date = new Date();
//		int hour = date.getHours();
//		int mm = date.getMinutes();
//		int ss = date.getSeconds();
//		Timestamp thisday = StrToTimestamp("19700101"+hour+mm+ss);
//		Timestamp start = StrToTimestamp("19700101110000");
//		Timestamp end = StrToTimestamp("19700101190000");
//		if(thisday.getTime()>start.getTime() && thisday.getTime()<end.getTime()){
//			System.out.println(thisday);
//		}
		
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
//		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
//		Calendar calendar=Calendar.getInstance();  
//		calendar.setTime(new Date());
//		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-1);//让日期-1
//		String inputtime = format1.format(new Date())+"-"+calendar.get(Calendar.DATE);
//		String time = format.format(new Date())+""+calendar.get(Calendar.DATE);
//		Timestamp start = StrToTimestamp(time+"000000");
//		Timestamp end = StrToTimestamp(time+"230000");
//		System.out.println(calendar.get(Calendar.DATE));
		
//		Calendar cal=Calendar.getInstance();
//        cal.add(Calendar.MINUTE,-30);
//        Date d=cal.getTime();
//        SimpleDateFormat sp=new SimpleDateFormat("yyyyMMddHHmmss");
//        String min=sp.format(d);
//        System.out.println(min);
		
		String doc = "SEVP_WMC_RE_WMBS_EWPF_AJL_LNO_P9_20141021095400000";
		
	}
	
	/**
	 * 获取昨天日期
	 * @return
	 */
	public static String yesterday(){
		Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        SimpleDateFormat sp=new SimpleDateFormat("yyyyMMdd");
        String ZUOTIAN=sp.format(d);//获取昨天日期
        return ZUOTIAN;
	}
	
	/**
	 * 字符转Timestamp
	 */
	public static Timestamp StrToTimestamp(String str){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
	    Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
        	String tempdate = format.format(format1.parse(str));          
            ts = Timestamp.valueOf(tempdate);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return ts;
	}
	
	/**
	 * 输出48小时
	 */
	@SuppressWarnings("deprecation")
	public static List<Timestamp> hour(Timestamp time){
		List<Timestamp> list = new ArrayList<Timestamp>();
		for(int i =0;i<1;i++){
        	Timestamp hour=new Timestamp(time.getTime());
            hour.setHours(time.getHours()-i);
            hour.setMinutes(00);  
            hour.setSeconds(00);
            hour.setNanos(0);
            list.add(hour);
        }
		return list;
	}
	
}
