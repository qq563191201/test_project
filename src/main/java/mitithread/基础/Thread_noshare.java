package mitithread.基础;

/**
 * 
 * @title 继承Thread类，不能资源共享
 * @description 
 * @author Xuxiaobing
 * @date 2014-12-30 下午02:25:21 
 * @version v1.0
 */
public class Thread_noshare extends Thread{
	public void run() {
        for (int i = 0; i < 7; i++) {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName()+"count= " + count--);
            }
        }
    }
 
    public static void main(String[] args) {
    	Thread_noshare h1 = new Thread_noshare();
    	Thread_noshare h2 = new Thread_noshare();
    	Thread_noshare h3 = new Thread_noshare();
        h1.start();
        h2.start();
        h3.start();
//        new Thread(h1, "1号窗口").start();
//        new Thread(h1, "2号窗口").start();
//        new Thread(h1, "3号窗口").start();
    }
 
    private int count = 5;
}
