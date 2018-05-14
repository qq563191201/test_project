package com.json;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.Reader;  
import java.net.URL;  
import java.nio.charset.Charset;  
  



import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import org.json.JSONObject;  
/** 
 * java根据 url获取 json对象 
 * @author openks 
 * @since 2013-7-16 
 *  需要添加java-json.jar才能运行 
 */  
public class GetPlaceByIp {  
  
  private static String readAll(Reader rd) throws IOException {  
    StringBuilder sb = new StringBuilder();  
    int cp;  
    while ((cp = rd.read()) != -1) {  
      sb.append((char) cp);  
    }  
    return sb.toString();  
  }  
  
  public static JSONObject readJsonFromUrl(String url) throws IOException, ParseException {  
    InputStream is = new URL(url).openStream();  
    try {  
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));  
      String jsonText = readAll(rd);  
      JSONObject json = new JSONObject(jsonText);  
      return json;  
    } finally {  
      is.close();  
     // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");  
    }  
  }  
  
  public static void readJsonString(){
	  String str = " [{\"packageName\":\"com.serve.goodsserve.act\",\"versionCode\":\"2\",\"versionName\":\"2.0\"},{\"packageName\":\"com.example.android.apis\",\"versionCode\":\"21\",\"versionName\":\"5.0-eng.buildbot.20150224.025318\"},{\"packageName\":\"com.bsrt.mm\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.itheima.mobilesafe\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.iedgeco.ryan.crop\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.qihoo.appstore\",\"versionCode\":\"300030223\",\"versionName\":\"3.2.23\"},{\"packageName\":\"com.mobogenie.assistant\",\"versionCode\":\"20160\",\"versionName\":\"2.1.60\"},{\"packageName\":\"com.bsrt.ads.sdk\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.dewmobile.kuaiya\",\"versionCode\":\"124\",\"versionName\":\"3.5 (CN)\"},{\"packageName\":\"com.handmark.pulltorefresh.samples\",\"versionCode\":\"2110\",\"versionName\":\"2.1.1\"},{\"packageName\":\"com.viewpagerindicator.sample\",\"versionCode\":\"65\",\"versionName\":\"2.4.1\"},{\"packageName\":\"com.qihoo.expressbrowser\",\"versionCode\":\"204\",\"versionName\":\"2.0.4\"},{\"packageName\":\"com.androidworks\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.example.adtest\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.example.bsrtsdk\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.example.staggeredgridviewdemo\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"cn.itcast.mobilesafe\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.koushikdutta.urlimageviewhelper.sample\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.example.android.tictactoe\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.stupic.ghostcome\",\"versionCode\":\"17\",\"versionName\":\"3.50\"},{\"packageName\":\"com.qihoo.freewifi\",\"versionCode\":\"314\",\"versionName\":\"3.1.4\"},{\"packageName\":\"com.example.test\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.autoconnectwifi.app\",\"versionCode\":\"65\",\"versionName\":\"3.5\"},{\"packageName\":\"com.bsrt.appmarket\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.example.musictest\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.lidroid.xutils.sample\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.example.sdktest\",\"versionCode\":\"1\",\"versionName\":\"1.0\"},{\"packageName\":\"com.xinmei365.font\",\"versionCode\":\"159\",\"versionName\":\"5.0.4.1\"},{\"packageName\":\"com.meizi.mm\",\"versionCode\":\"1\",\"versionName\":\"1.0\"}]";
	  JSONArray array = JSONArray.fromObject(str);
	  Map<String, String> map = new HashMap<String, String>();
	  for(int i = 0; i < array.size(); i++){
		  net.sf.json.JSONObject jsonObject = array.getJSONObject(i);
		  String packageName = (String) jsonObject.get("packageName");
		  String versionCode = (String) jsonObject.get("versionCode");
		  map.put(packageName, versionCode);
	  } 
	  System.out.println("");
  }
  
  public static void setId(long id){
	  id = 0;
  }
  
  public static void main(String[] args) throws IOException {  
      //这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm  
//    JSONObject json = readJsonFromUrl("http://123.125.82.206/app/getCollectionTags?os=16&webp=1&fm=cati&m=100a181920b5f9a08e23f919b8edc3c6&m2=e545e741cda1395e93b1d509ca7bcc40&v=3.2.23&re=1&ch=475235&os=16&model=MI+2A&sn=4.47798919578295&cu=qct+msm8960+cdp&ppi=720x1280&startCount=23");  
//    System.out.println(json.toString());  
//    System.out.println(((JSONObject) json.get("content")).get("address"));  
//	  readJsonString();
	  long id = 1;
	  setId(id);
	  System.out.println(id);
  }  
}  