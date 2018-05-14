package mitithread.基础.sleep;

/**
 * 
 * @title 同步和死锁
 * @description 
 * @author Xuxiaobing
 * @date 2014-12-31 下午03:59:24 
 * @version v1.0
 */
public class 同步和死锁 implements Runnable{
	public void run() {
        for(int i=0;i<10;++i){
//            if(count>0){
//                try{
//                    Thread.sleep(1000);
//                }catch(InterruptedException e){
//                    e.printStackTrace();
//                }
//                System.out.println(count--);
//            }
        	sale();
        }
    }
	
	//同步方法
	public synchronized void sale() {
        if (count > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(count--);
        }
	}
 
    public static void main(String[] args) {
    	同步和死锁 he=new 同步和死锁();
        Thread h1=new Thread(he);
        Thread h2=new Thread(he);
        Thread h3=new Thread(he);
        h1.start();
        h2.start();
        h3.start();
    }
    private int count=5;
}
