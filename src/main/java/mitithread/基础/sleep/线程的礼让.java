package mitithread.基础.sleep;

/**
 * 
 * @title 线程的礼让
 * @description  
 * @author Xuxiaobing
 * @date 2014-12-31 下午02:59:45 
 * @version v1.0
 */
public class 线程的礼让 implements Runnable{
	@SuppressWarnings("static-access")
	public void run() {
        for(int i=0;i<5;++i){
            System.out.println(Thread.currentThread().getName()+"运行"+i);
            if(i==3){
                System.out.println("线程的礼让");
                Thread.currentThread().yield();
            }
        }
    }
 
    public static void main(String[] args) {
        Thread h1=new Thread(new 线程的礼让(),"A");
        Thread h2=new Thread(new 线程的礼让(),"B");
        h1.start();
        h2.start();
         
    }
}
