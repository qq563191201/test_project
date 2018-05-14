package docfile.readname;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


public class PdfReader {

	public void readFdf(String file) throws Exception {
		// 是否排序
		boolean sort = false;
		// pdf文件名
		String pdfFile = file;
		// 输入文本文件名称
		String textFile = null;
		// 编码方式
		String encoding = "UTF-8";
		// 开始提取页数
		int startPage = 1;
		// 结束提取页数
		int endPage = Integer.MAX_VALUE;
		// 文件输入流，生成文本文件
		Writer output = null;
		// 内存中存储的PDF Document
		PDDocument document = null;
		try {
			try {
				// 首先当作一个URL来装载文件，如果得到异常再从本地文件系统//去装载文件
				URL url = new URL(pdfFile);
				document = PDDocument.load(pdfFile);
				// 获取PDF的文件名
				String fileName = url.getFile();
				// 以原来PDF的名称来命名新产生的txt文件
				if (fileName.length() > 4) {
					File outputFile = new File(fileName.substring(0, fileName
							.length() - 4)
							+ ".txt");
					textFile = outputFile.getName();
				}
			} catch (MalformedURLException e) {
				// 如果作为URL装载得到异常则从文件系统装载
				document = PDDocument.load(pdfFile);
				if (pdfFile.length() > 4) {

					textFile = pdfFile.substring(0, pdfFile.length() - 4)
							+ ".txt";
				}
			}
			
			// 文件输入流，写入文件倒textFile
			output = new OutputStreamWriter(new FileOutputStream(textFile),
					encoding);
			// PDFTextStripper来提取文本
			PDFTextStripper stripper = null;
			stripper = new PDFTextStripper();
			// 设置是否排序
			stripper.setSortByPosition(sort);
			// 设置起始页
			stripper.setStartPage(startPage);
			// 设置结束页
			stripper.setEndPage(endPage);
			// 调用PDFTextStripper的writeText提取并输出文本
			stripper.writeText(document, output);
		} finally {
			if (output != null) {
				// 关闭输出流
				output.close();
			}
			if (document != null) {
				// 关闭PDF Document
				document.close();
			}
		}
	}
	
	/**
	 * 获取pdf内容
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String getText(File file)throws Exception{
        boolean sort=false;
        int startPage=1;
        int endPage=1;
        PDDocument document=null;
        try{
            try{
                document=PDDocument.load(file);
            }catch(MalformedURLException e){
                
            }
            PDFTextStripper stripper=new PDFTextStripper();
            stripper.setSortByPosition(sort);
            stripper.setStartPage(startPage);
            stripper.setEndPage(endPage);
            return stripper.getText(document);
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }finally{
            if(document!=null){
                document.close();
            }
        }
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		PdfReader pdfReader = new PdfReader();
//		try {
//			// 取得E盘下的SpringGuide.pdf的内容
//			pdfReader.readFdf("E:\\Game\\doc\\test.pdf");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		File file=new File("E:\\Game\\doc\\1418806165041.pdf");
        try{
        	//获取pdf文本
            String cont=getText(file);
            String title = getSubString(cont,"\r");
            System.out.println(title);
        }catch(Exception e){
            System.out.println("Strip failed.");
            e.printStackTrace();
        }
		
//		String str = "addcbbs";
//        System.out.println("处理前的字符串：" + str);
//        String deelStr = removeMostWords(str);
//        System.out.println("处理后的字符串：" + deelStr);
	}
	
	private static String removeMostWords(String str)
    {
        if (null == str || "".equals(str))
        {
            return str;
        }
        //初始化最大次数为2次
        int iMax = 2;
        //将所有的字符与出现的次数作为一个键值对
        Map<Character, Integer> timeMap = new HashMap<Character, Integer>();
        Character cTmp;
        Integer iTmp;
        for (int i = 0; i < str.length(); i++)
        {
            cTmp = str.charAt(i);
            iTmp = timeMap.get(cTmp);
            //首次出现的字符，不需要比较
            if (null == iTmp)
            {
                timeMap.put(cTmp, 1);
                continue;
            }
            //出现次数+1，放入map
            iTmp = iTmp + 1;
            timeMap.put(cTmp, iTmp);
            //若超过最大次数，则替换最大次数
            iMax = iMax < iTmp ? iTmp : iMax;
        }
        //将所有达到最大次数的字符替换成空
        for (Map.Entry<Character, Integer> entry : timeMap.entrySet())
        {
            if (entry.getValue() == iMax)
            {
                str = str.replaceAll(String.valueOf(entry.getKey()), "");
            }
        }
        return str;
    }
	
	/**
	 * 定义一个计数器
	获取java第一次出现的位置
	从第一次出现位置后剩余的字符串中继续获取java出现的位置每获取一次就计数一次
	当获取不到时，计数完成
	 * @param str
	 * @param key
	 * @return
	 */
	public static String  getSubString(String str,String key){
        int count = 0;
        int index = 0;
        int one = 0;//第一段
        int two = 0;//第二段
//        int three = 0;//第三段
        while((index=str.indexOf(key,index))!=-1){
            index = index+key.length();
            count++;
            if(count == 1){
            	one = index;
            }else if(count == 2){
            	two = index;
            }
        }
        count = 0;
//        index = 0;
//        while((index=str.indexOf("期",index))!=-1){
//            index = index+"期".length();
//            count++;
//            if(count == 1){
//            	three = index;
//            }
//        }
        String title = str.substring(0, one).replaceAll("\r", "").replaceAll("\n", "")
        +str.substring(one, two).replaceAll("\r", "").replaceAll("\n", "");
//        int timeindex = str.indexOf("〔");
//        String time = str.substring(timeindex, three).replace("\r", "").replaceAll("\n", "");;
        return title;
    }

}
