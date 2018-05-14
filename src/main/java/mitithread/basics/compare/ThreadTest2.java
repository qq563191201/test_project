package mitithread.basics.compare;

public class ThreadTest2 implements Runnable{
	public static int shareVar = 0;
	public synchronized void run() {
	if (shareVar == 0) {
	for (int i = 0; i < 10; i++) {
	shareVar++;
	if (shareVar == 5) {
	try {
	this.wait();
	}
	catch (Exception e) {}
	}
	}
	}
	if (shareVar != 0) {
	System.out.print(Thread.currentThread().getName());
	System.out.println(" shareVar = " + shareVar);
	this.notify();
	}
	}

	public static void main(String[] args) {
	Runnable r = new ThreadTest2();
	Thread t1 = new Thread(r, "t1");
	Thread t2 = new Thread(r, "t2");
	t1.start();
	t2.start();
	} 
}
