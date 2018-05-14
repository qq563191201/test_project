//package com.excel;
//
//import java.io.BufferedReader;  
//import java.io.FileInputStream;  
//import java.io.IOException;  
//import java.io.InputStreamReader;  
//import java.util.ArrayList;  
//import java.util.HashMap;
//import java.util.List;  
//import java.util.Map;
//import java.util.regex.Matcher;  
//import java.util.regex.Pattern;  
//
//import test.TbFilterApp;
//  
//  
//public class TestImportCsv {  
//  
//    private InputStreamReader fr = null;  
//    private BufferedReader br = null;  
//  
//    public TestImportCsv(String f) throws IOException {  
//        fr = new InputStreamReader(new FileInputStream(f), "GBK");  
//    }  
//  
//    /** 
//     * 解析csv文件 到一个list中 每个单元个为一个String类型记录，每一行为一个list。 再将所有的行放到一个总list中 
//     */  
//    public Map<Long, TbFilterApp> readCSVFile() throws IOException {  
//    	Map<Long, TbFilterApp> map = new HashMap<Long, TbFilterApp>();
//        br = new BufferedReader(fr);  
//        String rec = null;// 一行  
//        String str;// 一个单元格  
//        try {  
//            // 读取一行  
//        	int j = 0;
//            while ((rec = br.readLine()) != null) {  
//                Pattern pCells = Pattern  
//                        .compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");  
//                Matcher mCells = pCells.matcher(rec);  
//                List<String> cells = new ArrayList<String>();// 每行记录一个list  
//                // 读取每个单元格  
//                int i = 0;
//                Long typesId = null;
//                TbFilterApp filterApp = new TbFilterApp();
//                while (mCells.find()) {  
//                    str = mCells.group();  
//                    str = str.replaceAll(  
//                            "(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");  
//                    str = str.replaceAll("(?sm)(\"(\"))", "$2");  
//                    cells.add(str);  
//                    if(j>0){
//                    	if(i==3){
//	                    	filterApp.setPackageName(str);
//	                    }
//                    	if(i==0){
//                    		typesId = Long.valueOf(str);
//	                    	filterApp.setTypesId(typesId);
//	                    }
//                    }
//                    i++;
//                }
//                if(j>0){
//                	map.put(typesId, filterApp);
//                }
//                j++;
//            }  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        } finally {  
//            if (fr != null) {  
//                fr.close();  
//            }  
//            if (br != null) {  
//                br.close();  
//            }  
//        }  
//        return map;  
//    }  
//  
//    public static void main(String[] args) throws Throwable {  
//        TestImportCsv test = new TestImportCsv("C:/Users/Administrator/Downloads/游戏列表(CPS)20150922111148.csv");
//        List<Long> list = new ArrayList<Long>();
//        list.add(2168677L);
//        list.add(1033015L);
//        list.add(731543L);//
//        list.add(2772069L);
//        list.add(3009768L);
//        list.add(2291359L);
//        list.add(730222L);//
//        list.add(730542L);
//        Map<Long, TbFilterApp> delMap = new HashMap<Long, TbFilterApp>();
//        Map<Long, TbFilterApp> addMap = new HashMap<Long, TbFilterApp>();
//        Map<Long, TbFilterApp> maps = test.readCSVFile();
//        for (Map.Entry<Long, TbFilterApp> map : maps.entrySet()) {
//			Long key = map.getKey();
//			TbFilterApp app = map.getValue();
//			boolean isTrue = false;
//			for(Long str:list){
//				if(key.compareTo(str)==0){
//					isTrue = true;
//				}
//			}
//			if(!isTrue){
//				addMap.put(key, app);
//			}
//		}
//        for(Long str:list){
//        	if(maps.get(str)==null){
//        		delMap.put(str, null);
//        	}
//        }
//        for (Map.Entry<Long, TbFilterApp> map : delMap.entrySet()) {
//        	Long key = map.getKey();
//			TbFilterApp app = map.getValue();
////			String packageName = app.getPackageName();
//        	System.out.println(key);
//        }
//    }  
//  
//}  