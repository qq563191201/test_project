package mitithread.基础;

/**
 * 继承Runnable类，资源共享
 * @title
 * @description 
 * @author Xuxiaobing
 * @date 2014-12-30 下午03:42:27 
 * @version v1.0
 */
public class Thread_share implements Runnable{
	private int ticket = 5;  //5张票
	 
    public void run() {
        for (int i=0; i<=20; i++) {
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName()+ "正在卖票"+this.ticket--);
            }
        }
    }
    public static void main(String [] args) {
    	Thread_share my = new Thread_share();
    	Thread_share my1 = new Thread_share();
    	Thread_share my2 = new Thread_share();
        new Thread(my, "1号窗口").start();
        new Thread(my, "2号窗口").start();
        new Thread(my, "3号窗口").start();
    }
}
