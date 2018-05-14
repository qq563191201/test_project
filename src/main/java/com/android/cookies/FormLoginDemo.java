package com.android.cookies;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.*;
import org.apache.commons.httpclient.methods.*; 
  
/** 
 * 用来演示登录表单的示例 
 */
public class FormLoginDemo {
	static final String LOGON_SITE = "localhost" ;
	static final int     LOGON_PORT = 8080;
	  
	   public static void main(String[] args) throws Exception{
	      HttpClient client = new HttpClient();
	      client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);
	  
	      // 模拟登录页面 login.jsp->main.jsp
	      PostMethod post = new PostMethod( "user/login" );
	      NameValuePair name = new NameValuePair( "name" , "admin" );
	      NameValuePair pass = new NameValuePair( "password" , "123456" );
	      post.setRequestBody( new NameValuePair[]{name,pass});
	      int status = client.executeMethod(post);
	      System.out.println(post.getResponseBodyAsString());
	      post.releaseConnection();
	  
	      // 查看 cookie 信息
	      CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
	      Cookie[] cookies = cookiespec.match(LOGON_SITE, LOGON_PORT, "/" , false , client.getState().getCookies());
	      if (cookies.length == 0) {
	         System.out.println( "None" );
	      } else {
	         for ( int i = 0; i < cookies.length; i++) {
	            System.out.println(cookies[i].toString());
	         }
	      }
	  
	   }
}
