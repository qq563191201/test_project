package mitithread.basics.compare;

public class ThreadTest1 implements Runnable {
	public void run() {
		for (int k = 0; k < 5; k++) {
			System.out.println(Thread.currentThread().getName()
			+ " : for loop : " + k);

		}
		synchronized (this) {
			for (int k = 0; k < 5; k++) {
				System.out.println(Thread.currentThread().getName()
				+ " : synchronized for loop : " + k);
			}
		}
	}

	/**
	 * 第一个for循环没有受synchronized保护。对于第一个for循环，t1,
t2可以同时访问。运行结果表明t1执行到了k = 2时，t2开始执行了。t1首先执行完了第一个for循环，此时还没有执行完第一个for循环（
t2刚执行到k = 2）。t1开始执行第二个for循环，当t1的第二个for循环执行到k = 1时，t2的第一个for循环执行完了。
t2想开始执行第二个for循环，但由于t1首先执行了第二个for循环，这个对象的锁标志自然在t1手中（
synchronized方法的执行权也就落到了t1手中），在t1没执行完第二个for循环的时候，它是不会释放锁标志的。
所以t2必须等到t1执行完第二个for循环后，它才可以执行第二个for循环 
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable r = new ThreadTest1();
		Thread t1 = new Thread(r, "t1_name");
		Thread t2 = new Thread(r, "t2_name");
		t1.start();
		t2.start();
	}
}
