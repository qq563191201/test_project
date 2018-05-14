package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class AssistUtil {
	
	//找出int类型的cityCode
    public static boolean isNumeric(String str)
    {
          Pattern pattern = Pattern.compile("[0-9]*");
          Matcher isNum = pattern.matcher(str);
          if( !isNum.matches() )
          {
                return false;
          }
          return true;
    }
    
    //判断Double类型
    public static boolean isDouble(String str){
    	boolean isdouble = false;
    	try{
    		Double doubles = Double.valueOf(str);
    		isdouble = true;
    	}catch(Exception e){
    		
    	}
    	return isdouble;
    }
    
    /**
     * yyyyMMddHH格式转Timestamp格式
     * @param time
     * @return
     * @throws ParseException
     */
    public static Timestamp strtoTime(String time){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
	    Timestamp ts = null;
	    try { 
	    	String tempdate = format.format(format1.parse(time));
            ts = Timestamp.valueOf(tempdate);
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return ts;
    }
    
    //0~3 分别代表温度，湿度，风速 降水
    public static Integer weather(String type){
    	Integer weather_code = 0;
    	if(!(type!=null?type:"").equals("")){
			if(type.equals("temperature")){
				weather_code = 0;
			}else if(type.equals("humidity")){
				weather_code = 1;
			}else if(type.equals("wind")){
				weather_code = 2;
			}else if(type.equals("rain")){
				weather_code = 3;
			}else if(type.equals("press")){
				weather_code = 4;
			}
		}
    	return weather_code;
    }
    //0-最高;1-最低;2-正点;3-平均
    public static Integer classify(String classify){
    	Integer fun = 0;
    	if(classify.equals("max")){
			fun = 0;
		}else if(classify.equals("min")){
			fun = 1;
		}else if(classify.equals("now")){
			fun = 2;
		}else if(classify.equals("avg")){
			fun = 3;
		}
		return fun;
    }
    
    /** 
     * 手机号验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,8,7][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }
    
    public static boolean isPassword(String str){
    	String regex = "^\\w[a-zA-Z0-9~!@#$%^&*()_+]{5,16}$";
    	return str.matches(regex);
    }
	
	public String getIpAddr(HttpServletRequest request) {  
		 String ip = request.getHeader("x-forwarded-for");  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		  ip = request.getHeader("Proxy-Client-IP");  
		 }  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		  ip = request.getHeader("WL-Proxy-Client-IP");  
		 }  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		  ip = request.getRemoteAddr();  
		 }  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		  ip = request.getHeader("http_client_ip");  
		 }  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		  ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
		 }  
		 // 如果是多级代理，那么取第一个ip为客户ip  
		 if (ip != null && ip.indexOf(",") != -1) {  
		  ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();  
		 }  
		 return ip;  
	} 
	
	/**
	* 去重复
	* @param repeatedNumArr
	* @return
	*/
	public static String[] filterRepeatNum(String[] repeatedNumArr) {
		List<String> filterResultList = new ArrayList<String>();
		for (int i = 0; i < repeatedNumArr.length; i++) {
			if (!filterResultList.contains(repeatedNumArr[i])) {
				filterResultList.add(repeatedNumArr[i]);
			}
		}
		String[] noRepeatedNumArr = new String[filterResultList.size()];
		for (int i = 0; i < noRepeatedNumArr.length; i++) {
			noRepeatedNumArr[i] = filterResultList.get(i);
		}
		return noRepeatedNumArr;
	}
	
	/**
	* 去重复
	* @param repeatedNumArr
	* @return
	*/
	public static List<String> filterNum(List<String> repeatedNumArr) {
		List<String> filterResultList = new ArrayList<String>();
		for (String string : repeatedNumArr) {
			if (!filterResultList.contains(string)) {
				filterResultList.add(string);
			}
		}
		return filterResultList;
	}
	
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "1");
		map.put("1", "1");
		
	}
}
