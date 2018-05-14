package mitithread.基础.sleep;

public class sleep线程的休眠  implements Runnable{
	public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
 
    public static void main(String[] args) {
    	sleep线程的休眠 he = new sleep线程的休眠();
        Thread demo = new Thread(he, "线程");
        demo.start();
    }
}
