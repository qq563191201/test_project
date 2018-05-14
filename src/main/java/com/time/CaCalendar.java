package com.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CaCalendar {
	public static void main(String[] args) throws ParseException {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		System.out.println(hour);
	    //时间戳转化为Sting或Date  
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        Long time=new Long(1512093964);  
        String d = format.format(time);  
        Date date=format.parse(d);  
        System.out.println("Format To String(Date):"+d);  
        System.out.println("Format To Date:"+date);  
	}
}
