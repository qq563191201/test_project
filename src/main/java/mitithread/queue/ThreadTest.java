package mitithread.queue;

/**
 * ClassName: ThreadTest <br/> 
 * Function: 那么这种方式可以顺利创建出一个新的线程么？答案是肯定的。
 * 至于此时的线程执行体到底是MyRunnable接口中的run()方法还是MyThread类中的run()方法呢？
 * 通过输出我们知道线程执行体是MyThread类中的run()方法。
 * 其实原因很简单，因为Thread类本身也是实现了Runnable接口，而run()方法最先是在Runnable接口中定义的方法
 * @author Xuxiaobing
 * @date 2016年6月30日 上午11:11:06
 * @version V1.0
 */
public class ThreadTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Runnable myRunnable = new MyRunnable();
                Thread thread = new MyThread(myRunnable);
                thread.start();
            }
        }
    }
}

class MyRunnable implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        System.out.println("in MyRunnable run");
        for (i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

class MyThread extends Thread {

    private int i = 0;
    
    public MyThread(Runnable runnable){
        super(runnable);
    }

    @Override
    public void run() {
        System.out.println("in ThreadTest run");
        for (i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}