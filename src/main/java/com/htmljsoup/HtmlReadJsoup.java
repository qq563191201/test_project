package com.htmljsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlReadJsoup {
	public static void main(String[] args) {
		Document doc = Jsoup.parse("http://data.shishicai.cn/cqssc/haoma/");
		Element content = doc.getElementById("key");
		Elements links = content.getElementsByTag("a");
		for (Element link : links) {
		  String linkHref = link.attr("href");
		  String linkText = link.text();
		} 		
	}
}
