package Date.week;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class weeks {
	public static void main(String args[]){
		System.out.println(week_day());
	}
	
	/**
	 * 
	 * @title 判断周几
	 * @description
	 * @return
	 * @author Xuxiaobing
	 * @date 2015-4-13 上午09:23:28
	 */
	public static String week_day(){
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		return sdf.format(new Date());
	}
}
