package com.httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * 说明
 * 利用httpclient下载文件
 * maven依赖
 * <dependency>
*			<groupId>org.apache.httpcomponents</groupId>
*			<artifactId>httpclient</artifactId>
*			<version>4.0.1</version>
*		</dependency>
*  可下载http文件、图片、压缩文件
*  bug：获取response header中Content-Disposition中filename中文乱码问题
 * @author tanjundong
 *
 */
public class HttpDownload {
	
	private final static String REMOTE_FILE_URL = "http://shouji.360tpcdn.com/151030/94cb2014a96640feff358a120664f766/com.lj.mlys.qh_8.apk";  
    
    private final static int BUFFER = 1024;  
  
	public static void main(String[] args) {  
		httpClientDown(REMOTE_FILE_URL, "E:/test/");
	}  
    
    public static int httpClientDown(String url, String path){
    	int x = -1;
    	HttpClient client = new HttpClient();  
        GetMethod httpGet = new GetMethod(url);  
        try {  
        	client.executeMethod(httpGet);  
               
        	InputStream in = httpGet.getResponseBodyAsStream();  
        	
        	String fileName = url.substring(url.lastIndexOf("/")+1);
              
        	FileOutputStream out = new FileOutputStream(new File(path+fileName));  
              
        	byte[] b = new byte[BUFFER];  
            int len = 0;  
            while((len=in.read(b))!= -1){  
                out.write(b,0,len);  
            }  
            in.close();  
            out.close();  
               
        }catch (HttpException e){  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            httpGet.releaseConnection();  
        }  
        System.out.println("download, success!!");
    	return x;
    }
}
