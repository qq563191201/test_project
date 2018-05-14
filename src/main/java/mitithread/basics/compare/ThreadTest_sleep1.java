package mitithread.basics.compare;

public class ThreadTest_sleep1 implements Runnable{
	public void run() {

		for (int k = 0; k < 5; k++) {
		if (k == 2) {
		try {
		Thread.currentThread().sleep(5000);
		}
		catch (Exception e) {}
		}
		System.out.println(Thread.currentThread().getName()
		+ " : " + k);
		} 
	}

	public static void main(String[] args) throws Exception{
		Runnable r = new ThreadTest_sleep1();
		Thread t1 = new Thread(r, "t1_name");
		Thread t2 = new Thread(r, "t2_name");
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start(); 
	} 
}
