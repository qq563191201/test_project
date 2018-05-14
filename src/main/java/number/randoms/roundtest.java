package number.randoms;

import java.util.Random;

public class roundtest {
	public static void main(String[] args) {
		int[] arr = randomArray(1, 100, 100);
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
	
    /** 
     * 随机指定范围内N个不重复的数 
     * 在初始化的无重复待选数组中随机产生一个数放入结果中， 
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换 
     * 然后从len-2里随机产生下一个随机数，如此类推 
     * @param max  指定范围最大值 
     * @param min  指定范围最小值 
     * @param n  随机数个数 
     * @return int[] 随机数结果集 
     */  
    public static int[] randomArray(int min,int max,int n){  
        int len = max-min+1;  
          
        if(max < min || n > len){  
            return null;  
        }  
          
        //初始化给定范围的待选数组  
        int[] source = new int[len];  
           for (int i = min; i < min+len; i++){  
            source[i-min] = i;  
           }  
             
           int[] result = new int[n];  
           Random rd = new Random();  
           int index = 0;  
           for (int i = 0; i < result.length; i++) {  
            //待选数组0到(len-2)随机一个下标  
               index = Math.abs(rd.nextInt() % len--);  
               //将随机到的数放入结果集  
               result[i] = source[index];  
               //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换  
               source[index] = source[len];  
           }  
           return result;  
    }  
	
	/**
	 * 6未数字随机数
	 */
	public void round6(){
		for(int i = 0;i<10;i++){
			String code = (int)(Math.random()*999999+100000)+"";
			if(code.length() > 6){
				code = code.substring(0, 6);
			}
		    System.out.println( String.valueOf(Math.random()).substring(2,8));
		}
	}
	
	/**
	 * @Title: 最小值最大值随机
	 * @Description:
	 * @author Xuxiaobing
	 * @date 2016年4月23日 下午4:10:06
	 * @version V1.0
	 */
	public static void minMax(){
		int max=10;
        int min=5;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
		for(int i=0;i<100;i++){
			Random random = new Random();
	        int num = random.nextInt(max)%(max-min+1) + min;
	        if(num == 5){
	        	n5++;
	        }
	        if(num == 6){
	        	n6++;
	        }
	        if(num == 7){
	        	n7++;
	        }
	        if(num == 8){
	        	n8++;
	        }
	        if(num == 9){
	        	n9++;
	        }
	        if(num == 10){
	        	n10++;
	        }
		}
		System.out.println(n5+"/"+n6+"/"+n7+"/"+n8+"/"+n9+"/"+n10+"/");
	}
}
