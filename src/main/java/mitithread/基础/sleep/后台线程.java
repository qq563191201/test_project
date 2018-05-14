package mitithread.基础.sleep;

/**
 * 
 * @title 后台线程
 * @description 
 * @author Xuxiaobing
 * @date 2014-12-31 下午02:32:14 
 * @version v1.0
 */
public class 后台线程 implements Runnable{
	public void run() {
		int i = 0;
        while (true) {
        	i++;
            System.out.println(Thread.currentThread().getName() + "在运行"+i);
        }
    }
	 
	public static void main(String[] args) {
		后台线程 he = new 后台线程();
        Thread demo = new Thread(he, "线程");
        demo.setDaemon(true);
        demo.start();
    }
}
