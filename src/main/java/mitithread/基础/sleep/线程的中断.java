package mitithread.基础.sleep;

/**
 * 
 * @title 线程的中断
 * @description 
 * @author Xuxiaobing
 * @date 2014-12-31 下午02:13:02 
 * @version v1.0
 */
public class 线程的中断 implements Runnable{
	public void run() {
        System.out.println("执行run方法");
        try {
            Thread.sleep(10000);
            System.out.println("线程完成休眠");
        } catch (Exception e) {
            System.out.println("休眠被打断");
            return;  //返回到程序的调用处
        }
        System.out.println("线程正常终止");
    }
 
    public static void main(String[] args) {
    	线程的中断 he = new 线程的中断();
        Thread demo = new Thread(he, "线程");
        demo.start();
        try{
            Thread.sleep(11000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        demo.interrupt(); //2s后中断线程
    }
}
