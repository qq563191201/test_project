package com.reg.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Group 类 用于匹配和抓取 html页面的数据
* @author SoFlash - 博客园  http://www.cnblogs.com/longwu
*/
public class regref {
	public static void main(String[] args) {
        // Pattern 用于编译正则 这里用到了3个正则 分别用括号()包住
        // 第1个正则用于匹配URL 当然这里的正则不一定准确 这个匹配URL的正则就是错误的 只是在这里刚好能匹配出来
        // 第2个正则是用于匹配标题 SoFlash的
        // 第3个正则用于匹配日期
        /* 这里只用了一条语句便把url,标题和日期全部给匹配出来了 */
        Pattern p = Pattern.compile("='(\\w.+)'>(\\w.+[a-zA-Z])-(\\d{1,2}\\.\\d{1,2}\\.\\d{4})");
        String s = "<a href='http://www.cnblogs.com/longwu'>SoFlash-12.22.2011</a>";
        Matcher m = p.matcher(s);
        while (m.find()) {
            // 通过调用group()方法里的索引 将url,标题和日期全部给打印出来
            System.out.println("打印出url链接:" + m.group(1));
            System.out.println("打印出标题:" + m.group(2));
            System.out.println("打印出日期:" + m.group(3));
            System.out.println();
        }
        System.out.println("group方法捕获的数据个数:" + m.groupCount() + "个");
    }
}
