package com.collections;

import java.util.Arrays;

public class CollectionSort {
	
	public static void main(String[] args) {
//		int[] arr = {15, 16, 10, 5, 8, 3, 9};
//		int[] arr1 = bubbleSort(arr);
//		for(int i: arr1){   
//        	System.out.println(i);   
//        }  
		sort();
	}
	
	public static int min(){
		Integer[] arr = {15, 16, 10, 5, 8, 3, 9};
		Integer min = arr[0];
		for(int i=0;i<arr.length;i++){
			if(min>arr[i]){
				min = arr[i];
			}
		}
		return min;
	}
	
	public static int max(){
		Integer[] arr = {15, 16, 10, 5, 8, 3, 9};
		Integer max = arr[0];
		for(int i=0;i<arr.length;i++){
			if(max<arr[i]){
				max = arr[i];
			}
		}
		return max;
	}
	
	public static void sort(){
		Integer[] arr = {15, 16, 10, 5, 8, 3, 9};
		Arrays.sort(arr);  //进行排序   
        for(int i: arr){   
        	System.out.println(i);   
        }  
	}
	
    public static int[] bubbleSort(int[] args){//冒泡排序算法   
    	for(int i=0;i<args.length-1;i++){   
    		for(int j=i+1;j<args.length;j++){   
    			if (args[i]>args[j]){   
    				int temp=args[i];   
    				args[i]=args[j];   
    				args[j]=temp;   
               	}   
            }  
       }  
       return args;  
} 
}
