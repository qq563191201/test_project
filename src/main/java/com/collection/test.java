package com.collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {
	public static void main(String[] args) throws UnsupportedEncodingException, IOException{
//		String strUrl = "http://zhushou.360.cn/detail/index/soft_id/77208";
//		URL url = new URL(strUrl);
        // InputStreamReader 是一个输入流读取器 用于将读取的字节转换成字符
        // 更多可以看看 http://blog.sina.com.cn/s/blog_44a05959010004il.html
//        InputStreamReader isr = new InputStreamReader(url.openStream(),
//                "utf-8"); // 统一使用utf-8 编码模式
//        // 使用 BufferedReader 来读取 InputStreamReader 转换成的字符
//        BufferedReader br = new BufferedReader(isr);
//        String json = "";
//        while (br.readLine() != null) {
//        	json += br.readLine();
//        }
//    	Document doc1=  Jsoup.connect(strUrl).get();
//        String images = doc1.getElementById("fullDesc").html();
//        System.out.println(json);
//        Document doc = Jsoup.parse(images);
//        Elements ele = doc.getElementsByAttribute("ul");
//        for(Element e :ele)
//        {
//        	System.out.println(e.attr("li"));
//        }
        try{
        	Timestamp.valueOf("111");
        }catch(Exception e){
        	e.printStackTrace();
        }
        System.out.println("11111");
	}
	
	//获取图片
	public void getImage() throws IOException{
		String strUrl = "http://m.app.haosou.com/detail/index?pname=com.qihoo360.mobilesafe&id=77208";
    	Document doc1=  Jsoup.connect(strUrl).get();
        String images = doc1.getElementById("thumbList").html();
        Document doc = Jsoup.parse(images);
        Elements ele = doc.getElementsByAttribute("src");
        for(Element e :ele)
        {
        	System.out.println(e.attr("src"));
        }
	}
	
	// 传入2个字符串参数 一个是pattern(我们使用的正则) 另一个matcher是html源代码
    public static String regularGroup(String pattern, String matcher) {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(matcher);
        if (m.find()) { // 如果读到
            return m.group(1);// 返回捕获的数据
        } else {
            return ""; // 否则返回一个空字符串
        }
    }
}
