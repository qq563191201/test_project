package json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class jsonOb {
	public static void main(String args[]) throws JSONException, IOException{
		lis();
	}
	
	public static void lis() throws IOException, JSONException{
		String now_url = "http://api.map.baidu.com/geocoder/v2/?ak=pmCgmADsAsD9rEXkqWNcTzjd&location="+29.302424+","+108.165712+"&output=json&pois=1";
		URL url = new URL(now_url);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));  
		String s = "";
		StringBuffer sb = new StringBuffer("");
		while ((s = br.readLine()) != null) {  
			sb.append(s);
		}
        br.close();
        String gis_name = "";
        JSONArray jsonarrays = new JSONArray("["+sb.toString()+"]");
        for (int i = 0; i < jsonarrays.length(); i++) {   
        	JSONObject jsonobj = jsonarrays.getJSONObject(i);
        	System.out.println(jsonobj.getString("result"));
        	JSONArray jsonarray = new JSONArray("["+jsonobj.getString("result")+"]");
        	for (int j = 0; j < jsonarray.length(); j++) {   
        		JSONObject nameReslut = jsonarray.getJSONObject(j);
        		gis_name = nameReslut.getString("formatted_address");
        	}
        }
        System.out.println(gis_name);
	}
	
	public static void addjson() throws JSONException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("a", 1);   
		jsonObject.put("b", 1.1);
		jsonObject.put("c", 1L);
		jsonObject.put("d", "test");
		jsonObject.put("e", true);
		System.out.println(jsonObject);
//		JSONArray jsonObject1 = new JSONArray("{d:test,e:true,b:1.1,c:1,a:1}");
		jsonObject = getJSONObject("{\"api_level\":\"\",\"system\":\"android\",\"density\":\"\",\"push_code\":\"\",\"device_code\":\"867747013763899\",\"resolution\":\"null ｘ null\",\"phone_brand\":\"\",\"channel_id\":\"\",\"phone_model\":\"\"}");
		System.out.println(jsonObject.getString("system"));
	}
	public static JSONObject getJSONObject(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace(System.err);
        }
        return jsonObject;
    }
}
