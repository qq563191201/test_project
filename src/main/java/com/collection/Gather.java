package com.collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gather {
	public static void main(String[] args) {
        // 首先用一个字符串 来装载网页链接
        String strUrl = "http://www.footballresults.org/league.php?all=1&league=EngPrem";
        try {
            // 创建一个url对象来指向 该网站链接 括号里()装载的是该网站链接的路径
            // 更多可以看看 http://wenku.baidu.com/view/8186caf4f61fb7360b4c6547.html
            URL url = new URL(strUrl);
            // InputStreamReader 是一个输入流读取器 用于将读取的字节转换成字符
            // 更多可以看看 http://blog.sina.com.cn/s/blog_44a05959010004il.html
            InputStreamReader isr = new InputStreamReader(url.openStream(),
                    "utf-8"); // 统一使用utf-8 编码模式
            // 使用 BufferedReader 来读取 InputStreamReader 转换成的字符
            BufferedReader br = new BufferedReader(isr);
            String strRead = ""; // 新增一个空字符串strRead来装载 BufferedReader 读取到的内容

            // 定义3个正则 用于匹配我们需要的数据
            String regularDate = "(\\d{1,2}\\.\\d{1,2}\\.\\d{4})";
            String regularTwoTeam = ">[^<>]*</a>";
            String regularResult = ">(\\d{1,2}-\\d{1,2})</TD>";

            int i =0;  //定义一个i来记录循环次数  即收集到的球队比赛结果数
            int index = 0; // 定义一个索引 用于获取分离 2个球队的数据 因为2个球队正则是相同的
            // 开始读取数据 如果读到的数据不为空 则往里面读
            while ((strRead = br.readLine()) != null) {
                /**
                 * 用于捕获日期数据
                 */
                String strGet = regularGroup(regularDate, strRead);
                //如果捕获到了符合条件的 日期数据 则打印出来 
                if (!strGet.equals("")) {
                    System.out.println("Date:" + strGet);
                    //这里索引+1  是用于获取后期的球队数据
                    ++index;   //因为在html页面里 源代码里 球队数据是在刚好在日期之后 
                }
                /**
                 * 用于获取2个球队的数据
                 */
                strGet = regularGroup(regularTwoTeam, strRead);
                if (!strGet.equals("") && index == 1) { //索引为1的是主队数据
                    // 通过substring方法 分离出 主队数据
                    strGet = strGet.substring(1, strGet.indexOf("</a>"));
                    System.out.println("HomeTeam:" + strGet); //打印出主队
                    index++;  //索引+1之后 为2了
                    // 通过substring方法 分离出 客队
                } else if (!strGet.equals("") && index == 2) {  //这里索引为2的是客队数据
                    strGet = strGet.substring(1, strGet.indexOf("</a>"));
                    System.out.println("AwayTeam:" + strGet); //打印出客队 
                    index = 0;
                }
                /**
                 * 用于获取比赛结果
                 */
                strGet = regularGroup(regularResult, strRead);
                if (!strGet.equals("")) {
                    //这里同样用到了substring方法 来剔除'<' 和 "</TD>" 标签 来获取我们想要的比赛结果
                    strGet = strGet.substring(1, strGet.indexOf("</TD>"));
                    System.out.println("Result:" + strGet);
                    System.out.println();
                    i++;
                }
            }
            // 当读完数据后 记得关闭 BufferReader
            br.close();
            System.out.println("共收集到"+i+"条比赛记录");//打印出循环次数
        } catch (IOException e) {
            // 如果出错 抛出异常
            e.printStackTrace();
        }
    }
	
	// 传入2个字符串参数 一个是pattern(我们使用的正则) 另一个matcher是html源代码
    public static String regularGroup(String pattern, String matcher) {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(matcher);
        if (m.find()) { // 如果读到
            return m.group();// 返回捕获的数据
        } else {
            return ""; // 否则返回一个空字符串
        }
    }
}
