package com.randoms;

import java.util.Random;

public class RandomWei {
	
	public static void main(String[] args) {
		Random rnd = new Random(10);
//		rnd.setSeed(30);
		Random rnd1 = new Random(10);
//		rnd1.setSeed(30);
		for(int i=0;i<100;i++){
			System.out.println("t1="+rnd.nextInt());
			System.out.println("t2="+rnd1.nextInt());
		}
	}
	
	/**
	* 去-n到n的随机数
	* 
	* @param n
	* @return b~n随机数
	*/
	public static int Rand(int n) {
		if (n == 0)
		return 0;
		return (new Random()).nextInt() % n;
	}
}
