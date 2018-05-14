package com.jsoupHtml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;

import util.Native2AsciiUtils;

public class JsoupHtml {

	/**
	 * @Title: jsoup模拟登陆
	 * @Description:
	 * @param url
	 * @throws IOException
	 * @author Xuxiaobing
	 * @date 2016年6月4日 下午2:39:00
	 * @version V1.0
	 */
	public static void jsoupLogin(String url) throws IOException{
		Map<String, String> map = new HashMap<>();
	    //map.put请根据自己的微博cookie得到
		//Html_v_54=5640_63600643735849; 
		//ssc_user_LandingPage=http%3a%2f%2fdata.shishicai.cn%2fcqssc%2fhaoma%2f; 
		//ssc_user_SiteReferrerUrl=https%3a%2f%2fwww.baidu.com%2flink%3furl%3dyqVR-zMzB3WKJEhyo9qMILdH9SWaXgKn-O-hnCP7N7usgj7k9mszsST2jpPEMtNz%26wd%3d%26eqid%3da455091400001ce4000000055752386f; 
		//ssc_user_RegEnterPage=http%3a%2f%2fdata.shishicai.cn%2fcqssc%2fhaoma%2f; 
		//Hm_lvt_cad2e9c6544a1e8f06862d019ce44f71=1465014992,1465015624,1465016547,1465016572; 
		//Hm_lpvt_cad2e9c6544a1e8f06862d019ce44f71=1465016737; 
		//ASP.NET_SessionId=lsfbdv3ynbcfx5cwdztplzbj; 
		//TKshishicai_user_InterfaceType=1; 
		//TKshishicai_user_UserID=1557229; 
		//TKshishicai_user_ClientUserName=xuxiaobing; 
		//TKshishicai_user_LastUserName=xuxiaobing; 
		//TKshishicai_user_Secret=7abc91340c550e0d7a0b70d27575d7ef; 
		//TKshishicai_user_LoginFailCount=0
		map.put("ASP.NET_SessionId", "lsfbdv3ynbcfx5cwdztplzbj");
		map.put("Hm_lpvt_cad2e9c6544a1e8f06862d019ce44f71", "1465016547");
		map.put("Hm_lvt_cad2e9c6544a1e8f06862d019ce44f71", "1465013704,1465014992,1465015624,1465016547");
		map.put("Html_v_54", "be88_63600642085984");
		map.put("TKshishicai_user_ClientUserName", "xuxiaobing");
		map.put("TKshishicai_user_InterfaceType", "1");
		map.put("TKshishicai_user_LastUserName", "xuxiaobing");
		map.put("TKshishicai_user_LoginFailCount", "0");
		map.put("TKshishicai_user_Secret", "7abc91340c550e0d7a0b70d27575d7ef");
		map.put("TKshishicai_user_UserID", "1557229");
		map.put("ssc_user_LandingPage", "http://data.shishicai.cn/cqssc/haoma/");
		map.put("ssc_user_RegEnterPage", "http://data.shishicai.cn/cqssc/haoma/");
		map.put("ssc_user_SiteReferrerUrl", "https://www.baidu.com/link?url=yqVR-zMzB3WKJEhyo9qMILdH9SWaXgKn-O-hnCP7N7usgj7k9mszsST2jpPEMtNz&wd=&eqid=a455091400001ce4000000055752386f");
		org.jsoup.Connection.Response res = Jsoup.connect(url)
				.data("lottery", "4").data("date", "2016-06-04")
	        .method(Method.POST).execute();
	    String s = res.body();
	    System.out.println(s);
	    String[] ss = s.split("<script>FM.view");
	    int i = 0;
	    // pl_content_homeFeed
	    // pl.content.homeFeed.index
	    List<String> list = new ArrayList<>();
	    for (String x : ss) {
//	          System.out.println(i++ + "======================================");
//	          System.out.println(x.substring(0,
//	                  x.length() > 200 ? 200 : x.length()));
//	          System.out.println("===========================================");
	      if (x.contains("\"html\":\"")) {
	        String value = getHtml(x);
	        list.add(value);
	        System.out.println(value);
	      }
	    }
	}
    public static String getHtml(String s) {
        String content = s.split("\"html\":\"")[1]
            .replaceAll("(\\\\t|\\\\n)", "").replaceAll("\\\\\"", "\"")
            .replaceAll("\\\\/", "/");
        content = content.substring(0,
            content.length() <= 13 ? content.length()
                : content.length() - 13);
        return Native2AsciiUtils.ascii2Native(content);
      }
}
