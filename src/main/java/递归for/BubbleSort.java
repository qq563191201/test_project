package 递归for;

public class BubbleSort {
	public static void main(String[] args){
		int score[] = {67, 65, 75, 87, 89, 90, 99, 100};
		System.out.println("\u4e2d\u56fd");
//		BubbleSort ll = new BubbleSort();
//		ll.insertSort(score);
		maopao();
    }
	
	/**
	 * 
	 * @title 冒泡排序
	 * @description http://zh.wikipedia.org/wiki/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F
	 * @author Xuxiaobing
	 * @date 2015-1-22 下午05:00:38
	 */
	public static void maopao(){
		int arr[] = {};
		int length = arr.length;
		int score[] = {67, 65, 75, 87, 89, 90, 99, 100};
		for(int i=0;i<score.length-1;i++){	//最多做n-1趟排序
			for(int j=0;j<score.length-i-1;j++){	//对当前无序区间score[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
				if(score[j]<score[j+1]){//把小的值交换到后面
					int temp = score[j];
					score[j] = score[j+1];//比较相邻的元素。如果第一个比第二个大，就交换他们两个。
					score[j+1] = temp;
				}
			}
			System.out.print("第" + (i + 1) + "次排序结果：");
         	for(int a = 0; a < score.length; a++){
         		System.out.print(score[a] + "\t");
         	}
         	System.out.println("");
		}
		System.out.print("最终排序结果：");
		for(int a=0;a<score.length;a++){
			System.out.print(score[a] + "\t");
		}
	}
	
	/**
	 * 插入排序
	 */
    public void insertSort(int a[]){  
    	int score[] = {67, 65, 61, 63, 89, 90, 87, 66};
    	Long l1 = System.currentTimeMillis();
        int length=score.length; //数组长度  
        int j;               //当前值的位置  
        int i;               //指向j前的位置  
        int key;             //当前要进行插入排序的值  
        //从数组的第二个位置开始遍历值  
        for(j=1;j<length;j++){  
            key=score[j];  
            i=j-1;  
            //a[i]比当前值大时，a[i]后移一位,空出i的位置，好让下一次循环的值后移  
            while(i>=0 && score[i]>key){  
            	score[i+1]=score[i]; //将a[i]值后移  
                i--;         //i前移  
            }//跳出循环(找到要插入的中间位置或已遍历到0下标)  
            score[i+1]=key;    //将当前值插入  
            System.out.print("第" + (j) + "次排序结果：");
         	for(int b = 0; b < score.length; b++){
         		System.out.print(score[b] + "\t");
         	}
         	System.out.println("");
        }  
        System.out.print("最终排序结果：");
        for (int k : score) {
			System.out.print(k + "\t");
		}
        Long l2 = System.currentTimeMillis();
        System.out.println("");
        System.out.println(l2-l1);
    }  
}