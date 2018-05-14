package number.algorithm;

import java.util.ArrayList;
import java.util.List;

public class prime {
	/**
	 * 四年一闰；百年不闰；四百年再闰。 
	 * @param year
	 * @return
	 */
	boolean isLeapYear(int year) {  
	    return (year%4 == 0 && year%100 !=0) || (year%400 == 0);  
	}
	/**
	 * 质数就是除了1和它本身以外不能再被其他数整除的数
	 * @param n
	 * @return
	 */
	boolean isPrimeNumber(int n) {
		int n1 = (int) Math.sqrt(n);
		for (int i = 2; i <= n1; i++) {
			if(n%i == 0) {
				return false;
			}
		}
		return true;
	}
	//返回质因数数组  
	Integer[] decPrime(int n) {  
	    List<Integer> list = new ArrayList<Integer>();  
	    for (int i=2;i <= n;i++){  
	        while(n != i){  
	            if(n%i != 0){  
	                break;//不能整除肯定不是因数，能够整除在这里一定是质数。因为所有的2，3,5,7  
	                      //都被除完之后。剩下的因数只能是奇数，且是质数。  
	            }  
	            list.add(Integer.valueOf(i));  
	            n = n/i;  
	        }  
	    }  
	    list.add(Integer.valueOf(n));  
	    return list.toArray(new Integer[list.size()]);  
	} 
	public static void main(String args[]){
		System.out.println(new prime().isPrimeNumber(19));
	}
}
