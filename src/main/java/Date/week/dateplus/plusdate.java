package Date.week.dateplus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class plusdate {
	public static void main(String args[]){
		Calendar calendar=Calendar.getInstance();  
		calendar.setTime(new Date());
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//今天的日期
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-1);//让日期加1  
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		String inputtime = format1.format(new Date());
		int thisday = calendar.get(Calendar.DATE);
		System.out.println(inputtime+"-"+thisday);//加1之后的日期Top 
	}
}
