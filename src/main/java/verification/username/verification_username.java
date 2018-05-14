package verification.username;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class verification_username {
	public static void main(String args[]){
		String userName = "11";
		String str = "^\\w+$";  
        boolean flag = false;  
        if (userName != null)  
        {  
            Pattern p = Pattern.compile(str);  
            Matcher match = p.matcher(userName);  
            flag = match.matches();  
        }
        System.out.println(flag);
	}
	
	/** 
     * 验证用户名注册是否合法-----------由数字、26个英文字母或者下划线组成的字符串 
     *  
     * @param userName 
     * @return 
     */  
    public static boolean isRegUserName(String userName)  
    {  
  
        String str = "^\\w+$";  
        boolean flag = true;  
        if (userName != null)  
        {  
            Pattern p = Pattern.compile(str);  
            Matcher match = p.matcher(userName);  
            flag = match.matches();  
        }  
        return flag;  
    }  
}
