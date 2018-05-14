package mitithread.basics.compare;

public class Thread_sleep implements Runnable{
	@SuppressWarnings("static-access")
	public void run() {
		for (int k = 0; k < 5; k++) {
			if (k == 2) {
				try {
					Thread.currentThread().sleep(5000);
				}
				catch (Exception e) {}
			}
			System.out.print(" " + k);
		}
	}
	public static void main(String[] args) {
		Runnable r = new Thread_sleep();
		Thread t = new Thread(r);
		t.start();
	} 
}
