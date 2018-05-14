package filter.chineseword;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class filterchinese {
	 public static void main(String argv[]){
        String filePath = "E:\\lesogo_data\\2.txt";
        List<String> towns = readTxtFile(filePath);
        String[] str = new String[towns.size()];
        int i = 0;
        for (String string : towns) {
        	str[i] = string;
        	i++;
//        	System.out.println(string);
		}
        String[] filtertowns = filterRepeatNum(str);
        Arrays.sort(filtertowns);
        for (String string : filtertowns) {
			System.out.println(string);
		}
    }
	/**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static List<String> readTxtFile(String filePath){
    	List<String> list = new ArrayList<String>();
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                
                while((lineTxt = bufferedReader.readLine()) != null){
                    list.add(lineTxt);
                }
                read.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;
    }
    
    /**
	* 去重复
	* @param repeatedNumArr
	* @return
	*/
	public static String[] filterRepeatNum(String[] repeatedNumArr) {
		List<String> filterResultList = new ArrayList<String>();
		for (int i = 0; i < repeatedNumArr.length; i++) {
			if (!filterResultList.contains(repeatedNumArr[i])) {
				filterResultList.add(repeatedNumArr[i]);
			}
		}
		String[] noRepeatedNumArr = new String[filterResultList.size()];
		for (int i = 0; i < noRepeatedNumArr.length; i++) {
			noRepeatedNumArr[i] = filterResultList.get(i);
		}
		return noRepeatedNumArr;
	}
}
