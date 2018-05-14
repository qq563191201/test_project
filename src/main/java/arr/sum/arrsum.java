package arr.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class arrsum {
	public static void main(String[] args) {
//		 System.out.println(3<<2);
		List<String> a = Arrays.asList("a", "b", "c");
	}
	
	//找出数组中不同的参数
	public static void compar() {
		List<String> a = Arrays.asList("a", "b", "c");
		List<String> b = Arrays.asList("a", "b");
		Collection<String> c = CollectionUtils.disjunction(a, b);
		String[] arr = c.toArray(new String[c.size()]);
		System.out.println(Arrays.toString(arr));
		CollectionSynchronized();
	}
	
	public void sef() {
		//如何在一个数组中求出任意几个数的和等于给定数
		 int[] a = { 0, 2, 3, 1, 4, 10, 23, 7, 8, 9, 6, 3 };
		 int initVal = 10;
		 for (int i = 1; i < 1 << a.length; i++) {
			 int sum = 0;
			 StringBuffer sb = new StringBuffer();
			 for (int j = 0; j < a.length; j++) {
				 if ((i & 1 << j) != 0) { 
					 sum += a[j];
					 sb.append(a[j]).append("+");
					 System.out.println("ii<<<"+i+",j<<<"+j);
				 }
			 }
			 if (sum == initVal) {
				 System.out.println(sb);
			 }
		 }
	}
	
	public static <T> List<T> compare(T[] t1, T[] t2) {  
        List<T> list1 = Arrays.asList(t1);  
        List<T> list2 = new ArrayList<T>();  
        for (T t : t2) {  
            if (!list1.contains(t)) {  
                list2.add(t);  
            }  
        }  
        return list2;  
    }  
	
	public static void CollectionSynchronized(){
		List<Integer> scoreupdate = Collections.synchronizedList(new ArrayList<Integer>());
		scoreupdate.add(1);
		scoreupdate.add(1);
		scoreupdate.add(3);
		for(Integer i:scoreupdate){
			System.out.println(i);
		}
	}
}
