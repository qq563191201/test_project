package com.httpclient.clientpost;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class Client {
	private static int port = 8080;
	
	private static URL url;  
	private static HttpURLConnection con;  
	private static int state = -1;  
	  
	/** 
	   * 功能：检测当前URL是否可连接或是否有效, 
	   * 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用 
	   * @param urlStr 指定URL网络地址 
	   * @return URL 
	   */  
	public synchronized URL isConnect(String urlStr) {  
	   int counts = 0;  
	   if (urlStr == null || urlStr.length() <= 0) {                         
		   return null;                   
	   }  
	   long start = System.currentTimeMillis();
	   while (counts < 5) {  
		   try {  
			   url = new URL(urlStr);  
			   con = (HttpURLConnection) url.openConnection();
			   con.setConnectTimeout(1000*20);
			   state = con.getResponseCode();
			   System.out.println(counts +"= "+state);  
			   if (state == 200) {  
				   System.out.println("URL可用！");  
			   }  
			   break;  
		   }catch (Exception ex) {  
			   counts++;   
			   System.out.println("URL不可用，连接第 "+counts+" 次");  
			   urlStr = null;  
			   continue;  
		   }  
	   }  
	   long end = System.currentTimeMillis();
	   System.out.println(end-start);
	   return url;  
	}   
    
    private static void urlConnectionPost() {
        StringBuilder responseBuilder = null;
        BufferedReader reader = null;
        OutputStreamWriter wr = null;
        URL url;
        try {
            url = new URL("http://119.147.137.167");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(1000 * 5);
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write("");
            wr.flush();
            // Get the response
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            responseBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line + "\n");
            }
            wr.close();
            reader.close();
            System.out.println(responseBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void httpClientPost() {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://www.poorren.com");
        try {
            ContentProducer cp = new ContentProducer() {
                public void writeTo(OutputStream outstream) throws IOException {
                    Writer writer = new OutputStreamWriter(outstream, "UTF-8");
                    writer.write("");
                    writer.flush();
                }
            };
            post.setEntity(new EntityTemplate(cp));
            HttpResponse response = client.execute(post);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void socket(){
    	Socket socket;
        try {
            String para = "market_interface/feed/advicepc?Email=1414";
            int len = para.length();
            socket = new Socket("localhost", port);
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),
                    "UTF8"));
            InputStream ins = socket.getInputStream();
            StringBuffer sb = new StringBuffer();
            sb.append("POST /FileShareServer/ProcessServlet HTTP/1.1\r\n");// 注意\r\n为回车换行
            sb.append("Accept-Language: zh-cn\r\n");
            sb.append("Connection: Keep-Alive\r\n");
            sb.append("Host:localhost\r\n");
            sb.append("Content-Length:"+len+"\r\n");
            sb.append("Content-Type: application/x-www-form-urlencoded\r\n");
            sb.append("\r\n");
            sb.append(para);
            // 接收Web服务器返回HTTP响应包
            wr.write(sb.toString());
            wr.flush();
            //System.out.println(sb.toString());
            BufferedReader rd = new BufferedReader(new InputStreamReader(ins));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] arg) {
    	Client u = new Client();
    	u.isConnect("http://119.147.137.167");  
    }
}
