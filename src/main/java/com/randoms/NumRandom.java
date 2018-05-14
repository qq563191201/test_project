package com.randoms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class NumRandom {
	
	//用Random.nextInt(2)获取0,1随机数  
    //获取概率均为0.5，但不随机  
    public static int randomBase0() {  
        Random r = new Random(); 
        int n = r.nextInt(2);
        return n;  
    }  
    
    //检测Random.nextInt(2)，连续获取两位0和1随机数  
    public static int randomBaseS() {  
        String s = new String(new StringBuffer().append(getBoolean()).append(getBoolean()));  
          
        if("00".equals(s)){  
            return 0;  
        }else if("01".equals(s)){  
            return 1;  
        }else if("10".equals(s)){  
            return 2;  
        }else{  
            return 3;  
        }  
    }  
    //获取随机数二进制字符串  
    public static String getBoolean(){  
    	String s = new String(new Integer(randomBase0()).toString());
        return s;  
    }
    
    //赌博概率
    public static void dubo() {
    	System.out.println("赌大小的游戏，输入0代表小，输入1代表大");
    	int n = 0;
    	try {
    		n = Integer.parseInt(new BufferedReader(new InputStreamReader(
    				System.in)).readLine());
    	} catch (Exception e) {
    		System.out.println("N必须为正整数");
    		return;
    	}
    	if (n < 0 || n > 1) {
    		System.out.println("N是0和1");
    		return;
    	}
    	if (n == 0) {
    		System.out.println("你猜的是“小”");
    	} else {
    		System.out.println("你猜的是“大”");
    	}
    	int resultArr[] = new int[3];
    	switch (n) {
    	case 0:
    		for (int i = 0; i < 3; i++) {
    			if (new Random().nextDouble() < 0.7) {
    				int j = new Random().nextInt(3) + 4;
    				resultArr[i] = j;
    				System.out.print(j + " ");
    			} else {
    				int j = new Random().nextInt(3) + 1;
    				resultArr[i] = j;
    				System.out.print(j + " ");
    			}
    		}
    		break;
    	case 1:
    		for (int i = 0; i < 3; i++) {
    			if (new Random().nextDouble() < 0.7) {
    				int j = new Random().nextInt(3) + 1;
    				resultArr[i] = j;
    				System.out.print(j + " ");
    			} else {
    				int j = new Random().nextInt(3) + 4;
    				resultArr[i] = j;
    				System.out.print(j + " ");
    			}
    		}
    		break;
    	}
    	boolean isAllIdentical = true;
    	for (int i = 0; i < resultArr.length - 1; i++) {
    		if (resultArr[i] != resultArr[i + 1]) {
    			isAllIdentical = false;
    			break;
    		}
    	}
    	if (isAllIdentical) {
    		System.out.println("通杀！你输了！");
    	} else {
    		int total = 0;
    		for (int e : resultArr) {
    			total += e;
    		}
    		if (total < 11) {
    			System.out.print("结果：小！");
    			if (n == 0) {
    				System.out.println("你赢了！");
    			} else {
    				System.out.println("你输了！");
    			}
    		} else {
    			System.out.print("结果：大！");
    			if (n == 1) {
    				System.out.println("你赢了！");
    			} else {
    				System.out.println("你输了！");
    			}
    		}
    	}
	}
    
    public static void main(String[] args) {
//    	dubo();
    	for(int i=0;i<30;i++)
    	System.out.println(new Random().nextInt(5));
	}

}
