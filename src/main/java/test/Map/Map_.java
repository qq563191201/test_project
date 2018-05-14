package test.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import test.Map.model.MapModel;
import test.Map.model.MapModel.Datas;

public class Map_ {  
    public static void main(String[] args) { 
    	Iteration();//迭代
//    	Map<String, Integer> map = new HashMap<String, Integer>();
//    	map.put("d", 2);
//    	map.put("c", 1);
//    	map.put("b", 1);
//    	map.put("a", 3);
//    	List<Map.Entry<String, Integer>> infoIds =
//    	    new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
//    	sort(infoIds);
    }
    
    /**
     * 性能测试
     */
    
    /**
     * Map 方法迭代
     */
    public static void Iteration(){
    	 Map<String,String> map=new HashMap<String,String>();  
         map.put("1", "张三");  
         map.put("2", "李四");  
         map.put("3", "王五");  
         map.put("4", "王五1");
         map.put("5", "王五2");
         /*方法一 ：迭代程序*/  
         System.out.println("方法一：");  
         Iterator iterator=map.entrySet().iterator();  
         while(iterator.hasNext()){        
        	 Map.Entry<String, String> entry= (Entry<String, String>) iterator.next();  
        	 System.out.println("key:"+entry.getKey()+" value"+entry.getValue());    
         }         
         /*方法二*/  
         System.out.println("方法二：");
         for (Map.Entry<String, String> m : map.entrySet()) {  
        	 System.out.println("key:"+m.getKey()+" value"+m.getValue());  
         }  
    }
    
    public static void sort(List<Map.Entry<String, Integer>> infoIds){
    	Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {   
    	    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
    	        return (o2.getValue() - o1.getValue()); //vlaue排序
//    	        return (o1.getKey()).toString().compareTo(o2.getKey());//key排序
    	    }
    	});
    	//排序后
    	for (int i = 0; i < infoIds.size(); i++) {
    	    String id = infoIds.get(i).toString();
    	    System.out.println(id);
    	}
    }
    
    private static MapModel setModel(){
    	MapModel model = MapModel.FactoryMapModel();
    	List<Datas> list = new ArrayList<Datas>();
    	Datas one = MapModel.FactoryDatas();
    	one.setId("1001");
    	one.setName("北京");
    	one.setPinying("beijing");
    	one.setTime("2014-08-06 10:00:00");
    	list.add(one);
    	one.setId("1002");
    	one.setName("重庆");
    	one.setPinying("chongqing");
    	one.setTime("2014-08-05 10:00:00");
    	list.add(one);
    	one.setId("1003");
    	one.setName("南京");
    	one.setPinying("nanjing");
    	one.setTime("2014-08-03 10:00:00");
    	list.add(one);
    	one.setId("10007");
    	one.setName("天津");
    	one.setPinying("tianjin");
    	one.setTime("2014-08-04 10:00:00");
    	list.add(one);
    	return model;
    }
}  