package test.Map;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Set_ {
	
	public static void main(String args[]){
		Set set=new HashSet();
		add(set);
		String newStr = "hello";
		boolean is = isExists(newStr, set);
		System.out.println(is);
	}
	
	/**
	 * add参数
	 * @param set
	 */
	public static void add(Set set){
		String s1=new String("hello");
		String s2=s1;
		String s3=new String("world");
		set.add(s1);
		set.add(s2);
		set.add(s3);
	}
	
	/**
	 * 判断参数是否存在
	 * @param newStr
	 * @param set
	 * @return
	 */
	public static boolean isExists(String newStr, Set set){
		boolean isExists=false;
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			String oldStr = (String) iterator.next();  
			if(newStr.equals(oldStr)){
				isExists=true;
			}
		} 
		return isExists;
	}
}
