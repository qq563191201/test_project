package mitithread.basics.compare;

public class ThreadTest implements Runnable{
	/**
	 * 有了synchronized关键字，多线程程序的运行结果将变得可以控制。synchronized关键字用于保护共享数据。请大家注意 "共享数据"，
		你一定要分清哪些数据是共享数据，JAVA是面向对象的程序设计语言，所以初学者在编写多线程程序时，容易分不清哪些数据是共享数据
		当这个对象的一个线程访问这个对象的某个synchronized数据时，这个对象的所有被synchronized修饰的数据将被上锁（因为 "锁标志"
		被当前线程拿走了），只有当前线程访问完它要访问的synchronized数据时，当前线程才会释放 "锁标志"，
		这样同一个对象的其它线程才有机会访问synchronized数据
	 */
	public synchronized void run() {
		for (int i = 0; i < 10; i++) {
			System.out.print(" " + i);
		}
	} 
	public static void main(String[] args) {
//		Runnable r1 = new ThreadTest();
//		Runnable r2 = new ThreadTest();
//		Thread t1 = new Thread(r1);
//		Thread t2 = new Thread(r2); 
		/**
		 * 如果你运行1000次这个程序，它的输出结果也一定每次都是：01234567890123456789。因为这里的synchronized保护的是共享数据。
		 * t2是同一个对象（r）的两个线程，当其中的一个线程（例如：t1）开始执行run() 方法时，由于run() 受synchronized保护，所以同一个对象的其他线程（
			t2）无法访问synchronized方法（run方法）。只有当t1执行完后t2才有机会执行。 
		 */
		Runnable r = new ThreadTest();
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r); 
		t1.start();
	
		t2.start();
	} 
}
