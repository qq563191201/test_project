package sort.list;

import java.util.ArrayList;
import java.util.List;

public class sortlist {
	/**
	 * @title
	 * @description 
	 * @author Xuxiaobing
	 * @date 2015-4-20 下午01:23:32 
	 * @version v1.0
	 */
	
	public static void main(String[] args){
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(54);
		list.add(43);
		list.add(8);
		list.add(33);
		list.add(103);
		list.add(303);
		for(int i=0;i<list.size()-1;i++){
			for(int j=1;j<list.size()-i;j++){
				Integer lj = list.get(j-1);
				Integer lj_ = list.get(j);
				Integer a;
				if(lj.compareTo(lj_)<0) {   //比较两个整数的大小
					a=list.get(j-1);
					list.set((j-1),list.get(j));
					list.set(j,a);
				}
			}
		}
		for(Integer s:list) {
			System.out.println(s.intValue());
		}
//		sort_list();
	}
	
	/**
	 * 
	 * @title 排序
	 * @description
	 * @author Xuxiaobing
	 * @date 2015-4-20 下午02:28:28
	 */
	public static void sort_list(){
		ArrayList<Integer> list=new ArrayList<Integer>();
		  list.add(76);
		  list.add(4);
		  list.add(786);
		  list.add(43);
		  list.add(21);
		  list.add(432);
		  list.add(10);
		  for(int i=0;i<list.size()-1;i++) {
			  for(int j=1;j<list.size()-i;j++) {
				  Integer a;
				  if((list.get(j-1)).compareTo(list.get(j))<0) {   //比较两个整数的大小
					  a=list.get(j-1);
					  list.set((j-1),list.get(j));
					  list.set(j,a);
				  }
			  }
		  }
		  for(Integer s:list) {
			  System.out.println(s.intValue());
		  }
	}
}
