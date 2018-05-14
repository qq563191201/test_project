package test.Map.listsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class sortlist {
	public static void main(String[] args) {
        List<Float> list1=  new ArrayList<Float> ();
        list1.add(1.2f);
        list1.add(11.1f);
        list1.add(11.3f);
        list1.add(21.0f);
        list1.add(13.0f);
        list1.add(15.0f);
        list1.add(0.5f);
        //打印
        System.out.print("sort前:");
        for (int i = 0; i < list1.size(); i++) {
            System.out.print(list1.get(i)+" ");
        }
        //排序
        Collections.sort(list1);
        //打印 
        System.out.println();
        System.out.print("sort后:");
        for (int i = 0; i < list1.size(); i++) {
            System.out.print(list1.get(i)+" ");
        }
    }
}
