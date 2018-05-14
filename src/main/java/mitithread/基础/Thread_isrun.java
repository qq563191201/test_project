package mitithread.基础;

/**
 * 判断线程是否启动
 * @title http://www.cnblogs.com/rollenholt/archive/2011/08/28/2156357.html
 * @description 
 * @author Xuxiaobing
 * @date 2014-12-30 下午03:41:53 
 * @version v1.0
 */
public class Thread_isrun implements Runnable{
	public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
 
    public static void main(String[] args) {
    	Thread_isrun he = new Thread_isrun();
        Thread demo = new Thread(he);
        System.out.println("线程启动之前---》" + demo.isAlive());
        demo.start();
        System.out.println("线程启动之后---》" + demo.isAlive());
    }
}
