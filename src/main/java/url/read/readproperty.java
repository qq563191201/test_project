package url.read;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class readproperty {
	public static void main(String[] args) throws Exception{
		readUrl("");
	}
	
	/**
	 * 
	 * @Title: 根据ip地址 读取url解析json数据 获取国家
	 * @Description:
	 * @throws ParseException
	 * @author Xuxiaobing
	 * @date 2016年5月11日 上午10:17:57
	 * @version V1.0
	 */
	public static String readUrl(String ip){
		String strUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=39.178.75.108";
		JSONObject jsonTotal = readJsonFromUrl(strUrl);
		String cn = "";
		if(jsonTotal!=null){
			String data = jsonTotal.getString("data");
			JSONArray jsonarray = new JSONArray("["+data+"]");
			if(jsonarray!=null&&jsonarray.length()==1){
				JSONObject jsonobj = jsonarray.getJSONObject(0);
				String country_id = jsonobj.getString("country_id");
				cn = country_id;
			}
		}
		return cn;
	}
	
	public static JSONObject readJsonFromUrl(String url) {  
		InputStream is = null;
		BufferedReader rd = null;
		JSONObject str = null;
		try {
			is = new URL(url).openStream();
	    	rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));  
	    	String jsonText = readAll(rd);  
	    	JSONObject json = new JSONObject(jsonText);  
	    	str = json;  
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally {  
	    	try {
	    		if(is!=null){
	    			is.close();
	    		}
	    		if(rd!=null){
	    			rd.close();
	    		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    	// System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");  
	    }  
		return str;
	}
	
	private static String readAll(Reader rd) {  
		StringBuilder sb = new StringBuilder();  
	    int cp;  
	    try{
		    while ((cp = rd.read()) != -1) {  
		    	sb.append((char) cp);  
		    }  
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return sb.toString();  
	} 
	
	
	public void readProperties() throws FileNotFoundException, IOException{
		Properties property = new Properties();
		property.load(new FileInputStream(System.getProperty("user.dir").replace("bin", "")+"/webapps/hncsnqt/WEB-INF/classes/taiji-push.properties"));
		String value = property.getProperty("answer_url");
		System.out.println(value);
	}
}
